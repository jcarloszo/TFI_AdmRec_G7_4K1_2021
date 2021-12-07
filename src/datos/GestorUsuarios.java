/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author ZURITA
 */
public class GestorUsuarios {
    
    public static Usuario buscarUsuario(String email){
        try{
            String sql = "SELECT * FROM usuario WHERE email='"+email+"'";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Usuario user = new Usuario();
                user.setId(fila.getInt("idusuario"));
                user.setNombre(fila.getString("nomyap"));
                user.setEmail(fila.getString("email"));
                user.setPassword(fila.getString("password"));
                return user;
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }
        return null;        
    }
    
    public static void agregar(Usuario user){
        try{
            String sql = "INSERT INTO usuarios SET nombre='"+user.getNombre()+"',"
                    + "email='"+user.getEmail()+"',"
                    + "password='"+user.getPassword()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al agregar datos a la tabla");
        }
    }
}
