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
import interfaz.IGestionTecnicoMod;
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
public class PresentadorGestorTecnicoMod {
    private IGestionTecnicoMod vista;
    private ArrayList<Pais> paises = new ArrayList<>();
    private ArrayList<Provincia> provincias = new ArrayList<>();
    private ArrayList<Localidad> localidades = new ArrayList<>();
    private ArrayList<Estado> estados = new ArrayList<>();
    private Tecnico tecnico;
    private Domicilio domicilio;

    public PresentadorGestorTecnicoMod(IGestionTecnicoMod vista) {
        this.vista = vista;
    }

    public void cargarListaPais() {
        paises = GestorPaises.leerPaises();
        vista.cargarListaPais(paises);    }

    public void cargarListaEstado() {
        estados = GestorEstados.leerEstados();
        vista.cargarListaEstado(estados);
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
    
    public void buscarTecnico(long dni){
        tecnico = GestorTecnicos.buscarPorDni(dni);
        Estado estado = GestorEstados.buscarEstado(tecnico.getIdEstado());
        domicilio = GestorDomicilio.buscarDomicilio(tecnico.getIdDomicilio());
        Localidad localidad = GestorLocalidades.buscarLocalidad(domicilio.getCodPostalLocalidad());
        
        vista.llenarVistaConDatos(tecnico, estado, domicilio, localidad);
    }

    public void actualizarTecnico(long dni, String nombre, String apellido, String calle, int numero, String localidad, long telefono, String estado) {
        if(dni > 0 && !nombre.equals("") && !apellido.equals("") && !calle.equals("") && numero > 0 && !localidad.equals("") && telefono > 0 && !estado.equals("")){
            Localidad loc = null;
            for(Localidad l : localidades){
                if(l.getNombre().equals(localidad)){
                    loc = l;
                    break;
                }
            }

            domicilio.setCalle(calle);
            domicilio.setNumero(numero);
            domicilio.setCodPostalLocalidad(loc.getCodigoPostal());

            GestorDomicilio.actualizar(domicilio);

            Estado est = null;
            for(Estado es : estados){
                if(es.getEstado().equals(estado)){
                    est = es;
                    break;
                }
            }
                        
            tecnico.setDNI(dni);
            tecnico.setNombre(nombre);
            tecnico.setApellido(apellido);
            tecnico.setTelefono(telefono);
            tecnico.setIdEstado(est.getIdEstado());
            tecnico.setIdDomicilio(domicilio.getId());
            
            GestorTecnicos.actualizar(tecnico);
            
            vista.mostrarMje("Técnico actualizado con éxito");
            vista.cerrar();
        } else {
            vista.mostrarMje("Error en uno de los campos");
        }
    }
    
}
