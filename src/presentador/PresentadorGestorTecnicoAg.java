/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentador;

import datos.GestorDomicilio;
import datos.GestorEstados;
import datos.GestorLocalidades;
import datos.GestorPaises;
import datos.GestorProveedores;
import datos.GestorProvincias;
import datos.GestorTecnicos;
import interfaz.IGestionTecnicoAg;
import java.util.ArrayList;
import modelo.Domicilio;
import modelo.Estado;
import modelo.Localidad;
import modelo.Pais;
import modelo.Proveedor;
import modelo.Provincia;
import modelo.Tecnico;

/**
 *
 * @author ZURITA
 */
public class PresentadorGestorTecnicoAg {
    private IGestionTecnicoAg vista;
    private ArrayList<Pais> paises = new ArrayList<>();
    private ArrayList<Provincia> provincias = new ArrayList<>();
    private ArrayList<Localidad> localidades = new ArrayList<>();
    private ArrayList<Estado> estados = new ArrayList<>();

    public PresentadorGestorTecnicoAg(IGestionTecnicoAg vista) {
        this.vista = vista;
    }

    public void cargarListaPais() {
        paises = GestorPaises.leerPaises();
        vista.cargarListaPais(paises);
    }

    public void leerProvincias(String nombrePais) {
        Pais pais = null;
        for(Pais p : paises){
            if(p.getNombre().equals(nombrePais)){
                pais = p;
                break;
            }
        }
        provincias = GestorProvincias.leerProvincias(pais.getId());
        vista.cargarListaProvincia(provincias);
    }

    public void leerLocalidades(String provincia) {
        Provincia prov = null;
        for(Provincia p : provincias){
            if(p.getNombre().equals(provincia)){
                prov = p;
                break;
            }
        }
        localidades = GestorLocalidades.leerLocalidades(prov.getId());
        vista.cargarListaLocalidad(localidades);
    }

    public void cargarListaEstado() {
        estados = GestorEstados.leerEstados();
        vista.cargarListaEstado(estados);
    }

    public void registarTecnico(long dni, long cuitProveedor, String nombre, String apellido, String calle, int numero, String localidad, long telefono, String estado) {
        if(dni > 0 && cuitProveedor > 0 && !nombre.equals("") && !apellido.equals("") && !calle.equals("") && numero > 0 && !localidad.equals("") && telefono > 0 && !estado.equals("")){
            Localidad loc = null;
            for(Localidad l : localidades){
                if(l.getNombre().equals(localidad)){
                    loc = l;
                    break;
                }
            }

            Domicilio domicilio = new Domicilio();
            domicilio.setCalle(calle);
            domicilio.setNumero(numero);
            domicilio.setCodPostalLocalidad(loc.getCodigoPostal());

            GestorDomicilio.agregar(domicilio);

            Estado est = null;
            for(Estado es : estados){
                if(es.getEstado().equals(estado)){
                    est = es;
                    break;
                }
            }
            
            Proveedor proveedor = GestorProveedores.buscarPorCuit(cuitProveedor);
            
            Tecnico tecnico = new Tecnico();
            tecnico.setDNI(dni);
            tecnico.setIdProveedor(proveedor.getId());
            tecnico.setNombre(nombre);
            tecnico.setApellido(apellido);
            tecnico.setTelefono(telefono);
            tecnico.setIdEstado(est.getIdEstado());
            tecnico.setIdDomicilio(domicilio.getId());
            
            GestorTecnicos.agregar(tecnico);
            
            vista.mostrarMje("Técnico cargado con éxito");
            //vista.cerrar();
        } else {
            vista.mostrarMje("Error en uno de los campos");
        }
        
    }
    
    
}
