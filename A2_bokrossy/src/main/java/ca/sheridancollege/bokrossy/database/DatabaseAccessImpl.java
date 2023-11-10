//Aiden Bokrossy
//991712275

package ca.sheridancollege.bokrossy.database;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bokrossy.beans.PasswordRecord;

// Class name DatabaseAccessImpl that implements the DatabaseAccess interface
@Repository
public class DatabaseAccessImpl implements DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	// Method to insert a PasswordRecord into the database
	public void insertPasswordRecord(PasswordRecord passwordRecord) {

		//// SQL query that inserts a PasswordRecord with parameters
		String query = "INSERT INTO passwordRecord (id, title, username, password, url, email, notes) "
				+ "VALUES (:id, :title, :username, :password, :url, :email, :notes)";

		// Create a MapSqlParameterSource and set the parameters
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", passwordRecord.getId());
		namedParameters.addValue("title", passwordRecord.getTitle());
		namedParameters.addValue("username", passwordRecord.getUsername());
		namedParameters.addValue("password", passwordRecord.getPassword());
		namedParameters.addValue("url", passwordRecord.getUrl());
		namedParameters.addValue("email", passwordRecord.getEmail());
		namedParameters.addValue("notes", passwordRecord.getNotes());

		int rowsAffected = jdbc.update(query, namedParameters);

		// Check if any rows were affected and print a message
		if (rowsAffected > 0) {
			System.out.println("A password record was inserted into the database");
		}
	}

	// Method to retrieve a list of all PasswordRecords from the database
	public List<PasswordRecord> getPasswordRecordList() {
		String query = "SELECT * FROM passwordRecord";
		// Execute the SQL query and map the results to a list
		return jdbc.query(query, new BeanPropertyRowMapper<>(PasswordRecord.class));
	}

	// Method to retrieve a list of all PasswordRecords from the database
	public List<PasswordRecord> getPasswordRecordListById(Long id) {
		String query = "SELECT * FROM passwordRecord WHERE id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		// Executes SQL query with parameters and map the results to a list of
		// PasswordRecords
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(PasswordRecord.class));
	}

}
