/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Domicilio;
import modelo.Usuario;

/**
 *
 * @author ZURITA
 */
public class GestorDomicilio {
    public static void agregar(Domicilio dom){
        try{
            String sql = "INSERT INTO domicilio SET ID_Domicilio='"+dom.getId()+"',"
                    + "Calle_Domicilio='"+dom.getCalle()+"',"
                    + "Nro_Domicilio='"+dom.getNumero()+"',"
                    + "LOCALIDAD_Código_Postal='"+dom.getCodPostalLocalidad()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println("Error al agregar datos a la tabla");
        }
    }
    
    public static Domicilio buscarDomicilio(int idDomicilio) {
        try{
            String sql = "SELECT * FROM domicilio WHERE ID_Domicilio='"+idDomicilio+"'";
            ResultSet fila = DBConexion.getConsulta().executeQuery(sql);
            if(fila.next()){
                Domicilio tmp = new Domicilio();
                tmp.setId(idDomicilio);
                tmp.setCalle(fila.getString("Calle_Domicilio"));
                tmp.setNumero(fila.getInt("Nro_Domicilio"));
                tmp.setCodPostalLocalidad(fila.getInt("LOCALIDAD_Código_Postal"));
                return tmp;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return null; 
    }

    public static void actualizar(Domicilio domicilio) {
        try{
            String sql = "UPDATE domicilio SET "
                    + "Calle_Domicilio='"+domicilio.getCalle()+ "', "
                    + "Nro_Domicilio='"+domicilio.getNumero()+ "',"
                    + "LOCALIDAD_Código_Postal='"+domicilio.getCodPostalLocalidad()+"' "
                    + "WHERE ID_Domicilio='"+domicilio.getId()+"'";
            DBConexion.getConsulta().execute(sql);
        }
        catch(SQLException e){
            System.out.println(e);
        }  
    }
}
