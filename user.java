/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;
import java.io.*;
import java.util.*;
import java.lang.String;
import java.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static library.management.driver.getConstring;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
 import java.text.DateFormat;
import java.util.function.Predicate;

/**
 *
 * @author EliteBook
 */
public class user {
    
    public String name;
    public int id;
    public int pass;
    public library lib;
    public List <loan> loans;
    public List <book> books;
    String phone;
    String addr;
    String type; /// whether clerk or librarian or borrower
    String finestatus;
    
       
    //cons  
   public user (String name,int id,int pass,String ph,String addr,String pos,String finestat ){ ///books are redun
       this.pass=pass;
       this.name=name;
       this.id=id;
       this.phone=ph;
       // fine status 0 initially
       this.addr=addr;       
       this.type=pos;
       finestatus=finestat;
       
     
       
    }      
   
  public user()
  {
      
  }
   public void printinfo()
   {
       System.out.println("name: "+name+" id: "+id+" phone: "+phone+" address: "+addr+" fine status: "+finestatus);
       
   }
   
   public void printwithpass()         
   {
      System.out.println("name: "+name+" id: "+id+" pass: "+pass+" phone: "+phone+" address: "+addr+" fine status: "+finestatus+" type: "+type);
        
   }
   
   public void searchbytitle(String title)
   {
       for (book b:books)
       {
           if (b.title.equals(title))
           {
               b.printbook();
           }
       }
                  
   }
   
   public void searchbyauthor(String author)
   {
        for (book b:books)
       {
           if (b.author.equals(author))
           {
               b.printbook();///what to do
           }
       }        
       
   }
   public void searchbysubject(String sub)
   {
        for (book b:books)
       {
           if (b.subject.equals(sub))
           {
               b.printbook();///what to do
           }
       }
   }
   
   public void exactsearch (String title,String author,String sub)
   {
        for (book b:books)
       {
           if (b.author.equals(author) && b.title.equals(title)&& b.subject.equals(sub))
           {
               
                b.printbook();
                   
           }
       }    
      
   }
   
   public void addlobolib(List <loan> loans,List <book> books,library lib)
   {
    
       this.loans=loans;
       this.books=books;
       this.lib=lib;
       
       
   }
    
   public void placeonhold(int bookid,user user)
   {
           for (book b:books)
       {
           if (b.bookid==bookid)
           {
            
               if (b.status==0) /// if not avaiable
               {
               
                    b.addtoqueue(user); 
               }
               else
               {
                  System.out.println("book is available");
               }
 
           }
       }   
        
   }
   
   public void borrowedbooks()
   {
       
       for (loan l:loans)
       {
           
           l.printloan();
              
           
      }
    
   }
   
  /*public void allborrowerinfo()
   {
       
       for (loan l:loans)
       {
           
           l.viewpersonalinfo();
              
           
      }
    
   }*/
      
   
    
