package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.CarroDTO;
import com.springboot.tienda.app.modelos.entidades.Carro;
import com.springboot.tienda.app.modelos.entidades.Cliente;
import com.springboot.tienda.app.modelos.entidades.EstadoCarro;
import com.springboot.tienda.app.modelos.entidades.Orden;
import com.springboot.tienda.app.modelos.repositorios.CarroRepositorio;
import com.springboot.tienda.app.modelos.repositorios.ClienteRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *Un servicio en una aplicacion de Spring es un componente que envuelve la logica de negocio ,
 * es como el pegamento entre la capa de persistencia y la capa Web . 
 * 
 * 
 * @author Daniel
 */


@RequiredArgsConstructor // Anotacion de lombok que genera un constructor con todos los parametros y en este caso tambien inyecta los componentes.
@Slf4j // Anotacion que nos provee de un logger para mandar datos a la consola , pueden ser traces de error o string personalizados .
@Service // Anotamos con el estereotipo de Service para acomodarse a buenas practicas . 
@Transactional // Anotacion que inyecta la logica transaccional a la clase. 
public class CarroServicio {
    
    /**
     * Estas tres dependecias las inyecta lombok en el constructor a traves de la anotacion @RequiredArgsConstructor
     * ¿Por que prefiero pasar la inyeccion de dependencia por el constructor ?
     * -Mejora a la hora de hacer test ya que es mas facil pasar objetos Mock en el constructor mientras haces testing
     * -Por esta via obligamos a spring a inyectarnos las dependencias  , asegurandonos de que cada objeto creado sea valido .
     **/
    
    
    private final CarroRepositorio carroRepositorio;

    private final ClienteRepositorio clienteRepositorio;

    private final OrdenServicio ordenServicio;
    
    /**
     *Metodo que nos devuelve la lista de todos los carros y los parametriza a objetos de tipo CarroDTO .
     * 
     * @return List CarroDTO
     */
    public List<CarroDTO> listarCarros() {
        log.debug("Peticion para obtener la lista de carros.");

        return this.carroRepositorio.findAll()
                .stream()
                .map(CarroServicio::mapearADto)
                .collect(Collectors.toList());

    }
    
    /**
     *Metodo que devuelve una lista de todos los carros activos , y los parametriza a objetos de tipo CarroDTO ,
     * 
     * @return List CarroDTO
     */
    public List<CarroDTO> listarTodosLosCarrosActivos(){
    
        return this.carroRepositorio.findByEstadoCarro(EstadoCarro.NUEVO)
                .stream()
                .map(CarroServicio::mapearADto)
                .collect(Collectors.toList());
    }
    
    /**
     *Metodo que nos devuelve un nuevo carro para un cliente , lanzara una excepcion del tipo
     * IllegalStateOfException si el cliente ya tiene un carro activo.
     * @param idCliente
     * @return CarroDTO
     */
    public CarroDTO crearCarro(Long idCliente){
        
       
        if(this.verCarroActivoDeCliente(idCliente) == null ){
        
            Cliente cliente = this.clienteRepositorio.findById(idCliente).orElseThrow( () -> new IllegalStateException("El cliente no existe"));
            
            Carro carro = new Carro(null , cliente , EstadoCarro.NUEVO);
            
            Orden orden = this.ordenServicio.crearOrdenDesdeCarro(carro);
            
            carro.setOrden(orden);
            
            return this.mapearADto(this.carroRepositorio.save(carro));
            
        }else {
        
            throw new IllegalStateException("Este cliente ya tiene un carro activo . ");
        
        }
        
    }
    
    /**
     *Metodo para buscar un carro por su Id , nos lanzara una excepcion del tipo IllegalStateException si 
     * no encuentra el carro .
     * @param id
     * @return CarroDTO
     */
    @Transactional (readOnly = true)
    public CarroDTO buscarCarroPorId(Long id){
        log.debug("Peticion a la base de datos de un carro especifico por su ID" + id);
        
        
        return this.carroRepositorio.findById(id).map(CarroServicio::mapearADto).orElseThrow(()-> new IllegalStateException("No se encontro"
                + "carro con id : " + id));
                        
                        
    }
    
    /**
     *Metodo estatico que nos verifica si el cliente tiene un carro activo
     * Lanzara una Excepcion del tipo IllegalStateOfExcepcion si el cliente tiene mas de un carro .
     * 
     * @param idCliente
     * @return CarroDTO
     */
    public CarroDTO verCarroActivoDeCliente (Long idCliente){
    
        List<Carro> carros = this.carroRepositorio.findByEstadoCarroAndClienteId(EstadoCarro.NUEVO, idCliente);
        
        if (carros != null ) {
            if (carros.size() == 1) {
                log.debug("El cliente no tiene ningun carro .");
                return mapearADto(carros.get(0));
            }
            
            if (carros .size() > 1) {
            
                throw new IllegalStateException("Muchos carros activos detectados , el cliente no puede tener mas de un carro activo ");
            }
        }
        
        return null;
    }
    
    /**
     *metodo para eliminar un carro por su Id , en este caso cambia el estado del carro a CANCELADO y lo guarda en la BD.
     * @param id
     */
    public void borrarCarro(Long id){
    
        log.debug("En proceso a borrar el carro con el id : " + id );
        Carro carro = this.carroRepositorio.findById(id).orElseThrow(()-> new IllegalStateException("No se borrar"
                + "carro con id : " + id));
    
        carro.setEstadoCarro(EstadoCarro.CANCELADO);
        this.carroRepositorio.save(carro);
        
    }
    
    
    /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Carro" a un objeto de tipo DTO "CarroDTO".
     * 
     * @param carro
     * @return CarroDTO
     */
    public static CarroDTO mapearADto(Carro carro) {

        if (carro != null) {
            return new CarroDTO(carro.getId(), carro.getOrden().getId(), ClienteServicio.mapearADto(carro.getCliente()), carro.getEstadoCarro().name());
        }
        return null;

    }
    
   
    

}
