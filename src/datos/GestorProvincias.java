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
import modelo.Provincia;

/**
 *
 * @author ZURITA
 */
public class GestorProvincias {
    public static ArrayList<Provincia> leerProvincias(int idPais){
        ArrayList<Provincia> lista = new ArrayList<Provincia>();
        try{
            String sql = "SELECT * FROM provincia WHERE PAIS_ID_País="+idPais+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Provincia tmp = new Provincia();
                tmp.setId(fila.getInt("ID_Provincia"));
                tmp.setNombre(fila.getString("Nombre_Provincia"));
                tmp.setId(fila.getInt("PAIS_ID_País"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
}
