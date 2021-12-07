/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.ArrayList;
import modelo.Estado;
import modelo.Proveedor;
import modelo.Tecnico;

/**
 *
 * @author ZURITA
 */
public interface IGestionProveedor {
    void llenarTablaProveedores(ArrayList<Proveedor> proveedores, ArrayList<Estado> estados, ArrayList<String> direcciones, double[] calificaciones);
    void llenarTablaTecnicos(ArrayList<Tecnico> tecnicos, ArrayList<Estado> estados, ArrayList<String> direcciones);
    void mostrarMje(String mje);
}
