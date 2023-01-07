
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Complaint_Management_System {

    static Scanner sc = new Scanner(System.in);

    public static void adminLogin() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating a connection
        String url = "jdbc:mysql://localhost:3306/Project";
        String username = "root";
        String password = "akshay";

        Connection con = DriverManager.getConnection(url, username, password);
        String q = "Select * from admin where username =?";
        java.sql.PreparedStatement pstmt = con.prepareStatement(q);
        String adm_username, adm_password;
        System.out.println("Enter Admin username:");
        adm_username = sc.nextLine();
        System.out.println("Enter Admin Password:");
        adm_password = sc.nextLine();
        pstmt.setString(1, adm_username);
        ResultSet result = pstmt.executeQuery();
        String check = null;
        while (result.next()) {
            check = result.getString(2);
        }

        if (Objects.equals(check, adm_password)){
            System.out.println("****Welcome to the Admin Section****");
            while(true){
                System.out.println("1.View all Compaints");
                System.out.println("2.Change status to completed");
                System.out.println("3.Delete a complaint");
                System.out.println("4.Exit");

                System.out.println("Enter your choice:");
                int choice =sc.nextInt();
                switch (choice){
                    case 1:
                        String s = "Select * from user";
                        Statement stmt=con.createStatement();
                        ResultSet r= stmt.executeQuery(s);
                        while(r.next()){
                            System.out.println("************************");
                            System.out.print("Complaint id:"+r.getInt(1)+" ");
                            System.out.print("Name:"+r.getString(2)+" ");
                            System.out.print("Phone No."+r.getString(3)+" ");
                            System.out.print("City:"+r.getString(4)+" ");
                            System.out.print("Subject:"+r.getString(5)+" ");
                            System.out.println("Status:"+r.getString(7)+" ");

                        }
                        break;

                    case 2:
                        System.out.println("Enter Complain Id:");
                        int input= sc.nextInt();
                        String a = "Update user set status='Completed' where Complain_id=?";
                        java.sql.PreparedStatement stmt1 = con.prepareStatement(a);
                        stmt1.setInt(1,input);
                        stmt1.executeUpdate();
                        System.out.println("Successfully Changed the status...");
                        break;

                    case 3:
                        System.out.println("Enter Complain Id:");
                        input=sc.nextInt();
                        String b="delete from user where complain_id=?";
                        java.sql.PreparedStatement stmt2 = con.prepareStatement(b);
                        stmt2.setInt(1,input);
                        stmt2.executeUpdate();
                        System.out.println("Deleted Successfully....");
                        break;

                    case 4:

                        return;

                }
            }

            }

        else{
            System.out.println("Incorrect Login");
            return;
        }

    }

    public static void userMenu() throws ClassNotFoundException, SQLException {
        while(true){
            System.out.println("1.New Complaint Registration");
            System.out.println("2.View Status");
            System.out.println("3.Exit");
            int input=sc.nextInt();
            switch (input){
                case 1 :
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    //creating a connection
                    String url = "jdbc:mysql://localhost:3306/Project";
                    String username = "root";
                    String password = "akshay";

                    Connection con = DriverManager.getConnection(url, username, password);

                    String q="Insert into user(Name,Phone_No,City,Subject,Description) values(?,?,?,?,?)";

                    java.sql.PreparedStatement pstmt = con.prepareStatement(q);

                    String user_Name,Phone_No,City,Subject,Description;
                    Scanner scanner =new Scanner(System.in);

                    System.out.println("Enter your Name:");
                    user_Name= scanner.nextLine();
                    System.out.println("Enter your Phone_No");
                    Phone_No=scanner.nextLine();
                    System.out.println("Enter your City:");
                    City= scanner.nextLine();
                    System.out.println("Enter your Complaint Subject:");
                    Subject= scanner.nextLine();
                    System.out.println("Enter Complaint Description:");
                    Description=scanner.nextLine();

                    pstmt.setString(1,user_Name);
                    pstmt.setString(2,Phone_No);
                    pstmt.setString(3,City);
                    pstmt.setString(4,Subject);
                    pstmt.setString(5,Description);

                    pstmt.executeUpdate();

                    String p="Select LAST_INSERT_ID() ";
                    Statement stmt=con.createStatement();
                    ResultSet id=stmt.executeQuery(p);
                    String c_id = null;
                    while (id.next()) {
                        c_id = id.getString(1);
                    }

                    System.out.println("Your Complaint id no. is "+c_id);
                    break;

                case 2:
                    System.out.println("Enter your Complaint Id:");
                    int com_id=sc.nextInt();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    //creating a connection
                    String url1 = "jdbc:mysql://localhost:3306/Project";
                    String username1 = "root";
                    String password1 = "akshay";

                    Connection con1 = DriverManager.getConnection(url1, username1, password1);

                    String r="Select * from user where Complain_Id = ?";
                    java.sql.PreparedStatement pstmt1 = con1.prepareStatement(r);
                    pstmt1.setInt(1,com_id);
                    ResultSet result=pstmt1.executeQuery();

                     while(result.next()){
                         System.out.println("Complaint id:"+result.getInt(1));
                         System.out.println("Name:"+result.getString(2));
                         System.out.println("Phone No."+result.getString(3));
                         System.out.println("City:"+result.getString(4));
                         System.out.println("Subject:"+result.getString(5));
                         System.out.println("Status:"+result.getString(7));
                         System.out.println("*****************************************");

                     }
                     break;

                case 3:
                    return;





            }



        }

    }


    public static void main(String[] args) {

        System.out.println("*******Welcome to Complaint Management System*****");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creating a connection
            String url="jdbc:mysql://localhost:3306/Project";
            String username="root";
            String password="akshay";

            Connection con = DriverManager.getConnection(url,username,password);

            while(true){

                System.out.println("1.Admin");
                System.out.println("2.User");
                System.out.println("3.Exit");
                Scanner sc = new Scanner(System.in);
                int input=sc.nextInt();
                switch (input){
                    case 1:
                        adminLogin();
                        break;

                    case 2:
                        userMenu();
                        break;

                    case 3:
                        System.out.println("Thankyou for using Complaint Management System.......");
                        return;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
