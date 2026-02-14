package com.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import com.bank.dto.TransactionDetailsDTO;

public class TransactionDAO {

	private final static String insert = "insert into transaction_details (Transaction_Date, Transaction_Time, Transaction_Amount, Balance_Amount,"
			+ " Customer_account_number, R_account_number, Transaction_Type) values(?,?,?,?,?,?,?)";
	private final static String url = "jdbc:mysql://localhost:3306/a4_bank_management_system_db?user=root&password=root";

	public boolean insertTransactionDetails(TransactionDetailsDTO transactionDetailsDTO) {

//insert into transaction_details (Transaction_Date, Transaction_Time, Transaction_Amount, Balance_Amount, Customer_account_number, R_account_number, Transaction_Type) values(?,?,?,?,?,?,?)
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setDate(1, Date.valueOf(transactionDetailsDTO.getTransactiondate()));
			preparedStatement.setTime(2, Time.valueOf(transactionDetailsDTO.getTransactiontime()));
			preparedStatement.setDouble(3, transactionDetailsDTO.getTransactionamount());
			preparedStatement.setDouble(4, transactionDetailsDTO.getBalanceamount());
			preparedStatement.setInt(5, transactionDetailsDTO.getAccountnumber());
			preparedStatement.setInt(6, transactionDetailsDTO.getRaccountnumber());
			preparedStatement.setString(7, transactionDetailsDTO.getTransactiontype());
			int result = preparedStatement.executeUpdate();
			if (result != 0) {

				return true;

			} else {
				return false;

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
