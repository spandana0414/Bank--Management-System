package com.bank.dto;
import java.time.LocalDate;
import java.time.LocalTime;
public class TransactionDetailsDTO {
	/*
	 * Transaction_Id, Transaction_Date, Transaction_Time, Transaction_Amount,
	 * Balance_Amount, Customer_account_number, R_account_number, Transaction_Type
	 */
	private int transactionid;
	private LocalDate transactiondate;
	private LocalTime transactiontime;
	private double transactionamount;
	private double balanceamount;
	private int accountnumber; 
	private int raccountnumber;

	private String transactiontype;

	public TransactionDetailsDTO() {
		// TODO Auto-generated constructor stub
	}

	public TransactionDetailsDTO(int transactionid, LocalDate transactiondate, LocalTime transactiontime,
			double transactionamount, double balanceamount, int accountnumber, int raccountnumber,
			String transactiontype) {
		super();
		this.transactionid = transactionid;
		this.transactiondate = transactiondate;
		this.transactiontime = transactiontime;
		this.transactionamount = transactionamount;
		this.balanceamount = balanceamount;
		this.accountnumber = accountnumber;
		this.raccountnumber = raccountnumber;
		this.transactiontype = transactiontype;
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public LocalDate getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}

	public LocalTime getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(LocalTime transactiontime) {
		this.transactiontime = transactiontime;
	}

	public double getTransactionamount() {
		return transactionamount;
	}

	public void setTransactionamount(double transactionamount) {
		this.transactionamount = transactionamount;
	}

	public double getBalanceamount() {
		return balanceamount;
	}

	public void setBalanceamount(double balanceamount) {
		this.balanceamount = balanceamount;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getRaccountnumber() {
		return raccountnumber;
	}

	public void setRaccountnumber(int raccountnumber) {
		this.raccountnumber = raccountnumber;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	@Override
	public String toString() {
		return "TransactionDetailsDTO [transactionid=" + transactionid + ", transactiondate=" + transactiondate
				+ ", transactiontime=" + transactiontime + ", transactionamount=" + transactionamount
				+ ", balanceamount=" + balanceamount + ", accountnumber=" + accountnumber + ", raccountnumber="
				+ raccountnumber + ", transactiontype=" + transactiontype + "]";
	}

}
