/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author ZURITA
 */
public class DBConexion {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "root";
    private static String PASSWORD = "admin";
    private static String URL = "jdbc:mysql://localhost:3306/mydb";
    private static Connection con;
    private static Statement consulta;
    
    public static boolean getConexion() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            consulta = con.createStatement();
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public static Statement getConsulta() {
        return consulta;
    }
}
