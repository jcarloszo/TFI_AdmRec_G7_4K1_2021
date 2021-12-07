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
import interfaz.IGestionProveedorAg;
import java.util.ArrayList;
import modelo.*;

/**
 *
 * @author ZURITA
 */
public class PresentadorGestorProveedorAg {
    private IGestionProveedorAg vista;
    private ArrayList<Pais> paises = new ArrayList<>();
    private ArrayList<Provincia> provincias = new ArrayList<>();
    private ArrayList<Localidad> localidades = new ArrayList<>();
    private ArrayList<Estado> estados = new ArrayList<>();

    public PresentadorGestorProveedorAg(IGestionProveedorAg vista) {
        this.vista = vista;
    }
    
    public void cargarListaPais(){
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

    public void registrarProveedor(long cuit, String razonSocial, long telefono, String calle, int numero, String localidad, String estado) {
        
        
        if(cuit > 0 && !razonSocial.equals("") && telefono > 0 && !calle.equals("") && numero > 0){
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
            
            Proveedor proveedor = new Proveedor();
            proveedor.setCuit(cuit);
            proveedor.setTelefono(telefono);
            proveedor.setRazonSocial(razonSocial);
            proveedor.setIdEstado(est.getIdEstado());
            proveedor.setIdDomicilio(domicilio.getId());
                        
            GestorProveedores.agregar(proveedor);
            
            vista.mostrarMje("Proveedor registrado con éxito.");
            vista.cerrar();
        } else {
            vista.mostrarMje("Error en uno de los campos.");
        }
    }
}
