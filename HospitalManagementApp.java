package org.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HospitalManagementApp {
	public static void main(String[] args) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("choose choice");
			System.out.println("1) Register a new patient by accepting all the details");
			System.out.println("2) update the disease information by Id");
			System.out.println("3) update the age by phone number");
			System.out.println("4) view all patient records");
			System.out.println("5) search patient by phone number");
			System.out.println("6) Search patients by Disease");
			System.out.println("7) Search patient by Name");
			System.out.println("8) Delete a patient records by phone number and name");
			System.out.println("9) Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				insertRecord();
				break;
			case 2:
				updateDiseaseById();
				break;
			case 3:
				updateAgeByPhNo();
				break;
			case 4:
				viewAllPatientRecords();
				break;
			case 5:
				viewPatientByPhoneno();
				break;
			case 6:
				searchPatientByDisease();
				break;
			case 7:
				searchPatientByName();
				break;
			case 8:
				deletepatientRecordByPhonenoAndName();
				break;
			case 9:
				System.out.println("exit.............");
				break;
			default:
				System.out.println();
				break;
			}
		}
	}

	public static void insertRecord()

	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("insert into patient values (?,?,?,?,?,?)");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Id : ");
			int id = sc.nextInt();
			System.out.println("Enter Name:");
			String name = sc.next();
			System.out.println("Enter Age:");
			int age = sc.nextInt();
			System.out.println("Enter Phoneno:");
			long phoneno = sc.nextLong();
			System.out.println("Enter Disease:");
			String disease = sc.next();
			System.out.println("Enter Admisiion_Date:");
			String admition_date = sc.next();

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setLong(4, phoneno);
			ps.setString(5, disease);
			ps.setString(6, admition_date);

			int row = ps.executeUpdate();
			System.out.println(row + "row affected......");
			ps.close();
			con.close();

		}
		catch (ClassNotFoundException | SQLException e) 
		{

			e.printStackTrace();
		}

	}

	public static void updateDiseaseById() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("update patient set disease=? where id=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter updated disease");
			String disease = sc.next();
			System.out.println("enter id:");
			int id = sc.nextInt();
			ps.setString(1, disease);
			ps.setInt(2, id);
			int row = ps.executeUpdate();
			System.out.println(row + "row affected...");
			ps.close();
			con.close();
			System.out.println("done");

		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();

		}

	}

	public static void updateAgeByPhNo() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("update patient set age=? where phoneno=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter updated age");
			int age = sc.nextInt();
			System.out.println("enter phoneno:");
			long phoneno = sc.nextLong();
			ps.setInt(1, age);
			ps.setLong(2, phoneno);
			int row = ps.executeUpdate();
			System.out.println(row + "row affected...");
			ps.close();
			con.close();
			System.out.println("done");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
	}

	
	public static void viewAllPatientRecords()

	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			Statement s = con.createStatement();
			boolean b = s.execute("select * from patient");
			if (b == true) 
			{
				ResultSet rs = s.getResultSet();

				while (rs.next())
				{
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getInt(3) + "\t");
					System.out.print(rs.getLong(4) + "\t");
					System.out.print(rs.getString(5) + " \t");
					System.out.println(rs.getString(6) + "\t");
				}
				rs.close();
			}
			s.close();
			con.close();
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	public static void searchPatientByDisease() 
{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("select * from patient where disease=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter disease");
			ps.setString(1,sc.next());
            ResultSet rs= ps.executeQuery();
             while(rs.next())
             {
            	 System.out.print(rs.getInt(1)+ "\t");
            	 System.out.print(rs.getString(2)+ "\t");
            	 System.out.print(rs.getInt(3)+ "\t");
            	 System.out.print(rs.getLong(4)+ "\t");
            	 System.out.print(rs.getString(5)+ "\t");
            	 System.out.println(rs.getString(6)+ "\t");
            	 
             }
            rs.close();
			ps.close();
			con.close();
			System.out.println("done");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

	}

	public static void viewPatientByPhoneno() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("select * from patient where phoneno=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter phoneno");
			ps.setLong(1,sc.nextLong());
       
			 ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {

            	 System.out.print(rs.getInt(1)+ "\t");
            	 System.out.print(rs.getString(2)+ "\t");
            	 System.out.print(rs.getInt(3)+ "\t");
            	 System.out.print(rs.getLong(4)+ "\t");
            	 System.out.print(rs.getString(5)+ "\t");
            	 System.out.println(rs.getString(6)+ "\t"); 
			 }
			 rs.close();
			ps.close();
			con.close();
			System.out.println("done");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

	}

	 public static void searchPatientByName()
 {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("select * from patient where name=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter name");
			ps.setString(1,sc.next());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
           	 System.out.print(rs.getInt(1)+ "\t");
           	 System.out.print(rs.getString(2)+ "\t");
           	 System.out.print(rs.getInt(3)+ "\t");
           	 System.out.print(rs.getLong(4)+ "\t");
           	 System.out.print(rs.getString(5)+ "\t");
           	 System.out.println(rs.getString(6)+ "\t");
           	 

			}
			rs.close();
			ps.close();
			con.close();
			System.out.println("done");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}

	}

	
	 public static void deletepatientRecordByPhonenoAndName() 
{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Root");
			PreparedStatement ps = con.prepareStatement("delete from patient  where phoneno=? and name=?");
			Scanner sc = new Scanner(System.in);
			System.out.println("enter phone no: ");
			Long phoneno = sc.nextLong();
			System.out.println("enter name :");
			String name = sc.next();
			ps.setLong(1, phoneno);
			ps.setString(2, name);
			int row = ps.executeUpdate();
			System.out.println(row + "row affected...");
			ps.close();
			con.close();
			System.out.println("done");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
	}
}
