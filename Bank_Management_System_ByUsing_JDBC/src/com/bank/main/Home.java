package com.bank.main;
import java.util.Scanner;

import com.bank.exception.CustomerInvalidDataException;
import com.bank.service.AdminService;
import com.bank.service.CustomerService;
public class Home {
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		Scanner scanner = new Scanner(System.in);
		AdminService adminService = new AdminService();
		while (true) {
			System.out.println("Enter "
					+ "\n 1. For Customer Registration "
					+ "\n 2. For Customer Login"
					+ "\n 3. For Admin Login");
	switch (scanner.nextInt()) {
	case 1:
		String a1 ="Customer Registration";
		for (int i = 0; i <a1.length(); i++) {
			System.out.print(a1.charAt(i));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		System.out.println();
		
		customerService.customerRegistration();
		
		break;
	case 2:
//		String a2 ="Customer Login";
//		for (int i = 0; i <a2.length(); i++) {
//			
//			System.out.print(a2.charAt(i));
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		System.out.println();
		customerService.customerLogin();
		break;
	case 3:
		System.out.println("Admin Login");
		adminService.adminLogin();
		break;
	default:
		System.out.println("Invalid Request");
		break;
	}
	System.out.println("Do you want to continue Enter \n 1 For YES \n 2 For NO");
	
	if (scanner.nextInt()!=1) {
		
		System.out.println("*******------------*******Thank You*******------------*******");
		break;	
	} 
		}
	

	
	}

}
