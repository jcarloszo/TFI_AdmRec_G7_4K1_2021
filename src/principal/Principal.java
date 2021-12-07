/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import datos.DBConexion;
import datos.GestorUsuarios;
import interfaz.IIniciarSesion;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Equipo;
import modelo.Usuario;
import vista.VistaIniciarSesion;
import vista.VistaPrincipal;

/**
 *
 * @author ZURITA
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //DBConexion c = new DBConexion();
        //c.getConnection();
        if(DBConexion.getConexion()){
            VistaPrincipal vista = new VistaPrincipal();
            vista.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CONECTAR CON LA BD");
        }   
    }
}
