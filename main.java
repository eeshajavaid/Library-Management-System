/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static library.management.driver.addlib;
import static library.management.driver.addlobolib1;
import static library.management.driver.addltob;
import static library.management.driver.fetchbooks;
import static library.management.driver.fetchloan;
import static library.management.driver.fetchuser;
/**
 *
 * @author EliteBook
 */
public class main {
    
      public static String getConstring()   ///con string
    {
        
        return "jdbc:sqlserver://COMPLUS\\alibutt:1433;databaseName=lib;user=sa;password=alibutt";
    }
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
   
    public static List <book> fetchbooks() throws SQLException   /// fetching books from database
    {
        
        con=DriverManager.getConnection(getConstring());
        st=con.createStatement();
        rs=st.executeQuery("select * from book");
        ArrayList book=new ArrayList <book> ();
        
        while (rs.next())
        {  
           int id=rs.getInt("bookid");
           String title=rs.getString("title");
           String author=rs.getString("author");
           String subject=rs.getString("subject");
           int status=rs.getInt("status1");
           book.add(new book(id,author,subject,title,status));
           
        }
        
        return book;
        
    }
    
      public static List <user> fetchuser() throws SQLException   /// fetching books from database
    {
        
        con=DriverManager.getConnection(getConstring());
        st=con.createStatement();
        rs=st.executeQuery("select * from user1");
        ArrayList user=new ArrayList <user> ();
        
        while (rs.next())
        {  
           int id=rs.getInt("id");
           int pass=rs.getInt("pass");
           String name=rs.getString("name");
           String phn=rs.getString("phn");
           String addr=rs.getString("addr");
           String pos=rs.getString("pos");
           String fs=rs.getString("finestat");
           user.add(new user(name,id,pass,phn,addr,pos,fs));
           
        }
        
        return user;
        
    }
    public static List <loan> fetchloan() throws SQLException
    {
          con=DriverManager.getConnection(getConstring());
        st=con.createStatement();
        rs=st.executeQuery("select * from loan");
         ArrayList loan=new ArrayList <loan> ();
        
        while (rs.next())
        {  
           int lid=rs.getInt("loanid");
           int pid=rs.getInt("pid");
           int bid=rs.getInt("bid");
           String issdate=rs.getString("issdate");
           String retdate=rs.getString("retdate");
           String status=rs.getString("status1");
           loan.add(new loan(lid,bid,pid,issdate,retdate,status));
            
        }
        
        return loan;  
        
    }
    
    public static void addlib(List <loan> loans,library lib)
    {
        for (loan l:loans)
        {
            l.setlib(lib);     
            
        }
        
    }
    
      public static void addlobolib1(List <user> users,List <loan> loans,List <book> books,library lib)
      {
          for (user u:users)
          {
              u.addlobolib(loans, books, lib);
          }
          
      }
   
      public static void addltob(List <book> books,List <loan> loans)
      {
          for (book b:books)
          {
              b.addltob(loans);
          }
      }
    
    
    public static void main(String args []) throws SQLException
    {
        
         /// fetchbooks from database
        
    /*        List <book> books=new ArrayList <book> ();
            books=fetchbooks();
             List <user> users=new ArrayList <user> ();
             users=fetchuser();
            library lib=new library(users,books);
             lib.printlibrary();
            List <loan> loans=new ArrayList <loan>(); 
            loans=fetchloan();
            addlib(loans,lib);   //adding lib to loan
                    
             addlobolib1(users,loans,books,lib); /// adding loans books and lib to user
             addltob(books,loans); // adding loans to book
             
        
        
        
        
        
    loginform login=new loginform();
    login.setVisible(true);
*/
     //   searchpanel sp=new  searchpanel();
      //  sp.setVisible(true);
    
        loginform login=new loginform();
        login.setVisible(true);
    
    
    }
    
    
}
