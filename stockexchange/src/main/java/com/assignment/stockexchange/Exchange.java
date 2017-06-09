package com.assignment.stockexchange;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.assignment.stockexchange.db.ExchangeManager;

@Path("exchange")
public class Exchange {

	@GET
    @Path("dbsnapshot")
    @Produces(MediaType.TEXT_PLAIN)
    public String dbSnapshot() {
		String companies = ExchangeManager.displayCompanies();
        return companies;
    }

	@GET
	@Path("matchcompany")
    @Produces(MediaType.TEXT_PLAIN)
    public String matchCompany(
    		@QueryParam("countrycode")String countryCode,
    		@QueryParam("category")String category,
    		@QueryParam("basebid")int baseBid) {
		String winners = ExchangeManager.matchCompany(countryCode, category, baseBid);
        return winners;
    }

	@GET
    @Path("logs_screenshot")
    @Produces(MediaType.TEXT_PLAIN)
    public String logsScreenshot() {
		String logs = ExchangeManager.displayLogs();
        return logs;
    }
}
