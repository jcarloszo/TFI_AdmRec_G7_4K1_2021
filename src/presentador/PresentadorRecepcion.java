/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentador;

import datos.GestorEquipos;
import datos.GestorEstados;
import datos.GestorProveedores;
import interfaz.IRecepcion;
import java.util.ArrayList;
import modelo.Equipo;
import modelo.Estado;
import modelo.EstadoEquipo;
import modelo.Proveedor;

/**
 *
 * @author ZURITA
 */
public class PresentadorRecepcion {
    private IRecepcion vista;
    private ArrayList<EstadoEquipo> estados = new ArrayList<>();
    private ArrayList<Equipo> equipos = new ArrayList<>();
    private Proveedor proveedor;

    public PresentadorRecepcion(IRecepcion vista) {
        this.vista = vista;
    }

    public void leerEstadosEquipo() {
        estados = GestorEstados.leerEstadosEquipos();
        vista.listarEstadosEquipos(estados);
    }

    public void buscarProveedor(long cuit) {
        proveedor = GestorProveedores.buscarPorCuit(cuit);
        
        if(proveedor != null && proveedor.getIdEstado() == 1){
            vista.mostrarDatosProveedor(proveedor);
        } else {
            vista.mostrarMje("Error en el CUIT o no existe");
        }
        
    }

    public void agregarEquipo(String descripcion, int garantia, String estado, String comentarios) {
        if(!descripcion.equals("") && garantia > 0 && proveedor != null){
            
            EstadoEquipo es = null;
            for(EstadoEquipo est : estados){
                if(est.getEstadoEquipo().equals(estado)){
                    es = est;
                    break;
                }
            }
            
            Equipo equipo = new Equipo();
            equipo.setDescripcion(descripcion);
            equipo.setGarantia(garantia);
            equipo.setComentarios(comentarios);
            equipo.setIdEstado(es.getId());
            equipo.setIdProveedor(proveedor.getId());
            
            equipos.add(equipo);
            listarEquipos();
            
        } else {
            vista.mostrarMje("Error en alguno de los campos");
        }
    }
    
    private void listarEquipos(){
        ArrayList<EstadoEquipo> estados = new ArrayList<>();
        for(Equipo equipo : equipos){
            estados.add(GestorEstados.buscarEstadoEquipo(equipo.getIdEstado()));
        }
        vista.listarEquipos(equipos, estados);
        vista.limpiar();
    }

    public void eliminarEquipo(int codigo) {
        ArrayList<Equipo> nuevoArray = new ArrayList<>();
        
        for(Equipo equipo : equipos){
            if(equipo.getId() != codigo){
                nuevoArray.add(equipo);
            }
        }
        
        equipos = nuevoArray;
        listarEquipos();
    }

    public void registrarEquipos() {
        if(proveedor != null && equipos.size()>0){
            for(Equipo equipo : equipos){
                GestorEquipos.agregar(equipo);
            }
            vista.mostrarMje("Los equipos se han registrado exitosamente");
            vista.cerrar();
        } else {
            vista.mostrarMje("No se han especificado todos los datos");
        }
    }
}