     public static String getConstring()   ///con string
    {
        
        return "jdbc:sqlserver://COMPLUS\\alibutt:1433;databaseName=lib;user=sa;password=alibutt";
    }
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    
    
    
    public boolean checkloanidexist(int loanid)
    {
     for (loan l:loans)
     {
         if (l.loanid==loanid)
         {
             return true;
         }
     }
     return false;
    }
    
    
    public void issueloan() throws Exception
    {
        
        Scanner myscan=new Scanner(System.in);
        System.out.println("Enter Borrower id : ");
        int boid=myscan.nextInt();
        if (lib.checkuserexist(boid))
        {
            if(lib.getuserbyid(boid).finestatus.equals("paid"))
            {
                
            
             System.out.println("Enter book id : ");
             int bookid=myscan.nextInt();
             if (lib.checkbookexist(bookid) )
             {
                 if (lib.checkbookstatus(bookid))
                 {
                     /// if book is available
                      
                       System.out.println("Enter loan id : ");
                       int loanid=myscan.nextInt();
                       
                       if (checkloanidexist(loanid)==true)
                       {
                                System.out.println("loan id already in existence: use another id ");  
                                System.out.println("Enter loan id : ");
                                loanid=myscan.nextInt();
                           while(checkloanidexist(loanid)==true)
                           {
                                System.out.println("loan id already in existence: use another id ");  
                                System.out.println("Enter loan id : ");
                                loanid=myscan.nextInt();
                               
                           }
                       } 
                           
                       
                       
                  /*     for (loan l:loans)
                       {
                           if (l.loanid==loanid)
                           {
                               System.out.println("loan id already in existence: use another id ");
                               
                                System.out.println("Enter loan id : ");
                                int loanid11=myscan.nextInt();   //// update it not lasting
                                
                           }
                       }
*/
                       
                       
                       System.out.println("Enter Date today : ");
                       String curdate=myscan.next();
                       
                       System.out.println("Enter Return date : "); 
                       String retdate=myscan.next();
                       String stat="checkedout";
                        loan newloan=new loan();
                        newloan.addloan(loanid,bookid,boid,retdate,curdate,stat,lib);
                        loans.add(newloan);
                      
                        Date issdate1=new SimpleDateFormat("yyyy/mm/dd").parse(curdate);
                        Date retdate1=new SimpleDateFormat("yyyy/mm/dd").parse(retdate);
                       
                        java.sql.Date sqlissdate = new java.sql.Date(issdate1.getTime()); 
                        java.sql.Date sqlretdate = new java.sql.Date(retdate1.getTime());            
                        
                        con=DriverManager.getConnection(getConstring());
                          
                        PreparedStatement psdtmt=con.prepareStatement("insert into loan (loanid,bid,pid,issdate,retdate,status1) VALUES (?,?,?,?,?,?)");
                        psdtmt.setInt(1,loanid );
                         psdtmt.setInt(2,bookid );
                          psdtmt.setInt(3,boid);
                           psdtmt.setDate(4,sqlissdate);
                           psdtmt.setDate(5,sqlretdate);//inserting in databse
                           psdtmt.setString(6,"checkedout");
                           
                           psdtmt.executeUpdate();
                           
                    
                    book tempbook=new book();
                    tempbook=lib.getbookbyid(bookid);
                    
                    tempbook.status=0; //not available
                    
                     PreparedStatement psdtmt1=con.prepareStatement("update book set status1="+0+"where bookid="+bookid);
                    
                     psdtmt1.executeUpdate();
                           
                     
                 }
                 else
                 {
                     System.out.println("Book is Currently on another loan");
                     /// put on hold logic
                 }
                 
                 
                 
             }
             else
             {
               System.out.println("Book is not available ");   
             }
        }
            else
            {
                System.out.println("fine pending"); 
            }
            
        }
        else
        {
            System.out.println("User doesnot exist");
        }
        
        
        
        System.out.println("Successful");
        
        
        
    }
    
    public loan getloanbyid(int lid)
    {
        for (loan l:loans)
        {
            if (l.loanid==lid)
            {
                return l;
            }
        }
        return null;
    }
    
    
    public void checkin(int bookid,int loanid) throws SQLException   ///// check if fine
    {
         book b=new book();   
         b=lib.getbookbyid(bookid);
         b.status=1;   ///making book available
         
          
        con=DriverManager.getConnection(getConstring());///updating in database
         PreparedStatement psdtmt1=con.prepareStatement("update book set status1="+1+"where bookid="+bookid);
          
           psdtmt1.executeUpdate();
          
           
           
           
          loan lo=new loan();
          lo=getloanbyid(loanid);
          
          lo.status="checkedin"; 
        PreparedStatement psdtmt2=con.prepareStatement("Update loan set status1= 'checkedin' where loanid="+loanid);
       
         psdtmt2.executeUpdate();
         
         
        
    }
    public void renew(int bookid,int loanid)  ///////////
    {
        
        
    }
  /*  public void addfine(int userid )
    {
        
        
    }
    
    public void removefine(int userid)
    {
        
    }
    */
   public boolean checkuseridexist(int id)
    {
        for (user u:lib.persons)
        {
            if (u.id==id)
            {
                return true;
            }
        }
        return false;
    }
    
