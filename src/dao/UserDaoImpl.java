package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.image.Image;
import model.User;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";

	public UserDaoImpl() {
	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
				Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME 
					+ " (username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR(8) NOT NULL," 
					+ "firstName VARCHAR(15),"
					+ "lastName VARCHAR(15),"
					+ "profileImage BLOB,"
					+ "PRIMARY KEY (username)"
					+ ")";
			stmt.executeUpdate(sql);
		}
	}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				return null;
			} 
		}
	}

	@Override
	public User getUserAll(String username, String password, String firstName, String lastName, byte[] profileImage) throws SQLException {
//		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ? AND firstName = ? AND lastName = ? AND profileImage = ?";
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection(); 
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setBytes(5, profileImage);
			
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setProfileImage(rs.getBytes("profileImage"));
					return user;
				}
				return null;
			} 
		}
	}
	
	private PreparedStatement store, retrieve;
	private String storeStmt = "INSERT INTO application(profileImage) VALUES(?)";
	private String retrieveStmt = "SELECT profileImage FROM application WHERE username = ?";
	
	@Override
	public User createUser(String username, String password, String firstName, String lastName, byte[] profileImage) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setBytes(5, profileImage);

			stmt.executeUpdate();
			return new User(username, password, firstName, lastName, profileImage);
		} 
	}
	
	// Attempt to save a chosen profile picture. 
	public Image chooseAndSaveFile(File file) {
		Image img = null;
		try {
			Connection connection = Database.getConnection();
			store = connection.prepareStatement(storeStmt);
			retrieve = connection.prepareStatement(retrieveStmt);
			FileInputStream fileInputStream = new FileInputStream(file);
			store.setBinaryStream(1, fileInputStream, fileInputStream.available()); 
			store.execute();
			img = new Image(fileInputStream);
			
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return img;
	}
	
	public void loadImage() {
		
	}

	public PreparedStatement getStore() {
		return store;
	}

	public void setStore(PreparedStatement store) {
		this.store = store;
	}

	public PreparedStatement getRetrieve() {
		return retrieve;
	}

	public void setRetrieve(PreparedStatement retrieve) {
		this.retrieve = retrieve;
	}

	public String getStoreStmt() {
		return storeStmt;
	}

	public void setStoreStmt(String storeStmt) {
		this.storeStmt = storeStmt;
	}

	public String getRetrieveStmt() {
		return retrieveStmt;
	}

	public void setRetrieveStmt(String retrieveStmt) {
		this.retrieveStmt = retrieveStmt;
	}
	
	
}
