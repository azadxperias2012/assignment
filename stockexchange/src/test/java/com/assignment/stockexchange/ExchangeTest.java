package com.assignment.stockexchange;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;

@RunWith(ConcurrentTestRunner.class)
public class ExchangeTest extends JerseyTest {
	
	@Override
    public Application configure() {		
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(Exchange.class);
    }
	
	@Test
	public void testDBsnapshot() {		
        Response output = target("exchange/dbsnapshot").request().get();
        assertEquals("should return status 200", 200, output.getStatus());

        String resp = output.readEntity(String.class);
        assertNotNull("Should return response", resp);
    }
	
	@Test
	public void testMatchCompany() {
        Response output = target("exchange/matchcompany").queryParam("countrycode", "US")
    			.queryParam("category", "Automobile").queryParam("basebid", 10).request().get();
        assertEquals("should return status 200", 200, output.getStatus());

        String winner = output.readEntity(String.class);
        assertNotNull("Should return response", winner);
        if(winner.equalsIgnoreCase("C1"))
        	assertEquals("C1", winner);
        else
        	assertEquals("No Companies Passed from BaseBid Check", winner);
    }
	
	@Test
	public void testMatchCompanyCountryFail() {
        Response output = target("exchange/matchcompany").queryParam("countrycode", "USA")
    			.queryParam("category", "Automobile").queryParam("basebid", 10).request().get();
        assertEquals("should return status 200", 200, output.getStatus());

        String response = output.readEntity(String.class);
        assertNotNull("Should return response", response);
        assertEquals("No Companies Passed from Targeting", response);
    }
	
	@Test
	public void testMatchCompanyCategoryFail() {
		Response output = target("exchange/matchcompany").queryParam("countrycode", "US")
    			.queryParam("category", "Automobilesss").queryParam("basebid", 10).request().get();
        assertEquals("should return status 200", 200, output.getStatus());

        String response = output.readEntity(String.class);
        assertNotNull("Should return response", response);
        assertEquals("No Companies Passed from Targeting", response);
    }
	
	@Test
	public void testMatchCompanyBudgetFail() {
		Response output = target("exchange/matchcompany").queryParam("countrycode", "IN")
    			.queryParam("category", "Finance").queryParam("basebid", 201).request().get();
        assertEquals("should return status 200", 200, output.getStatus());

        String response = output.readEntity(String.class);
        assertNotNull("Should return response", response);
        assertEquals("No Companies Passed from Budget", response);
    }
	
	@Test
	public void testMatchCompanyBaseBidFail() {
		Response output = target("exchange/matchcompany").queryParam("countrycode", "IN")
    			.queryParam("category", "Finance").queryParam("basebid", 40).request().get();
        assertEquals("should return status 200", 200, output.getStatus());

        String response = output.readEntity(String.class);
        assertNotNull("Should return response", response);
        assertEquals("No Companies Passed from BaseBid Check", response);
    }
}
