/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pais;

/**
 *
 * @author ZURITA
 */
public class GestorPaises {
    public static ArrayList<Pais> leerPaises(){
        ArrayList<Pais> lista = new ArrayList<Pais>();
        try{
            String sql = "SELECT * FROM pais";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Pais tmp = new Pais();
                tmp.setId(fila.getInt("ID_País"));
                tmp.setNombre(fila.getString("Nombre_País"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
    
}
