package com.example.totcery_backend.repositories;

import com.example.totcery_backend.domain.Address;
import com.example.totcery_backend.exceptions.TotBadRequestException;
import com.example.totcery_backend.exceptions.TotResourceNotFoundException;
import jdk.jfr.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    JdbcTemplate jdbcTemplate;


    private static final String SQL_FIND_BY_ID = "SELECT USER_ID,STATE, CITY, AREA, SOCIETY_NAME, RESPECTIVE_BLOCK, FLAT_NUMBER,  PARENT_ADDRESS " +
            "FROM TOT_USER_ADDRESS WHERE ADDRESS_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO TOT_USER_ADDRESS (ADDRESS_ID, USER_ID, STATE, CITY, AREA, SOCIETY_NAME,RESPECTIVE_BLOCK,FLAT_NUMBER, PARENT_ADDRESS) VALUES('TOT_USER_SEQ'),?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Address> findAll(Integer userId) throws TotResourceNotFoundException {
        return null;
    }

    @Override
    public Address findById(Integer userId, Integer addressId) throws TotResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{addressId}, addressRowMapper);
        } catch (Exception e) {
            throw new TotResourceNotFoundException("Address Not Found");
        }
    }

    @Override
    public Integer create(Integer userId, String state, String city, String area, String societyName, String respectiveBlock, String flatNumber, String parentAddress) {
        System.out.print("pssss");
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, state);
                ps.setString(3, city);
                ps.setString(4, area);
                ps.setString(5, societyName);
                ps.setString(6, respectiveBlock);
                ps.setString(7, flatNumber);
                ps.setString(8, parentAddress);
                System.out.print("pssss"+ ps);
                return ps;
            }, keyHolder);
            System.out.print("keyHolder"+ keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
            throw new TotBadRequestException("Invalid request");
        }
    }

    private RowMapper<Address> addressRowMapper = ((rs, rowNum) -> {
        return new Address(rs.getInt("ADDRESS_ID"),
                rs.getInt("USER_ID"),
                rs.getString("STATE"),
                rs.getString("CITY"),
                rs.getString("AREA"),
                rs.getString("SOCIETY_NAME"),
                rs.getString("RESPECTIVE_BLOCK"),
                rs.getString("FLAT_NUMBER"),
                rs.getString("PARENT_ADDRESS")
        );
    });
}