/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scamazon;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

/**
 *
 * @author Kyle
 */
public class SQLMethods {

    public void File_to_Database(String FileName){

        String dbName, userName, userPassword, serverIP;
        dbName="kyle.beckley";  //your database name.  In class, you have one same as your login id
        userName="kyle.beckley";  // your login id to the database
        userPassword = "apple";  //your password to database (not the machine)
        serverIP="147.97.156.236";

        try {
            // Step 1: Allocate a database 'Connection' object
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+serverIP+"/"+dbName, userName, userPassword);
                //"jdbc:postgresql://hostname:port/databaseName", "username", "password"

            CopyManager cm = new CopyManager((BaseConnection) con);

            FileReader fr = new FileReader(FileName);
            //cm.copyIn("COPY item FROM STDIN WITH DELIMITER '|'", fr); //suh, comment out original one
            cm.copyIn("COPY sells FROM STDIN WITH DELIMITER '|'", fr);  //suh, change table name to sells

        } catch (SQLException | IOException ex) {
            Logger lgr = Logger.getLogger(ItemClass.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }

    public void All_to_Database(int max){
        int j = 1;
        for (int i = 0; i < max; i++){
            String fileName = "item" + j;
            String txt = fileName + ".txt";
            String img = fileName + ".jpeg";  //suh, comment out the original one
            File_to_Database(txt);
            WriteImage(img);  //suh, command out the original one
            j++;
        }
    }

    public void Retrieve (ItemClass item){
        String dbName, userName, userPassword, serverIP;
        dbName="kyle.beckley";  //your database name.  In class, you have one same as your login id
        userName="kyle.beckley";  // your login id to the database
        userPassword = "apple";  //your password to database (not the machine)
        serverIP="147.97.156.236";

        try {
            // Step 1: Allocate a database 'Connection' object
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+serverIP+"/"+dbName, userName, userPassword);
                //"jdbc:postgresql://hostname:port/databaseName", "username", "password"

            PreparedStatement pst = con.prepareStatement("SELECT * FROM item");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                item.setItemName(rs.getString(1));
                item.setPrice(rs.getDouble(2));
                item.setSeller(rs.getString(3));
                item.setIsbn(rs.getString(4));
                item.setDescription(rs.getString(5));
                item.setCategory(rs.getString(6));
                item.setRating(rs.getInt(7));
                item.setStock(rs.getInt(8));

            }
        } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(ItemClass.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
        // Step 5: Close the resources - Done automatically by try-with-resources
    }

    public void WriteImage(String FileName){
        String dbName, userName, userPassword, serverIP;
        dbName="kyle.beckley";  //your database name.  In class, you have one same as your login id
        userName="kyle.beckley";  // your login id to the database
        userPassword = "apple";  //your password to database (not the machine)
        serverIP="147.97.156.236";

        try {
            // Step 1: Allocate a database 'Connection' object
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+serverIP+"/"+dbName, userName, userPassword);
                //"jdbc:postgresql://hostname:port/databaseName", "username", "password"

            File img = new File(FileName);
            FileInputStream fin = new FileInputStream(img);

            PreparedStatement pst = con.prepareStatement("INSERT INTO image(data) VALUES(?)");
            pst.setBinaryStream(1, fin, (int) img.length());
            pst.executeUpdate();

        } catch (FileNotFoundException | SQLException ex) {
            Logger lgr = Logger.getLogger(ItemClass.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void ReadImage (String FileName){
        String dbName, userName, userPassword, serverIP;
        dbName="kyle.beckley";  //your database name.  In class, you have one same as your login id
        userName="kyle.beckley";  // your login id to the database
        userPassword = "apple";  //your password to database (not the machine)
        serverIP="147.97.156.236";

        try {
            // Step 1: Allocate a database 'Connection' object
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+serverIP+"/"+dbName, userName, userPassword);
                //"jdbc:postgresql://hostname:port/databaseName", "username", "password"

                String query = "SELECT data, LENGTH(data) FROM image WHERE id = 1";
            PreparedStatement pst = con.prepareStatement(query);

            ResultSet result = pst.executeQuery();
            result.next();

            FileOutputStream fos = new FileOutputStream(FileName);

            int len = result.getInt(2);
            byte[] buf = result.getBytes("data");
            fos.write(buf, 0, len);


        } catch (IOException | SQLException ex) {
            Logger lgr = Logger.getLogger(ItemClass.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }


}
