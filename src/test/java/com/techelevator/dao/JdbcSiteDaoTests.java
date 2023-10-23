package com.techelevator.dao;

import com.techelevator.model.Site;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcSiteDaoTests extends BaseDaoTests {

    private SiteDao dao;

    @Before
    public void setup() {
        dao = new JdbcSiteDao(dataSource);
    }

    @Test
    public void getSitesWithRVAccessByParkId_Should_Return_Sites_With_Positive_RV_Length() {
        int parkId = 1;
        int rvLength = 1;
        List<Site> sites = dao.getSitesAvailableTodayByParkId(parkId);
        assertEquals("Incorrect count of site", 2, sites.size());
    }

    @Test
    public void getSitesAvailableTodayByParkId_Should_Return_Available_Parks() {
        List<Site> sites = dao.getSitesAvailableTodayByParkId(1);
        assertEquals("Incorrect count of currently available sites", 0, sites.size());

    }
}
