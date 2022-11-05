/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.util.List;

/**
 *
 * @author EliteBook
 */
public class borrower extends user {
    
   
    
    
    public borrower()
    {
        
    }
    
    
    public borrower(String name,int id,int pass,String ph,String addr,String type,String fs) {
        super(name,id,pass,ph,addr,type,fs);
       
        
    }
    
    
      public void placeonhold(int bookid)
   {
           for (book b:books)
       {
           if (b.bookid==bookid)
           {
            
               if (b.status==0) /// if not avaiable
               {
               
                    b.addtoqueue(this); 
               }
               else
               {
                  System.out.println("book is available");
               }
 
           }
       }   
        
   }
    
    
    
    
    
}
