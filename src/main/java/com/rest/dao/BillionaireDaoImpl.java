package com.rest.dao;

import com.rest.model.Billionaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BillionaireDaoImpl implements BillionaireDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Billionaire> getBillionaires() {
        String sql = "SELECT * FROM billionaires;";
        return jdbcTemplate.query(sql, new BillionaireMapper());
    }

    @Override
    public Billionaire getBillionaire(Long billionaireId) {
        String sql = "SELECT * FROM billionaires WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new BillionaireMapper(), billionaireId);
    }

    @Override
    public int deleteBillionaire(Long billionaireId) {
        String sql = "DELETE FROM billionaires WHERE id = ?;";
        return jdbcTemplate.update(sql, billionaireId);
    }

    @Override
    public Billionaire updateBillionaire(Billionaire billionaire) {
        String sql = "UPDATE billionaires SET first_name = ?, last_name = ?, company = ?, wealth = ? WHERE id = ?;";
        jdbcTemplate.update(sql, billionaire.getFirstName(), billionaire.getLastName(), billionaire.getCompany(), billionaire.getWealth(), billionaire.getId());
        return getBillionaire(billionaire.getId());
    }

    @Override
    public int createBillionaire(Billionaire billionaire) {
        String sql = "INSERT INTO billionaires(first_name, last_name, company, wealth) VALUES(?,?,?,?);";
        return jdbcTemplate.update(sql, billionaire.getFirstName(), billionaire.getLastName(), billionaire.getCompany(), billionaire.getWealth());
    }

    private static class BillionaireMapper implements RowMapper<Billionaire> {
        @Override
        public Billionaire mapRow(ResultSet rs, int rowNum) throws SQLException {
            Billionaire billionaire = new Billionaire();
            billionaire.setId(rs.getLong("id"));
            billionaire.setFirstName(rs.getString("first_name"));
            billionaire.setLastName(rs.getString("last_name"));
            billionaire.setCompany(rs.getString("company"));
            billionaire.setWealth(rs.getString("wealth"));
            return billionaire;
        }
    }
}
