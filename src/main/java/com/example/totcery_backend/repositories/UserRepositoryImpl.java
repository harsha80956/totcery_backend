package com.example.totcery_backend.repositories;

import com.example.totcery_backend.domain.User;
import com.example.totcery_backend.exceptions.totAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final String SQL_CREATE = "INSERT INTO TOT_USERS(USER_ID, FULL_NAME, EMAIL, PASSWORD, MOBILE_NUMBER, USER_TYPE) VALUES(NEXTVAL('TOT_USERS_SEQ'), ?, ?, ?, ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM TOT_USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID,FULL_NAME, EMAIL, PASSWORD, MOBILE_NUMBER, USER_TYPE " +
            "FROM TOT_USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, FULL_NAME, EMAIL, PASSWORD, MOBILE_NUMBER, USER_TYPE "+
            "FROM TOT_USERS WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String fullName, String email, String password, String mobileNumber, String userType) throws totAuthException {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, fullName);
                ps.setString(2,email);
                ps.setString(3, hashedPassword);
                ps.setString(4, mobileNumber);
                ps.setString(5,userType);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e){
            throw new totAuthException("Invalid details, Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws totAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            if (!password.equals(user.getPassword()))
                throw new totAuthException("Invalid email/password");
            System.out.print("aaaaaa"+user);
            return user;
        }catch (EmptyResultDataAccessException e){
            throw new totAuthException("Invalid email/password");

        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("FULL_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("MOBILE_NUMBER"),
                rs.getString("USER_TYPE")
        );
    });
}
