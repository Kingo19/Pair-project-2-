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
        List<Site> sites = dao.getSitesWithRVAccessByParkId(parkId);
        Assert.assertNotNull(sites);
        Assert.assertTrue(sites.get(0).isAccessible());
        Assert.assertTrue(sites.get(0).getSiteId() == parkId);
//        assertEquals("Incorrect count of site", sites.size(), dao.getSitesWithRVAccessByParkId(parkId).size());
        Assert.assertTrue(sites.get(parkId).getMaxRvLength()>0);
    }

    @Test
    public void getSitesAvailableTodayByParkId_Should_Return_Available_Parks() {
        List<Site> sites = dao.getSitesAvailableTodayByParkId(1);
        List<Site> sites2 = dao.getSitesAvailableTodayByParkId(2);
        assertEquals("Incorrect count of currently available sites", 0, sites2.size());
        assertEquals("Incorrect count of currently available sites", 5, sites.size());

    }
}
