/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.ArrayList;
import modelo.Domicilio;
import modelo.Estado;
import modelo.Localidad;
import modelo.Pais;
import modelo.Proveedor;
import modelo.Provincia;

/**
 *
 * @author ZURITA
 */
public interface IGestionProveedorMod {
    void cargarListaPais(ArrayList<Pais> paises);
    void cargarListaProvincia(ArrayList<Provincia> provincias);
    void cargarListaLocalidad(ArrayList<Localidad> localidades);
    void cargarListaEstado(ArrayList<Estado> Estados);
    void llenarVistaConDatos(Proveedor proveedor, Estado estado, Domicilio domicilio, Localidad localidad);
    void mostrarMje(String mje);
    void cerrar();
}
