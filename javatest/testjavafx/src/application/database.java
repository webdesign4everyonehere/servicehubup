package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    
    public static Connection connectDb(){
        
        try{
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // �tablir la connexion � la base de donn�es servicehubdata
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/servicehubdata", "root", "");
            System.out.println("Connexion �tablie avec succ�s � la base de donn�es servicehubdata.");
            return connect;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
