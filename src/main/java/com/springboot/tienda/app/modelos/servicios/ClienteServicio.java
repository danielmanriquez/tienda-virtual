package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.ClienteDTO;
import com.springboot.tienda.app.modelos.entidades.Cliente;
import com.springboot.tienda.app.modelos.repositorios.ClienteRepositorio;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    /**
     * Metodo para crear un cliente en la base de datos .
     *
     * @param clienteDTO
     * @return clienteDTO
     */
    public ClienteDTO crearCliente(ClienteDTO clienteDto) {

        log.debug("Peticion para crear un nuevo Cliente");
        return mapearADto(this.clienteRepositorio.save(new Cliente(
                clienteDto.getNombre(),
                clienteDto.getApellido(),
                clienteDto.getEmail(),
                clienteDto.getTelefono(),
                Boolean.FALSE ,
                Collections.emptySet()
                
        )));
    }

    /**
     * Metodo que devuelve una lista de todos los clientes , y los parametriza a
     * objetos de tipo ClienteDTO ,
     *
     * @return List ClienteDTO
     */
    public List<ClienteDTO> listarTodosLosClientes() {

        log.debug("Peticion para listar todos los clientes");

        return this.clienteRepositorio.findAll().stream()
                .map(ClienteServicio::mapearADto)
                .collect(Collectors.toList());
    }

    /**
     * Metodo para buscar un cliente por su Id , devolvera null si no existe en
     * la base de datos.
     *
     * @param id
     * @return ClienteDTO
     */
    public ClienteDTO buscarClientePorId(Long id) {

        log.debug("Peticion para buscar un cliente por el siguiente id : " + id);

        return this.clienteRepositorio.findById(id).map(ClienteServicio::mapearADto)
                .orElse(null);
    }
    
    /**
     *Metodo para listar todos los clientes activos y los devulve como objetos de tipo DTO.
     * @return List ClienteDTO
     */
    public List<ClienteDTO> listarClientesActivos(){
    
        log.debug("Peticion para listar todos los clientes activos");
        return this.clienteRepositorio.findAllByActivo(true)
                .stream()
                .map(ClienteServicio::mapearADto)
                .collect(Collectors.toList());
    }
    
     /**
     *Metodo para listar todos los clientes inactivos y los devulve como objetos de tipo DTO.
     * @return List ClienteDTO
     */
    public List<ClienteDTO> listarClientesInactivos(){
    
        log.debug("Peticion para listar todos los clientes inactivos");
        return this.clienteRepositorio.findAllByActivo(false)
                .stream()
                .map(ClienteServicio::mapearADto)
                .collect(Collectors.toList());
    }
    
    /**
     *Metodo para cambiar el valor de activo a false en la base de datos , lanza una excepcion de tipo
     * IllegalStateExcepcion si no encuentra el cliente en la BD.
     * @param id
     */
    public void borrarCliente(Long id){
        
        log.debug("Peticion para borrar un cliente");
        Cliente cliente = this.clienteRepositorio.findById(id).orElseThrow(() -> new IllegalStateException("No se pudo encontrar el cliente con id : "+id));
        
        cliente.setActivo(false);
        this.clienteRepositorio.save(cliente);
    }

    /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Cliente" a un
     * objeto de tipo DTO "ClienteDTO".
     *
     * @param cliente
     * @return ClienteDTO
     */
    public static ClienteDTO mapearADto(Cliente cliente) {

        if (cliente != null) {

            return new ClienteDTO(cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getEmail(),
                    cliente.getTelefono());
        }
        return null;
    }

}
