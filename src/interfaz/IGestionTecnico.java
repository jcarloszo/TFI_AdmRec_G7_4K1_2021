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
public interface IGestionTecnico {
    void mostrarDatosProveedor(Proveedor prov);
    void mostrarMje(String mje);
    void llenarTablaTecnicos(ArrayList<Tecnico> tecnicos, ArrayList<Estado> estados, ArrayList<String> direcciones);
}
