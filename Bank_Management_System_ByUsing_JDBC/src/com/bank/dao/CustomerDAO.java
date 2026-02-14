package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dto.CustomerDetails;

public class CustomerDAO {
	/*
	 * Insertion Update Delete Selection
	 */
	private final static String insertion = "insert into customer_details(customer_name, customer_emailid, "
			+ "aadhar_number, pan_number, mobile_number, customer_address, gender, Date_Of_Birth, customer_age, Amount) "
			+ "values(?,?,?,?,?,?,?,?,?,?)";
	private final static String url = "jdbc:mysql://localhost:3306/a4_bank_management_system_db?user=root&password=root";

	private final static String customer_login = "select * from customer_details where customer_emailid=? and PIN=?";
   private final static String byusingacno="select * from customer_details where Account_Number=?";
	
   private final static String update_Amount="update customer_details set Amount =? where Account_Number=?";
   private final static String by_null="select * from customer_details where Account_Number is null and pin is null"   ;
   private final static String update_account_and_pin=
		   "Update customer_details  set account_number=?,pin=? where aadhar_number=?";
   
   public void insertCustomerDetails(CustomerDetails customerDetails) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insertion);
			preparedStatement.setString(1, customerDetails.getCustomername());
			preparedStatement.setString(2, customerDetails.getCustomeremailid());
			preparedStatement.setLong(3, customerDetails.getAadharnumber());
			preparedStatement.setString(4, customerDetails.getPannumber());
			preparedStatement.setLong(5, customerDetails.getMobilenumber());
			preparedStatement.setString(6, customerDetails.getCustomeraddress());
			preparedStatement.setString(7, customerDetails.getGender());
			preparedStatement.setDate(8, customerDetails.getDateofbirth());
			preparedStatement.setInt(9, customerDetails.getCustomerage());
			preparedStatement.setDouble(10, customerDetails.getAmount());
			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				System.out.println("Registration Successfull...");
			} else {
				System.out.println("500 Error");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public CustomerDetails selectCustomerDetailsByUsingEmailidAndPin(String emailid,int pin) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement =connection.prepareStatement(customer_login);
			preparedStatement.setString(1,emailid);
			preparedStatement.setInt(2, pin);
			ResultSet resultSet= preparedStatement.executeQuery();
			if (resultSet.next()) {
			CustomerDetails customerDetails = new CustomerDetails();
			customerDetails.setCustomername(resultSet.getString("customer_name"));
			customerDetails.setGender(resultSet.getString("gender"));
			customerDetails.setAccountnumber(resultSet.getInt("Account_Number"));
			customerDetails.setAmount(resultSet.getDouble("Amount"));
			return customerDetails;
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public boolean selectCustomerDetailsByUsingAccountNumber(int accountnumber) {
		// select * from customer_details where Account_Number=?
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(byusingacno);
			preparedStatement.setInt(1, accountnumber);
			ResultSet resultSet = preparedStatement.executeQuery();
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
	
	
	
	
	public boolean updateAmountByUsingAccountNumber(int accountnumber,double amount) {
// update customer_details set Amount =? where Account_Number=?
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(update_Amount);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, accountnumber);
			int result = preparedStatement.executeUpdate();
			if (result!=0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	public List<CustomerDetails>  selectAllCustomerDetailsByAccountNumberAndPinNull() {
		//select * from customer_details where Account_Number is null and pin is null
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement =connection.prepareStatement(by_null);
			ResultSet resultSet= preparedStatement.executeQuery();
			List<CustomerDetails> listofcustomerdetails = new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
			while(resultSet.next()) {
			CustomerDetails customerDetails = new CustomerDetails();
			
			customerDetails.setCustomername(resultSet.getString("customer_name"));
			customerDetails.setCustomeremailid(resultSet.getString("customer_emailid"));
			customerDetails.setAadharnumber(resultSet.getLong("aadhar_number"));
			customerDetails.setMobilenumber(resultSet.getLong("mobile_number"));
			customerDetails.setCustomeraddress(resultSet.getString("customer_address"));
			listofcustomerdetails.add(customerDetails);
			} 
			return listofcustomerdetails;
			}else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public boolean updateAccountNumberAndPinByUsingAadharNumber(int accountnumber,int pinnumber,long aadharnumber) {
		//Update customer_details  set account_number=?,pin=? where aadhar_number=?
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(update_account_and_pin);
			preparedStatement.setDouble(1, accountnumber);
			preparedStatement.setInt(2, pinnumber);
			preparedStatement.setLong(3, aadharnumber);
			int result = preparedStatement.executeUpdate();
			if (result!=0) {
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
