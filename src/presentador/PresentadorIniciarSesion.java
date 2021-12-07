/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentador;

import datos.GestorUsuarios;
import interfaz.IIniciarSesion;
import modelo.Sesion;
import modelo.Usuario;

/**
 *
 * @author ZURITA
 */
public class PresentadorIniciarSesion {
    private IIniciarSesion vista;

    public PresentadorIniciarSesion(IIniciarSesion vista) {
        this.vista = vista;
    }
    
    public void validarUsuario(String email, String password){
        Usuario user = GestorUsuarios.buscarUsuario(email);
        System.out.println(email);
        if(user != null && user.getPassword().equals(password)){
            Sesion.setNomyAp(user.getNombre());
            vista.abrirMenu();
        } else {
            vista.mostrarMjeDatosIncorrectos();
        }
    }
}
