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

/**
 *
 * @author EliteBook
 */
public class library {
    public List <user> persons;
    public List <book> books;
    
    
    public library(List <user> per,List <book> books)
    {
        persons=per;
        this.books=books;
        
    }
    public user getuserbyid(int id)
    {
        for (user u:persons)
        {
            if (u.id==id)
            {
                return u;
                
            }
        }
        return null;
        
    }
     public book getbookbyid(int id)
    {
        for (book b:books)
        {
            if (b.bookid==id)
            {
                return b;
                
            }
        }
        return null;
        
    }
     
     public boolean checkuserexist(int uid)
     {
         for (user u:persons)
         {
             
             if (u.id==uid)
             {
                 return true;
             }
             
         }
         return false;
     }
    
     
     public boolean checkbookexist(int bid)
     {
         for (book b:books)
         {
             if (b.bookid==bid)
             {
                 return true;
             }
         }
         return false;
     }
    
    public boolean checkbookstatus(int bookid)
    {
        for (book b:books)
        {
            if (b.bookid==bookid)
            {
             if (b.status==1)
             {
                 return true;
             }
             
                
            }
        }
        return false;
    }
    
    
    public void printlibrary()
    {
        System.out.println("Books -->");
        for (book b:books)
        {
        b.printbook();
        }
        
        System.out.println("Users-->");
         for (user u:persons)
        {
        u.printwithpass();
        }
        
  
   
        
        
    }
    
    
    
    
    
    
    
    
    
}
