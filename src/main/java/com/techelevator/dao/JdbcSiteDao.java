package com.techelevator.dao;

import com.techelevator.model.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Site> getSitesWithRVAccessByParkId(int parkId) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM site WHERE campground_id IN (SELECT campground_id FROM campground WHERE park_id = ?) AND accessibility = true ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

        while (result.next()) {
            Site site = mapRowToSite(result);
            sites.add(site);
        }
        return sites;
    }

    @Override
    public List<Site> getSitesAvailableTodayByParkId(int parkId) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT s.* FROM site s " +
                "LEFT JOIN reservation r ON s.site_id = r.site_id " +
                "WHERE s.site_id = ? " +
                "AND (r.from_date IS NULL OR r.from_date > ?) " +
                "AND (r.to_date IS NULL OR r.to_date < ?)";
        LocalDate today = LocalDate.now();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId, today, today);

        while (result.next()) {
            Site site = mapRowToSite(result);
            sites.add(site);
        }
            return sites;
    }

    private Site mapRowToSite(SqlRowSet results) {
        Site site = new Site();
        site.setSiteId(results.getInt("site_id"));
        site.setCampgroundId(results.getInt("campground_id"));
        site.setSiteNumber(results.getInt("site_number"));
        site.setMaxOccupancy(results.getInt("max_occupancy"));
        site.setAccessible(results.getBoolean("accessible"));
        site.setMaxRvLength(results.getInt("max_rv_length"));
        site.setUtilities(results.getBoolean("utilities"));
        return site;
    }
}
