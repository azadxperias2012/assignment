package com.assignment.stockexchange.db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExchangeManager {
	
	private static StringBuffer logMessageBuffer = new StringBuffer();
	
	public static synchronized String displayCompanies() {
		String result = "";
		String sql = "SELECT company.id AS CompanyId, " +
				"(SELECT GROUP_CONCAT(country_code SEPARATOR \",\") FROM country_lookup WHERE company_id = company.id) AS Countries, " +
				"CONCAT(company.budget, '$') AS Budget, CONCAT(company.bid, ' cent') AS Bid, " +
			    "(SELECT GROUP_CONCAT(category_name SEPARATOR \",\") FROM category_lookup WHERE company_id = company.id) AS Category " +
				"FROM company;";
		
		try (
				Connection conn = DataSource.getInstance().getConnection();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sql);
			) {
			
			StringBuffer bf = new StringBuffer();
			bf.append("Company Table:\n");
			while(rs.next()) {				
				bf.append(rs.getString("CompanyId") + " ");
				bf.append(rs.getString("Countries") + " ");				
				bf.append(rs.getString("Budget") + " ");
				bf.append(rs.getString("Bid") + " ");
				bf.append(rs.getString("Category"));
				bf.append("\n");
			}
			result = bf.toString();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public static synchronized String matchCompany(String countryCode, String category, int baseBid) {
		String result = "";

		String sql = "SELECT a.id AS CompanyId, b.country_code AS Country, a.budget AS Budget, a.bid AS Bid, c.category_name AS Category" + 
		" FROM company a" +
		" LEFT JOIN country_lookup b ON a.id = b.company_id && b.country_code = ?" +
		" LEFT JOIN category_lookup c ON a.id = c.company_id && c.category_name = ?;";
		
		ResultSet rs = null;
		try (
				Connection conn = DataSource.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			) {
			stmt.setString(1, countryCode);
			stmt.setString(2, category);
			rs = stmt.executeQuery();
			
			List<MatchedCompanyBean> matchedCompanies = null;
			if (rs.isBeforeFirst()) {
				logMessageBuffer.setLength(0);
				logMessageBuffer.append("BaseTargeting:"); 
			}			
			while(rs.next()) {
				String companyId = rs.getString("CompanyId");
				String countryName = rs.getString("Country");
				String categoryName = rs.getString("Category");
				
				if(countryName == null || categoryName == null) {
					logMessageBuffer.append("{" + companyId + ", Failed}");
					continue;
				}
				
				if(matchedCompanies == null) {					
					matchedCompanies = new ArrayList<MatchedCompanyBean>();
				}
				
				logMessageBuffer.append("{" + companyId + ", Passed}");
				MatchedCompanyBean matchedCompany = createMatchedCompanyBean(companyId, rs);
				matchedCompanies.add(matchedCompany);
			}

			if(matchedCompanies == null || matchedCompanies.isEmpty()) {
				result = "No Companies Passed from Targeting";
			} else {
				result = shortList(matchedCompanies, baseBid);
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return result;
	}
	
	private static MatchedCompanyBean createMatchedCompanyBean(String companyId, ResultSet rs) throws SQLException {
		double budget = rs.getDouble("Budget");
		int bid = rs.getInt("Bid");
		
		MatchedCompanyBean matchedCompany = new MatchedCompanyBean();
		matchedCompany.setCompanyId(companyId);
		matchedCompany.setBudget(budget);
		matchedCompany.setBid(bid);
		return matchedCompany;
	}
	
	private static String shortList(List<MatchedCompanyBean> matchedCompanies, int baseBid) {
		logMessageBuffer.append("\nBudgetCheck:");
		if(!applyBudgetCheckFilter(matchedCompanies, baseBid)) {
			return "No Companies Passed from Budget";
		}
		logMessageBuffer.append("\nBaseBid:");
		if(!applyBaseBidFilter(matchedCompanies, baseBid)) {
			return "No Companies Passed from BaseBid Check";
		}
		
		String winner = shortListCompany(matchedCompanies);
		logMessageBuffer.append("\nWinner = " + winner);
		return winner;
	}
	
	private static boolean applyBudgetCheckFilter(List<MatchedCompanyBean> matchedCompanies, int baseBid) {
		Iterator<MatchedCompanyBean> it = matchedCompanies.iterator();
		while (it.hasNext()) {
			MatchedCompanyBean matchedCompany = it.next();
			String companyId = matchedCompany.getCompanyId();
			double budgetInDollars = matchedCompany.getBudget();
			
			boolean budgetPass = (budgetInDollars * 100) >= baseBid;
			if(budgetPass) {
				logMessageBuffer.append("{" + companyId + ", Passed}");
			} else {
				logMessageBuffer.append("{" + companyId + ", Failed}");
				it.remove();
			}
		}
		return !matchedCompanies.isEmpty();
	}
	
	private static boolean applyBaseBidFilter(List<MatchedCompanyBean> matchedCompanies, int baseBid) {
		Iterator<MatchedCompanyBean> it = matchedCompanies.iterator();
		while (it.hasNext()) {
			MatchedCompanyBean matchedCompany = it.next();
			String companyId = matchedCompany.getCompanyId();
			int bidPrice = matchedCompany.getBid();
			
			boolean bidPricePass = bidPrice >= baseBid;
			if(bidPricePass) {
				logMessageBuffer.append("{" + companyId + ", Passed}");
			} else {
				logMessageBuffer.append("{" + companyId + ", Failed}");
				it.remove();
			}
		}
		return !matchedCompanies.isEmpty();
	}

	private static String shortListCompany(List<MatchedCompanyBean> matchedCompanies) {
		int index = -1;
		int maxBid = -1;
		for (MatchedCompanyBean matchedCompanyBean : matchedCompanies) {
			int bidPrice = matchedCompanyBean.getBid();
			if(bidPrice > maxBid) {
				maxBid = bidPrice;
				index++;
			}
		}
		MatchedCompanyBean matchedCompany = matchedCompanies.get(index);		
		reduceBudget(matchedCompany);
		return matchedCompany.getCompanyId();
	}

	private static void reduceBudget(MatchedCompanyBean matchedCompany) {
		String companyId = matchedCompany.getCompanyId();
		double budget = matchedCompany.getBudget();
		int bidPrice = matchedCompany.getBid();
		
		double updatedBudget = ((100 * budget) - bidPrice) / 100;
		String sql = "UPDATE company SET " + "budget = ?" + "WHERE id = ?;";
		try (
				Connection conn = DataSource.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {

			stmt.setDouble(1, updatedBudget);
			stmt.setString(2, companyId);
			stmt.executeUpdate();
		} catch (SQLException | IOException | PropertyVetoException e) {
			System.err.println(e);
		}
	}
}
