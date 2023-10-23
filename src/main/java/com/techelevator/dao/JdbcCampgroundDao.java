package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Campground getCampgroundById(int id) {
        Campground campground = null;
        String sql = "SELECT * FROM campground WHERE campground_id = ? ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if (result.next()) {
            campground = mapRowToCampground(result);
            return campground;
        } else  {
            return null;
        }
<<<<<<< HEAD
=======

>>>>>>> 0a94f3783a43ea0efa7648a9788bf709846a1562
    }

    @Override
    public List<Campground> getCampgroundsByParkId(int parkId) {
        List<Campground> campgrounds = new ArrayList<>();
        String sql = "SELECT * FROM campground WHERE park_id = ? ; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

<<<<<<< HEAD
        while (result.next()) {
            Campground campground = mapRowToCampground(result);
            campgrounds.add(campground);
        }
            return campgrounds;
=======
        if (result.next()) {
            Campground campground = mapRowToCampground(result);
            campgrounds.add(campground);
            return campgrounds;
        } else {
            return null;
        }

>>>>>>> 0a94f3783a43ea0efa7648a9788bf709846a1562
    }

    private Campground mapRowToCampground(SqlRowSet results) {
        Campground campground = new Campground();
        campground.setCampgroundId(results.getInt("campground_id"));
        campground.setParkId(results.getInt("park_id"));
        campground.setName(results.getString("name"));
        campground.setOpenFromMonth(results.getInt("open_from_mm"));
        campground.setOpenToMonth(results.getInt("open_to_mm"));
        campground.setDailyFee(results.getDouble("daily_fee"));
        return campground;
    }

    List <Park> parks = new ArrayList<>();
    List <Reservation> reservations;
}
