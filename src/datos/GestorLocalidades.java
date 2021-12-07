/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Localidad;
import modelo.Provincia;

/**
 *
 * @author ZURITA
 */
public class GestorLocalidades {
    public static ArrayList<Localidad> leerLocalidades(int idProvincia){
        ArrayList<Localidad> lista = new ArrayList<Localidad>();
        try{
            String sql = "SELECT * FROM localidad WHERE PROVINCIA_ID_Provincia="+idProvincia+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Localidad tmp = new Localidad();
                tmp.setCodigoPostal(fila.getInt("Código_Postal"));
                tmp.setIdProvincia(fila.getInt("PROVINCIA_ID_Provincia"));
                tmp.setNombre(fila.getString("Nombre_Localidad"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
    
    public static Localidad buscarLocalidad(int codPostal){
        try{
            String sql = "SELECT * FROM localidad WHERE Código_Postal="+codPostal+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Localidad tmp = new Localidad();
                tmp.setCodigoPostal(fila.getInt("Código_Postal"));
                tmp.setIdProvincia(fila.getInt("PROVINCIA_ID_Provincia"));
                tmp.setNombre(fila.getString("Nombre_Localidad"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return null;
    }
}
