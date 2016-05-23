package com.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("foodGroupDAO")
public class FoodGroupDAO {
	
	private JdbcTemplate MyJdbcTemplate; 
	
	public List<FoodGroup> getFoodGroups() {
		
		return MyJdbcTemplate.query("select * from foodgroups", new RowMapper<FoodGroup>(){

			public FoodGroup mapRow(ResultSet rs, int rowNum) throws SQLException {

				FoodGroup fg = new FoodGroup();
				fg.setId(rs.getInt("id"));
				fg.setName(rs.getString("name"));
				fg.setDescription(rs.getString("description"));
				
				return fg;
			}
			
			
		});
	}

	public JdbcTemplate getMyJdbcTemplate() {
		return MyJdbcTemplate;
	}

	@Autowired
	public void setMyJdbcTemplate(DataSource ds) {
		this.MyJdbcTemplate = new JdbcTemplate(ds);
	}
	
	

}
