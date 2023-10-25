package com.techelevator.dao;

import com.techelevator.model.Campground;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcCampgroundDaoTests extends BaseDaoTests {

    private CampgroundDao dao;

    @Before
    public void setup() {
        dao = new JdbcCampgroundDao(dataSource);
    }

    @Test
    public void getCampgroundById_Should_Return_Specific_Campground() {
        Campground campground = dao.getCampgroundById(1);

        assertEquals("Incorrect campground returned for ID 1", 1, campground.getCampgroundId());
    }

    @Test
    public void getCampgroundsByParkId_Should_Return_All_Campgrounds_For_Park() {
        List<Campground> campgrounds1 = dao.getCampgroundsByParkId(1);
        List<Campground> campgrounds2 = dao.getCampgroundsByParkId(2);
        assertNotNull(campgrounds1);
        assertTrue(!campgrounds1.isEmpty());
        assertEquals("Incorrect number of campgrounds", 2, campgrounds1.size());
        assertEquals("Incorrect number of campgrounds", 0, campgrounds2.size());

    }

}