    public void Signupuser() throws SQLException
    {
        Scanner myscan=new Scanner(System.in);
        System.out.println("Enter name of user");
        String name=myscan.next();
        System.out.println("Enter id of user");
        int id=myscan.nextInt();
        if (checkuseridexist(id)==true)
        {
            while(checkuseridexist(id)==true)
            {
                System.out.println("user id already in existence");
                  System.out.println("Enter id of user");
                   id=myscan.nextInt();
                
          }
            
        }
        
        
        System.out.println("Enter password of user");
        int pass=myscan.nextInt();
        
          System.out.println("Enter phone");
          String phn=myscan.next();
          
          System.out.println("Enter address");
          String addr=myscan.next();
          
          System.out.println("Enter position");
          String pos=myscan.next();
          
          
          
          user u=new user (name,id,pass,phn,addr,pos,"paid");
          u.addlobolib(loans,books, lib);   ////
          
          
          lib.persons.add(u);    ///cbw
          
          /// inserting in databse
          
            
                        con=DriverManager.getConnection(getConstring());
                          
                        PreparedStatement psdtmt=con.prepareStatement("insert into user1 (id,pass,name,phn,addr,pos,finestat) VALUES (?,?,?,?,?,?,?)");
                        psdtmt.setInt(1,id );
                         psdtmt.setInt(2,pass );
                          psdtmt.setString(3,name);
                           psdtmt.setString(4,phn);
                           psdtmt.setString(5,addr);//inserting in databse
                           psdtmt.setString(6,pos);
                           psdtmt.setString(7,"paid");
                           
                           psdtmt.executeUpdate();
          
          System.out.println("Successful");
     
          
        
    }
    
    public void updateuserinfo() throws SQLException
    {
          Scanner myscan=new Scanner(System.in);
         System.out.println("Enter id of user");
        int id=myscan.nextInt();
        if (checkuseridexist(id)==false)
        {
            while(checkuseridexist(id)==false)
            {
                System.out.println("user donot exist");
                  System.out.println("Enter id of user");
                   id=myscan.nextInt();
                
          }
            
        }
      
        System.out.println("Enter enter new name");
        String name=myscan.next();
       
        
        
        System.out.println("Enter new password");
        int pass=myscan.nextInt();
        
          System.out.println("Enter new phone");
          String phn=myscan.next();
          
          System.out.println("Enter new address");
          String addr=myscan.next();
          
          System.out.println("Enter new position");
          String pos=myscan.next();
          
          System.out.println("Enter fine status");
          String fs=myscan.next();
          
          
        for (user u :lib.persons)
        {
            if (u.id==id)
            {
             u.name=name;
             u.pass=pass;
             u.phone=phn;
             u.type=pos;
             u.addr=addr;
             u.finestatus=fs;
                
            }
           
            
        }
          
         
          
          /// inserting in databse
          
            
                        con=DriverManager.getConnection(getConstring());
                          
                        PreparedStatement psdtmt=con.prepareStatement("update user1 set name='"+name+"',pass="+pass+",phn='"+phn+"',addr='"+addr+"',pos='"+pos+"',finestat='"+fs+ "' where id="+id);
                      
                           psdtmt.executeUpdate();
          
                            System.out.println("Successful");
     
        
        
        
    }
    
