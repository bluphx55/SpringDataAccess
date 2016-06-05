package com.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("foodGroupDAO")
public class FoodGroupDAO {
	
	//private JdbcTemplate MyJdbcTemplate;
	private NamedParameterJdbcTemplate MyJdbcTemplate;
	
	
	public List<FoodGroup> getFoodGroups() {
		
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("groupName", "meats");
		
		return MyJdbcTemplate.query("select * from foodgroups where name=:groupName", myMap, new RowMapper<FoodGroup>(){

			public FoodGroup mapRow(ResultSet rs, int rowNum) throws SQLException {

				FoodGroup fg = new FoodGroup();
				fg.setId(rs.getInt("id"));
				fg.setName(rs.getString("name"));
				fg.setDescription(rs.getString("description"));
				
				return fg;
			}
			
			
		});
	}

	public NamedParameterJdbcTemplate getMyJdbcTemplate() {
		return MyJdbcTemplate;
	}

	@Autowired
	public void setMyJdbcTemplate(DataSource ds) {
		this.MyJdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}
	
	public FoodGroup getFoodGroup(int id){
			
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("id", id);
		
		return MyJdbcTemplate.queryForObject("Select * from foodgroups where id = :id", myMap, new RowMapper<FoodGroup>(){

			public FoodGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				FoodGroup fg = new FoodGroup();
				fg.setId(rs.getInt("id")); //db table column id is an int value
				fg.setName(rs.getString("name")); //db table column name is varchar
				fg.setDescription(rs.getString("description")); //db table column description is varchar
				
				return fg;
				
			}});
			
	}

	public Boolean addFoodGroup(String name, String description) {
		
		Boolean res = false;
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);
		params.addValue("description", description);
		
		int numOfRowsAffected = MyJdbcTemplate.update("insert into foodgroups (name, description) values (:name, :description)", params);
		
		if(numOfRowsAffected == 1){
			System.out.println("One row added to table foodgroups successfully");
			res = true;
		} else {
			System.out.println("There was a problem adding to table foodgroups");
		}
		
		return res; 
	}
}
