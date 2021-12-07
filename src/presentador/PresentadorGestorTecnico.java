/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentador;

import datos.GestorDomicilio;
import datos.GestorEstados;
import datos.GestorProveedores;
import datos.GestorTecnicos;
import interfaz.IGestionTecnico;
import java.util.ArrayList;
import modelo.Estado;
import modelo.Proveedor;
import modelo.Tecnico;

/**
 *
 * @author ZURITA
 */
public class PresentadorGestorTecnico {
    private IGestionTecnico vista;
    private ArrayList<Tecnico> tecnicos = new ArrayList();

    public PresentadorGestorTecnico(IGestionTecnico vista) {
        this.vista = vista;
    }

    public void leerTecnicos() {
        tecnicos = GestorTecnicos.leerTecnicos();
        
        ArrayList<Estado> estados = leerEstados(tecnicos);
        ArrayList<String> direcciones = leerDireccion(tecnicos);
        
        vista.llenarTablaTecnicos(tecnicos, estados, direcciones);
    }

    private ArrayList<Estado> leerEstados(ArrayList<Tecnico> proveedores) {
        ArrayList<Estado> estados = new ArrayList();
        for(Tecnico prov : proveedores){
            estados.add(GestorEstados.buscarEstado(prov.getIdEstado()));
        }
        return estados;
    }

    private ArrayList<String> leerDireccion(ArrayList<Tecnico> proveedores) {
        ArrayList<String> direcciones = new ArrayList();
        for(Tecnico prov : proveedores){
            direcciones.add(GestorDomicilio.buscarDomicilio(prov.getIdDomicilio()).toString());
        }
        return direcciones;
    }

    public void buscarTecnicoPorDni(long dni) {
        Tecnico tecnico = GestorTecnicos.buscarPorDni(dni);
        if(tecnico != null){
            tecnicos = new ArrayList<Tecnico>();
            tecnicos.add(tecnico);
            ArrayList<Estado> estados = leerEstados(tecnicos);
            ArrayList<String> direcciones = leerDireccion(tecnicos);
            vista.llenarTablaTecnicos(tecnicos, estados, direcciones);
        } else {
            vista.mostrarMje("Error en el DNI o no existe");
        }
    }

    public void buscarProveedor(long dni) {
        Tecnico tecnico = GestorTecnicos.buscarPorDni(dni);
        Proveedor proveedor = GestorProveedores.buscarPorID(tecnico.getIdProveedor());
        vista.mostrarDatosProveedor(proveedor);
    }

    public void eliminarTecnico(long dni) {
        Tecnico tecnico = GestorTecnicos.buscarPorDni(dni);
        GestorTecnicos.borrar(tecnico.getId());
        leerTecnicos();
        vista.mostrarMje("Se ha borrado al técnico del sistema");
        leerTecnicos();
    }
    
    
}
