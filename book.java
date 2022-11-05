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
import java.util.Date;
import java.util.PriorityQueue;
/**
 *
 * @author EliteBook
 */
public class book {
    
    public int bookid;
    public String author;
    public String subject;
    public String title;
    public int status;   //1 for available and 0 for not available
    public List <loan> loans;
    public Queue <user> user;
    
    public book()
    {
        
    }
    public book (int bookid,String author,String subject,String title,int status)
    {
        this.bookid=bookid;
        this.author=author;
        this.subject=subject;
        this.title=title;
        this.status=status; /// initially available
        loans=null;
        user=new PriorityQueue();
             
    }
   
    public void addltob(List <loan> loans)
    {
        this.loans=loans;
    }
    
    
    
    
    public void printbook()
    {
        
        System.out.println("book id: "+bookid+" Title: "+title+" subject: "+subject+" author: "+author+" status: "+status);
    }
    
    public void addtoqueue(user u)
    {
        user.add(u);
        
    }
    
    public void removefromqueue()
    {
        user.remove();
    }
    
    
    
    
    
    
}
