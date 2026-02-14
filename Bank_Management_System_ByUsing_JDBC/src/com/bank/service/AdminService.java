package com.bank.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import com.bank.dao.AdminDAO;
import com.bank.dao.CustomerDAO;
import com.bank.dto.CustomerDetails;

public class AdminService {
	Scanner scanner = new Scanner(System.in);
	AdminDAO adminDAO = new AdminDAO();
	CustomerDAO customerDAO = new CustomerDAO();

	public void adminLogin() {
		System.out.println("Enter Admin Emailid");
		String emailid = scanner.next();
		System.out.println("Enter Admin Password");
		String password = scanner.next();
		if (adminDAO.selectAdminDetailsByUsingEmailidAndPassword(emailid, password)) {
			System.out.println("Enter \n 1. For Accept Account Request \n 2. For Reject Account Request");
			switch (scanner.nextInt()) {
			case 1:
				System.out.println("Accept Account Request");
				acceptAccountRequest();
				break;
			case 2:
				System.out.println("Reject Account Request");
				break;
			default:
				System.out.println("Invalid Request");
				break;
			}
		}
	}

	public void acceptAccountRequest() {
		List<CustomerDetails> list = customerDAO.selectAllCustomerDetailsByAccountNumberAndPinNull();
		if (list.size() != 0) {
			int i = 1;
			for (CustomerDetails customerDetails : list) {
				System.out.println("S.No :" + i++);
				System.out.println("Customer Name :" + customerDetails.getCustomername());
				System.out.println("Customer Emailid :" + customerDetails.getCustomeremailid());
				System.out.println("Customer Mobile Number :" + customerDetails.getMobilenumber());
				System.out.println("Customer Aadhar Number :" + customerDetails.getAadharnumber());
				System.out.println("-*****---*****----******");
			}
		} else {
			System.out.println("No Data Found");
		}
		System.out.println("Enter S.no To Accept The Account Request");
		CustomerDetails customerDetails = list.get(scanner.nextInt() - 1);
		System.out.println(customerDetails);
		Random random = new Random();
		int accountnumber = random.nextInt(9999999);
		if (accountnumber<1000000) {
			accountnumber+=1000000;
		}
		int pin = random.nextInt(9999);
		if (pin<1000) {
			pin+=1000;
		}
		customerDAO.updateAccountNumberAndPinByUsingAadharNumber(accountnumber,pin,customerDetails.getAadharnumber());
	}

}
