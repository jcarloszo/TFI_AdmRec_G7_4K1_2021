/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Estado;
import modelo.EstadoEquipo;
import modelo.Pais;
import modelo.Usuario;

/**
 *
 * @author ZURITA
 */
public class GestorEstados {
    public static ArrayList<Estado> leerEstados(){
        ArrayList<Estado> lista = new ArrayList<Estado>();
        try{
            String sql = "SELECT * FROM estado";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Estado tmp = new Estado();
                tmp.setIdEstado(fila.getInt("idEstado"));
                tmp.setEstado(fila.getString("Estado"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }

    public static Estado buscarEstado(int idEstado) {
        try{
            String sql = "SELECT * FROM estado WHERE idEstado='"+idEstado+"'";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Estado tmp = new Estado();
                tmp.setIdEstado(fila.getInt("idEstado"));
                tmp.setEstado(fila.getString("Estado"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }
        return null; 
    }
    
    public static ArrayList<EstadoEquipo> leerEstadosEquipos(){
        ArrayList<EstadoEquipo> lista = new ArrayList<EstadoEquipo>();
        try{
            String sql = "SELECT * FROM estado_equipo";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                EstadoEquipo tmp = new EstadoEquipo();
                tmp.setId(fila.getInt("ID_Estado"));
                tmp.setEstadoEquipo(fila.getString("Estado"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
    
    public static EstadoEquipo buscarEstadoEquipo(int idEstado) {
        try{
            String sql = "SELECT * FROM estado_equipo WHERE ID_Estado='"+idEstado+"'";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                EstadoEquipo tmp = new EstadoEquipo();
                tmp.setId(fila.getInt("ID_Estado"));
                tmp.setEstadoEquipo(fila.getString("Estado"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }
        return null; 
    }
}
