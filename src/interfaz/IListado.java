/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.ArrayList;
import modelo.Equipo;
import modelo.Proveedor;

/**
 *
 * @author ZURITA
 */
public interface IListado {
    void mostrarMje(String mje);
    void cargarTablaProveedor(ArrayList<Proveedor> proveedores);
    void cargarTablaSeleccionados(ArrayList<Proveedor> seleccionados);
    void cargarTablaEquipos(ArrayList<Equipo> equipos);
    void mostrarDatosEquipo(String comentarios, String estadoEquipo);
}
