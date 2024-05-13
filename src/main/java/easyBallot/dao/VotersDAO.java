package easyBallot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easyBallot.model.Candidate;
import easyBallot.model.Voter;

public class VotersDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/registration";
	private String jdbcUsername = "root";
	private String jdbcPassword = "@swayam9";

	private static final String SELECT_ALL_USERS = "select * from candidates";

	Voter voter = new Voter();

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public int getVoteCount() {
		int voteCount=0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				 voteCount = rs.getInt("voteCount");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return voteCount;
	}
	
	
	public List<Candidate> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Candidate> candidates = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int voterCount = rs.getInt("voterCount");
				candidates.add(new Candidate(id, name, voterCount));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return candidates;
	}
}
