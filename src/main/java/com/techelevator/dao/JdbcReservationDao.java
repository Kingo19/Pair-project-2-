package com.techelevator.dao;

import com.techelevator.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;

public class JdbcReservationDao implements ReservationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override 
    public Reservation getReservationById(int id) {
        Reservation reservation = new Reservation();
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if (result.next()) {
            Reservation reservation1 = mapRowToReservation(result);
            return reservation1;
        } else {
            return null;
        }

    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) VALUES (?, ?, ?, ?, ?) RETURNING reservation_id; ";
        int siteId = reservation.getSiteId();
        String name = reservation.getName();
        LocalDate fromDate = reservation.getFromDate();
        LocalDate toDate = reservation.getToDate();
        LocalDate createDate = reservation.getCreateDate();
        int reservationId = jdbcTemplate.queryForObject(sql, int.class, siteId, name, createDate, fromDate, toDate);
        reservation.setReservationId(reservationId);

        return reservation;
    }

    private Reservation mapRowToReservation(SqlRowSet results) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(results.getInt("reservation_id"));
        reservation.setSiteId(results.getInt("site_id"));
        reservation.setName(results.getString("name"));
        reservation.setFromDate(results.getDate("from_date").toLocalDate());
        reservation.setToDate(results.getDate("to_date").toLocalDate());
        reservation.setCreateDate(results.getDate("create_date").toLocalDate());
        return reservation;
    }


}
