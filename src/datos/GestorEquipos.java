/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Equipo;

/**
 *
 * @author ZURITA
 */
public class GestorEquipos {
    public static void agregar(Equipo equipo) {
        try{
            String sql = "INSERT INTO equipo SET ID_Equipo='"+equipo.getId()+"',"
                    + "Descripcion_Equipo='"+equipo.getDescripcion()+"',"
                    + "Garantia='"+equipo.getGarantia()+"',"
                    + "PROVEEDOR_ID_Proveedor='"+equipo.getIdProveedor()+"',"
                    + "ID_EstadoEquipo='"+equipo.getIdEstado()+"',"
                    + "Comentarios='"+equipo.getComentarios()+"',"
                    + "adquisicion='"+equipo.formatoFecha()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static ArrayList<Equipo> leerEquiposPorProveedor(int id) {
        ArrayList<Equipo> lista = new ArrayList<Equipo>();
        try{
            String sql = "SELECT * FROM equipo WHERE PROVEEDOR_ID_Proveedor="+id+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Equipo tmp = new Equipo();
                tmp.setId(fila.getInt("ID_Equipo"));
                tmp.setDescripcion(fila.getString("Descripcion_Equipo"));
                tmp.setGarantia(fila.getInt("Garantia"));
                tmp.setIdProveedor(fila.getInt("PROVEEDOR_ID_Proveedor"));
                tmp.setIdEstado(fila.getInt("ID_EstadoEquipo"));
                tmp.setComentarios(fila.getString("Comentarios"));
                tmp.setAdquisicion(fila.getDate("adquisicion"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println("Error al leer datos de la tabla");
        }        
        return lista;
    }
}
