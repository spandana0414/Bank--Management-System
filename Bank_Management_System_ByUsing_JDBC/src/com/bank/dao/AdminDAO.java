package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dto.CustomerDetails;

public class AdminDAO {
	
	private final static String url = "jdbc:mysql://localhost:3306/a4_bank_management_system_db?user=root&password=root";
private final static String admin_login="select * from admin_details where admin_emailid=? and admin_pin=?";
	
	public boolean selectAdminDetailsByUsingEmailidAndPassword(String emailid,String pin) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement =connection.prepareStatement(admin_login);
			preparedStatement.setString(1,emailid);
			preparedStatement.setString(2, pin);
			ResultSet resultSet= preparedStatement.executeQuery();
			if (resultSet.next()) {
			return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}


	}
	
	

}
