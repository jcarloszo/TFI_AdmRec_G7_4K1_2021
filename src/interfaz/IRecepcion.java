/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.ArrayList;
import modelo.Equipo;
import modelo.EstadoEquipo;
import modelo.Proveedor;

/**
 *
 * @author ZURITA
 */
public interface IRecepcion {
    void mostrarMje(String mje);
    void listarEstadosEquipos(ArrayList<EstadoEquipo> estados);
    void mostrarDatosProveedor(Proveedor proveedor);
    void listarEquipos(ArrayList<Equipo> equipos, ArrayList<EstadoEquipo> estados);
    void limpiar();
    void cerrar();
}