     public boolean checkbookidexist(int bookid)
    {
     for (book b:books)
     {
         if (b.bookid==bookid)
         {
             return true;
         }
     }
     return false;
    }
    
      
     public void updatefinestatus() throws SQLException
     {
         
         Scanner myscan=new Scanner(System.in);
          System.out.println("Enter user id");
          int id=myscan.nextInt();
         
         System.out.println("Enter fine status");
         String fs=myscan.next();
         
         for (user u:lib.persons)
         {
             if (u.id==id)
             {
                 u.finestatus=fs;
             }
         }
         
         
         // inserting  in databse
         
         con=DriverManager.getConnection(getConstring());///updating in database
         PreparedStatement psdtmt1=con.prepareStatement("update user1 set finestat='"+fs+"'where id="+id);
          
           psdtmt1.executeUpdate();
         
         
         
         
         
         
     }
     
    
    public void addnewbook() throws SQLException
    {
        System.out.println("Enter book id");
        Scanner myscan=new Scanner(System.in);
        int id=myscan.nextInt();
        
       if (checkbookidexist(id)==true)
       {
           while(checkbookidexist(id)==true)
           { System.out.println("Book id already exist");
            System.out.println("Enter book id");
               id=myscan.nextInt();
            
           }
           
       }
       
       System.out.println("Enter book title");     
       String title=myscan.next();
       
       System.out.println("Enter book author");     
       String author=myscan.next();
       
       System.out.println("Enter book subject");     
       String sub=myscan.next();
       
       book b1=new book(id,author,sub,title,1);
       b1.addltob(loans);
                              ////we can also add to the books in user
       lib.books.add(b1);   /////
       
       ///inserting to database
       
        con=DriverManager.getConnection(getConstring());
                          
        PreparedStatement psdtmt=con.prepareStatement("insert into book (bookid,title,author,subject,status1) VALUES (?,?,?,?,?)");
                         psdtmt.setInt(1,id);
                         psdtmt.setString(2,title);
                          psdtmt.setString(3,author);
                           psdtmt.setString(4,sub);
                           psdtmt.setInt(5,1);//inserting in databse
                          
                           
                           psdtmt.executeUpdate();
          
          System.out.println("Successful");
     
      
        
    }
    
        public void deletebook() throws SQLException
     {
        System.out.println("Enter book id");
        Scanner myscan=new Scanner(System.in);
        int id=myscan.nextInt();
        
       if (checkbookidexist(id)==false)
        {
          
            while(checkbookidexist(id)==false)
           { 
            System.out.println("Book id doesnot exist");
            System.out.println("Enter book id");
               id=myscan.nextInt();
            
           }
           
         }
       final int bid=id;
      // Predicate <book> bookpred=b->b.bookid==id;
       lib.books.removeIf(b->b.bookid==bid);
  
       
       ///inserting to database
       
            
        con=DriverManager.getConnection(getConstring());///updating in database
         PreparedStatement psdtmt1=con.prepareStatement("delete from book where bookid="+id);
          
           psdtmt1.executeUpdate();
       
       
       
               System.out.println("Successful");
                 
          }
        
        
        public void updatebook() throws SQLException
        {
           
        System.out.println("Enter book id");
        Scanner myscan=new Scanner(System.in);
        int id=myscan.nextInt();
        
       if (checkbookidexist(id)==false)
       {
           while(checkbookidexist(id)==false)
           { System.out.println("Book id doesnot exist");
            System.out.println("Enter book id");
               id=myscan.nextInt();
            
           }
           
       }
       
       System.out.println("Enter new title");     
       String title=myscan.next();
       
       System.out.println("Enter new author");     
       String author=myscan.next();
       
       System.out.println("Enter new subject");     
       String sub=myscan.next();
       
       System.out.println("Enter new status (0 for not available(vice verca))");     
       int stat=myscan.nextInt();
       
       
       for (book b:lib.books)
       {
           if (b.bookid==id)
           {
               b.author=author;
               b.title=title;
               b.subject=sub;
               b.status=stat;
           }
       }
            
        
       /////updating to database
       
       
                        con=DriverManager.getConnection(getConstring());
                          
                        PreparedStatement psdtmt=con.prepareStatement("update book set bookid="+id+",title='"+title+"',author='"+author+"',subject='"+sub+"',status1="+stat+" where bookid="+id);
                      
                           psdtmt.executeUpdate();
          
                            System.out.println("Successful");
       
       
       
            
            
            
        }
        
        
        
     
    
}
