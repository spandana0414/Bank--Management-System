package com.bank.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.bank.dao.CustomerDAO;
import com.bank.dao.TransactionDAO;
import com.bank.dto.CustomerDetails;
import com.bank.dto.TransactionDetailsDTO;
import com.bank.exception.CustomerInvalidDataException;

public class CustomerService {

	Scanner scanner = new Scanner(System.in);
	CustomerDAO customerDAO = new CustomerDAO();
	CustomerDetails customerDetails;
	TransactionDAO transactionDAO = new TransactionDAO();
	public void customerRegistration() {

		System.out.println("Enter Customer Name");
		String name = scanner.next();
		System.out.println("Enter Customer Emailid ");
		String emailid = scanner.next();
		if (emailid.contains("@gmail.com")) {
		} else {
			throw new CustomerInvalidDataException("Invalid Emailid");
		}

		System.out.println("Enter Customer Aadhar Number");
		long anumber = scanner.nextLong();
		if (anumber >= 100000000000l && anumber <= 999999999999l) {
		} else {
			throw new CustomerInvalidDataException("Invalid Aadhar Number");
		}
		System.out.println("Enter Customer Mobile Number");
		long mbnumber = scanner.nextLong();
		if (mbnumber >= 6000000000l && mbnumber <= 9999999999l) {
		} else {
			throw new CustomerInvalidDataException("Invalid Mobile Number");
		}
		System.out.println("Enter Customer PAN Number");
		String pannumber = scanner.next();
		System.out.println("Enter Customer Address");
		String address = scanner.next();
		System.out.println("Enter Customer Gender");
		String gender = scanner.next();
		System.out.println("Enter Date Of Birth");
		String dob = scanner.next();
		System.out.println("Enter Customer AGE");
		int age = scanner.nextInt();
		System.out.println("Enter Amount");
		double amount = scanner.nextDouble();

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomername(name);
		customerDetails.setCustomeremailid(emailid);
		customerDetails.setAadharnumber(anumber);
		customerDetails.setAmount(amount);
		customerDetails.setCustomeraddress(address);
		customerDetails.setCustomerage(age);
		customerDetails.setDateofbirth(Date.valueOf(dob));
		customerDetails.setGender(gender);
		customerDetails.setMobilenumber(mbnumber);
		customerDetails.setPannumber(pannumber);

		customerDAO.insertCustomerDetails(customerDetails);
	}

	public void customerLogin() {
		System.out.println("Enter Customer Emailid");
		String emailid = scanner.next();
		System.out.println("Enter Customer PIN");
		int pin = scanner.nextInt();

		 customerDetails = customerDAO.selectCustomerDetailsByUsingEmailidAndPin(emailid, pin);
		if (customerDetails != null) {
			if (customerDetails.getGender().equalsIgnoreCase("Male")) {
				System.out.println("Heloo MR :-" + customerDetails.getCustomername());
				customerOperations();
			}
			if (customerDetails.getGender().equalsIgnoreCase("Female")) {
				System.out.println("Heloo Miss :-" + customerDetails.getCustomername());
				customerOperations();
			}
		} else {
			System.out.println("Invalid Emailid And PIN");
		}
	}
	public void customerOperations() {
		System.out.println(
"Enter \n 1. For Credit" + " \n 2. For Debit  3. For Check Statement \n 4. For Change PIN ");
	   switch (scanner.nextInt()) {
	case 1:
		System.out.println("Credit");
		break;
	case 2:
		System.out.println("Debit");
		customerDebit();
		break;
	case 3:
		System.out.println("Check Statement");
		break;
	case 4:
		System.out.println("Change PIN");
		break;
	default:
		System.out.println("Invalid Request");
		break;
	}
	
	}
	
	public void customerDebit() {
		System.out.println("Enter Account Number");
		int accountnumber = scanner.nextInt();
		int databaseaccountNumber = customerDetails.getAccountnumber();
if (customerDAO.selectCustomerDetailsByUsingAccountNumber(accountnumber)&&accountnumber==databaseaccountNumber) {
			System.out.println("Enter Amount");
			double amount = scanner.nextDouble();
			double databaseamount = customerDetails.getAmount();
			if (amount<=databaseamount) {

				double balanceamount = databaseamount-amount;
				if(customerDAO.updateAmountByUsingAccountNumber(databaseaccountNumber, balanceamount))
				{
					System.out.println("Amount Debited");
					TransactionDetailsDTO transactionDetailsDTO = new TransactionDetailsDTO();
					transactionDetailsDTO.setAccountnumber(accountnumber);
					transactionDetailsDTO.setRaccountnumber(accountnumber);
					transactionDetailsDTO.setTransactionamount(amount);
					transactionDetailsDTO.setBalanceamount(balanceamount);
					transactionDetailsDTO.setTransactiondate(LocalDate.now());
					transactionDetailsDTO.setTransactiontime(LocalTime.now());
					transactionDetailsDTO.setTransactiontype("Debit");
					transactionDAO.insertTransactionDetails(transactionDetailsDTO);
				}
				else {
					System.out.println("500 error");
				}
			} else {
				System.out.println("In-Sufficient Amount");
			}
		} else {
			System.out.println("Invalid Account Number");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
