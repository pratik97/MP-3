/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pratik
 */
public class database_handler {
    public FXMLDocumentController xy;
    public void insert_data(String qid,String question,String qtype,String difficulty,String OptionA,String OptionB,String OptionC,String OptionD,String ans1) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn=null;
        cn=DriverManager.getConnection("jdbc:mysql://localhost/questions","root","");
        Statement stmt=cn.createStatement();
        System.out.println(qid);
        stmt.executeUpdate("INSERT INTO myques VALUES('"+qid+"','"+question+"','"+qtype+"','"+difficulty+"','"+OptionA+"','"+OptionB+"','"+OptionC+"','"+OptionD+"','"+ans1+"')");
        
        cn.close();
        
    }
    public void fetch_last_data()
    {
        
    }
    public int returnlastid(String x) throws SQLException
    {
        String last_id="";
        Connection cn=null;
        cn=DriverManager.getConnection("jdbc:mysql://localhost/questions","root","");
        Statement statement = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs= statement.executeQuery("select count(*) from myques where qid like '000"+x+"%';");
        while(rs.next())
        {
            last_id=rs.getString(1);
        }
        System.out.println(xy.incrementer);
        return Integer.parseInt(last_id);
    }
    
}
