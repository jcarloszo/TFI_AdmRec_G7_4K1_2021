/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Proveedor;

/**
 *
 * @author ZURITA
 */
public class GestorProveedores {

    public static void agregar(Proveedor proveedor) {
        try{
            String sql = "INSERT INTO proveedor SET ID_Proveedor='"+proveedor.getId()+"',"
                    + "Razón_Social='"+proveedor.getRazonSocial()+"',"
                    + "Estado_idEstado='"+proveedor.getIdEstado()+"',"
                    + "telefono='"+proveedor.getTelefono()+"',"
                    + "DOMICILIO_ID_Domicilio='"+proveedor.getIdDomicilio()+"',"
                    + "cuit='"+proveedor.getCuit()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static ArrayList<Proveedor> leerProveedores(){
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
        try{
            String sql = "SELECT * FROM proveedor";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            while(fila.next()){
                Proveedor tmp = new Proveedor();
                tmp.setId(fila.getInt("ID_proveedor"));
                tmp.setRazonSocial(fila.getString("Razón_Social"));
                tmp.setIdEstado(fila.getInt("Estado_idEstado"));
                tmp.setTelefono(fila.getLong("telefono"));
                tmp.setIdDomicilio(fila.getInt("DOMICILIO_ID_Domicilio"));
                tmp.setCuit(fila.getLong("cuit"));
                lista.add(tmp);
            }
        }
        catch(SQLException e){
            System.out.println(e/*"Error al leer datos de la tabla"*/);
        }        
        return lista;
    }
    
    public static Proveedor buscarPorCuit(long cuit){
        try{
            String sql = "SELECT * FROM proveedor WHERE cuit="+cuit+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Proveedor tmp = new Proveedor();
                tmp.setId(fila.getInt("ID_proveedor"));
                tmp.setRazonSocial(fila.getString("Razón_Social"));
                tmp.setIdEstado(fila.getInt("Estado_idEstado"));
                tmp.setTelefono(fila.getLong("telefono"));
                tmp.setIdDomicilio(fila.getInt("DOMICILIO_ID_Domicilio"));
                tmp.setCuit(fila.getLong("cuit"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }
        return null;  
    }

    public static void actualizar(Proveedor proveedor) {
        try{
            String sql = "UPDATE proveedor SET "
                    + "Razón_Social='"+proveedor.getRazonSocial()+ "', "
                    + "Estado_idEstado='"+proveedor.getIdEstado()+ "',"
                    + "DOMICILIO_ID_Domicilio='"+proveedor.getIdDomicilio()+"', "
                    + "cuit='"+proveedor.getCuit()+"', "
                    + "telefono='"+proveedor.getTelefono()+"' "
                    + " WHERE ID_Proveedor='"+proveedor.getId()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al modificar datos a la tabla");
        }  
    }
    
    public static Proveedor buscarPorID(int id){
        try{
            String sql = "SELECT * FROM proveedor WHERE ID_Proveedor="+id+"";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Proveedor tmp = new Proveedor();
                tmp.setId(fila.getInt("ID_proveedor"));
                tmp.setRazonSocial(fila.getString("Razón_Social"));
                tmp.setIdEstado(fila.getInt("Estado_idEstado"));
                tmp.setTelefono(fila.getLong("telefono"));
                tmp.setIdDomicilio(fila.getInt("DOMICILIO_ID_Domicilio"));
                tmp.setCuit(fila.getLong("cuit"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println("Error al buscar datos de la tabla");
        }
        return null;  
    }
    
    public static void borrar(int idProv){
        try{
            String sql = "DELETE FROM proveedor WHERE ID_proveedor="+idProv+"";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }        
    }
}
