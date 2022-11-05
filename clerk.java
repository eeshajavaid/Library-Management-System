/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.List;

/**
 *
 * @author EliteBook
 */
public class clerk extends user {
    
    
    public clerk(String name,int id,int pass,String ph,String addr,String type,String fs){
        super(name,id,pass,ph,addr,type,fs);
       
        
    }
    
    public clerk()
    {
        super();
    }
    
    
    
     
    
}
