/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

/**
 *
 * @author EliteBook
 */

import java.io.*;
import java.util.*;
import java.lang.String;
import java.*;
import java.util.Date;
public class loan {
    
    public int loanid;
    public library lib;
    public int bookid;
    public int userid;
    public String issuedate;
    public String returndate;
    public String status;
   
    
    public loan()
    {
        
    }
  /*  public void addloan(int loanid,int bid,int uid,Date retd,library lib)
    {
        this.loanid=loanid;
        this.bookid=bid;
        this.userid=uid;
        this.returndate=retd;
        this.issuedate=new Date();
        this.status="checkedout";
        this.lib=lib;
        
    }*/
    
    public loan (int loanid,int bid,int uid,String issd,String retd,String stat)
    {
        this.loanid=loanid;
        bookid=bid;
        userid=uid;
        this.issuedate=issd;
        this.returndate=retd;
        status=stat;
        
    }
    
    public void addloan(int loid,int bid,int uid,String redate,String issdate,String stat,library lib)
    {
        this.loanid=loid;
        this.issuedate=issdate;
        this.userid=uid;
        this.status="checkedout";
        this.bookid=bid;
        this.returndate=redate;
        this.lib=lib;
        
        
        
    }
    
    
    
    public void setlib(library lib)
    {
        this.lib=lib;
        
    }
    
    public void checkinloan()
    {
        this.status="checkedin";
        
    }
    
    
    
    public void printloan()
    { 
      user person= lib.getuserbyid(userid);
      book book=lib.getbookbyid(bookid);
      
        
        System.out.println("loanid: "+loanid+" bookid: "+bookid+" book title: "+book.title+" book author: "+book.author+" borrower name: "+person.name+" issue date: "+issuedate+" returndate: "+returndate+" Status: "+status);
        
    }
    
    
    public void viewpersonalinfo()
    {  
        user person= lib.getuserbyid(userid);
        person.printinfo();
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
}
