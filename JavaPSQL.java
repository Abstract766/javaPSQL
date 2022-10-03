/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javapsql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class JavaPSQL {
    public static String queryTask(){
        String query = "select * from person;";
        return query;
    }
    public static String deleteTask(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Which row to delete?");
        String deleteRowID = sc2.nextLine();
        String delete = "DELETE FROM person WHERE id = "+ deleteRowID +";";
        System.out.println(deleteRowID +". row deleted");
        return delete;
    }
    
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
	Statement stmt = null;
        String userName = "postgres";
        String password = "0000";
        String port = "5432";
        String databaseName = "test";
	
	try {
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:"+port+"/"+databaseName,userName,password);
		
		if (connection!=null) {
			System.out.println("Connection OK");
			stmt = connection.createStatement();
                        
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter number");
                        String number = sc.nextLine();
                        String result = "";
                        if(Integer.parseInt(number) == 1){result = queryTask();}
                        if(Integer.parseInt(number) == 2){
                            Scanner sc3 = new Scanner(System.in);
                            System.out.println("Add a new row.");
                            System.out.println("First name:");
                            String firstName = sc3.next();
                            System.out.println("Last name:");
                            String lasttName = sc3.next();
                            System.out.println("Gender:");
                            String gender = sc3.next();
                            System.out.println("Date of birth:");
                            String dateOfBirth = sc3.next();
                            System.out.println("Email");
                            String email = sc3.next();
                            java.sql.Date sqlDate = java.sql.Date.valueOf(dateOfBirth);
                            if (connection!=null) {
                                System.out.println("Connection OK");

                                String query = "insert into person(first_name, last_name, gender, date_of_birth,email) values(?,?,?,?,?)";
                                ps = connection.prepareStatement(query);
                                ps.setString(1, firstName);
                                ps.setString(2, lasttName);
                                ps.setString(3, gender);
                                ps.setDate(4, sqlDate);
                                ps.setString(5, email);
                                ps.executeUpdate();
                                System.out.println("Record is inserted.");
                            }
                        }
                        if(Integer.parseInt(number) == 3){result = deleteTask();}

                        
			 ResultSet rs = stmt.executeQuery(result);
			 while (rs.next()) {
                                if(Integer.parseInt(number) == 1){
                                    int id = rs.getInt("id");
                                    String  firstName = rs.getString("first_name");
                                    String  lastName = rs.getString("last_name");
                                    String  gender = rs.getString("gender");
                                    String  dateOfBirth = rs.getString("date_of_birth");
                                    String  email = rs.getString("email");
                                    System.out.println( "ID = " + id );
                                    System.out.println( "Firstname = " + firstName );
                                    System.out.println( "LastName = " + lastName );
                                    System.out.println( "Gender = " + gender );
                                    System.out.println( "Date of birth = " + dateOfBirth );
                                    System.out.println( "Email = " + email );
                                    System.out.println();
                                }
			}
		}
		else {
			System.out.println("Connection failed.");
		}
	} catch (Exception e) {
		System.out.println(e);
	}
    }
}
