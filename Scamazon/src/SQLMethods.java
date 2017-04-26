package scamazon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import java.sql.DriverManager;
import java.util.Scanner;



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
    

    public void Retrieve (ItemClass ItemArray[]){
       /* String dbName, userName, userPassword, serverIP;
        dbName="kyle.beckley";  //your database name.  In class, you have one same as your login id
        userName="kyle.beckley";  // your login id to the database
        userPassword = "apple";  //your password to database (not the machine)
        serverIP="147.97.156.236";

        try {
            // Step 1: Allocate a database 'Connection' object
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://"+serverIP+"/"+dbName, userName, userPassword);
                //"jdbc:postgresql://hostname:port/databaseName", "username", "password"
            // con.setLoginTimeout(30);
            DriverManager.setLoginTimeout(60);
            
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
        */

       int j = 1;
        for (int i = 0; i < Globals.MAX; i++){
            String fileName = "C:\\Users\\L\\Documents\\NetBeansProjects\\scamazon\\src\\scamazon\\item" + j;
            String txt = fileName + ".txt";
            ItemArray [i] = Read(txt);
            j++;
        }
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

    public ItemClass Read (String FileName){
        ItemClass temp = new ItemClass();
        try{
            String filename = FileName;
            Scanner read = new Scanner (new File(filename));
            read.useDelimiter("\n");

                temp.setItemName(read.next());

                String a = read.next();
                double b = Double.parseDouble(a);
                temp.setPrice(b);
                temp.setSeller(read.next());
                temp.setIsbn(read.next());
                temp.setDescription(read.next());
                temp.setCategory(read.next());

                //String a2 = read.next();
                //int c = Integer.parseInt(a2);

                //temp.setRating(c);
                //a3 = read.next();
                //c = Integer.parseInt(a3);
                //temp.setStock(c);
                read.close();
        }
        catch (IOException ex) {
            Logger lgr = Logger.getLogger(ItemClass.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return temp;
    }
    
        public void RetrieveUser (User UserArray[]){
            int j = 1;
            for (int i = 0; i < Globals.MAX; i++){
                String fileName = "C:\\Users\\L\\Documents\\NetBeansProjects\\scamazon\\src\\scamazon\\user" + j;
                String txt = fileName + ".txt";
                UserArray [i] = ReadUser(txt);
                j++;
            }
        }

    public User ReadUser (String FileName){
        User temp = new User();
        try{
            String filename = FileName;
            Scanner read = new Scanner (new File(filename));
            read.useDelimiter("\n");

                temp.setUsername(read.next());
                temp.setPassword(read.next());
                temp.setID(read.next());
                String a = read.next();
                double c = Double.parseDouble(a);
                temp.setBalance(c);
                temp.setEmail(read.next());

                //String a2 = read.next();
                //int c = Integer.parseInt(a2);

                //temp.setRating(c);
                //a3 = read.next();
                //c = Integer.parseInt(a3);
                //temp.setStock(c);
                read.close();
        }
        catch (IOException ex) {
            Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return temp;
    }        
        
    public void SearchCategory (String Category, int max, ItemClass ItemArray[], ItemClass VariableCategoryArray[]){
    int k = 0;
    for(int i = 0; i < max; i++){
        if(ItemArray[i].getCategory().equalsIgnoreCase(Category)){
            VariableCategoryArray[k] = ItemArray[i];
        }
    }
}

public void Search(String Searchword, int max, ItemClass [] ItemArray, ItemClass [] VariableCategoryArray){
    int k = 0;
    ItemClass temp = new ItemClass();
    for(int i = 0; i < max; i++){
        if((ItemArray[i].getCategory()).equalsIgnoreCase("Electronics")){
            temp = ItemArray[i];
            VariableCategoryArray[k] = temp;
            
        }
        else{
            char[] charArray = Searchword.toCharArray();
            int len = charArray.length;
            char[] charArray2 = ItemArray[i].getItemName().toCharArray();
            int H = 0;
            for(int t = 0; t < len; t++){
                if(charArray[t] == charArray2[t]){
                    H++;
                }
            }
            if(H >= 3){
                VariableCategoryArray[k] = ItemArray[i];
            }
        }
    }
}
}
    

