package easyBallot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easyBallot.model.Candidate;

public class CandidateDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/registration";
	private String jdbcUsername = "root";
	private String jdbcPassword = "@swayam9";
	
	private static final String UPDATE_USERS_SQL = "update candidates set  approved =?, rejected = ? where id = ?;";
	private static final String SELECT_ALL_USERS = "select * from candidates";
	private static final String SELECT_USER_BY_ID = "select id,name,party,approved,rejected from candidates where id =?";
	
	
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
	
	public boolean updateUser(Candidate user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement pst = connection.prepareStatement(UPDATE_USERS_SQL);) {
		
			pst.setBoolean(1, user.isApproved());
			pst.setBoolean(2, user.isRejected());
			pst.setInt(3, user.getId());

			rowUpdated = pst.executeUpdate() > 0;
		}
		return rowUpdated;
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
				String party = rs.getString("party");
				Boolean approved = rs.getBoolean("approved");
				Boolean rejected = rs.getBoolean("rejected");
				candidates.add(new Candidate(id, name, party, approved,rejected));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return candidates;
	}
	
	public List<Candidate> countVotes() {

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
				int voteCount = rs.getInt("voteCount");
				boolean rejected = rs.getBoolean("rejected");
				boolean approved = rs.getBoolean("approved");
				if(!rejected && approved) {					
					candidates.add(new Candidate(id, name, voteCount));
				}
				else {
					continue;
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return candidates;
	}
	
	
	
	public Candidate selectUser(int id) {
		Candidate candidate = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String party = rs.getString("party");
				boolean approved = rs.getBoolean("approved");
				Boolean rejected = rs.getBoolean("rejected");
				candidate = new Candidate(id, name, party, approved,rejected);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return candidate;
	}

}
