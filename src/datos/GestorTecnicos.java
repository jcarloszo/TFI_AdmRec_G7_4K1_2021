/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Tecnico;

/**
 *
 * @author ZURITA
 */
public class GestorTecnicos {

    public static void agregar(Tecnico tecnico) {
        try{
            String sql = "INSERT INTO tecnico SET DNI='"+tecnico.getDNI()+"',"
                    + "Apellido='"+tecnico.getApellido()+"',"
                    + "Nombre='"+tecnico.getNombre()+"',"
                    + "telefono='"+tecnico.getTelefono()+"',"
                    + "PROVEEDOR_ID_Proveedor='"+tecnico.getIdProveedor()+"',"
                    + "DOMICILIO_ID_Domicilio='"+tecnico.getIdDomicilio()+"',"
                    + "ESTADO_idEstado='"+tecnico.getIdEstado()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static ArrayList<Tecnico> leerTecnicosPorProveedor(int id) {
        ArrayList<Tecnico> lista = new ArrayList<Tecnico>();
        try{
            String sql = "SELECT * FROM tecnico WHERE PROVEEDOR_ID_Proveedor="+id+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Tecnico tmp = new Tecnico();
                tmp.setId(fila.getInt("idTecnico"));
                tmp.setDNI(fila.getLong("DNI"));
                tmp.setNombre(fila.getString("Nombre"));
                tmp.setApellido(fila.getString("Apellido"));
                tmp.setTelefono(fila.getLong("telefono"));
                tmp.setIdDomicilio(fila.getInt("DOMICILIO_ID_Domicilio"));
                tmp.setIdEstado(fila.getInt("ESTADO_idEstado"));
                tmp.setIdProveedor(id);
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println(e/*"Error al leer datos de la tabla"*/);
        }        
        return lista;
    }
    
    public static ArrayList<Tecnico> leerTecnicos() {
        ArrayList<Tecnico> lista = new ArrayList<Tecnico>();
        try{
            String sql = "SELECT * FROM tecnico";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Tecnico tmp = new Tecnico();
                tmp.setId(fila.getInt("idTecnico"));
                tmp.setDNI(fila.getLong("DNI"));
                tmp.setNombre(fila.getString("Nombre"));
                tmp.setApellido(fila.getString("Apellido"));
                tmp.setTelefono(fila.getLong("telefono"));
                tmp.setIdDomicilio(fila.getInt("DOMICILIO_ID_Domicilio"));
                tmp.setIdEstado(fila.getInt("ESTADO_idEstado"));
                tmp.setIdProveedor(fila.getInt("PROVEEDOR_ID_Proveedor"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println(e/*"Error al leer datos de la tabla"*/);
        }        
        return lista;
    }
    
    public static Tecnico buscarPorDni(long dni){
        try{
            String sql = "SELECT * FROM tecnico WHERE DNI="+dni+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Tecnico tmp = new Tecnico();
                tmp.setDNI(dni);
                tmp.setId(fila.getInt("idTecnico"));
                tmp.setNombre(fila.getString("Nombre"));
                tmp.setApellido(fila.getString("Apellido"));
                tmp.setTelefono(fila.getLong("telefono"));
                tmp.setIdDomicilio(fila.getInt("DOMICILIO_ID_Domicilio"));
                tmp.setIdEstado(fila.getInt("ESTADO_idEstado"));
                tmp.setIdProveedor(fila.getInt("PROVEEDOR_ID_Proveedor"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println(e/*"Error al leer datos de la tabla"*/);
        }        
        return null;
    }

    public static void actualizar(Tecnico tecnico) {
        try{
            String sql = "UPDATE tecnico SET "
                    + "DNI='"+tecnico.getDNI()+ "', "
                    + "Nombre='"+tecnico.getNombre()+ "',"
                    + "Apellido='"+tecnico.getApellido()+"', "
                    + "telefono='"+tecnico.getTelefono()+ "',"
                    + "DOMICILIO_ID_Domicilio='"+tecnico.getIdDomicilio()+ "',"
                    + "ESTADO_idEstado='"+tecnico.getIdEstado()+ "',"
                    + "PROVEEDOR_ID_Proveedor='"+tecnico.getIdProveedor()+ "'"
                    + "WHERE idTecnico='"+tecnico.getId()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void borrar(int idTecnico){
        try{
            String sql = "DELETE FROM tecnico WHERE idTecnico="+idTecnico+"";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }        
    }
}
