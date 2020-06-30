package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.OrdenDTO;
import com.springboot.tienda.app.modelos.entidades.Carro;
import com.springboot.tienda.app.modelos.entidades.EstadoOrden;
import com.springboot.tienda.app.modelos.entidades.Orden;
import com.springboot.tienda.app.modelos.repositorios.OrdenRepositorio;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class OrdenServicio {

    private final OrdenRepositorio ordenRepositorio;

    /**
     * Metodo que se llama desde CarroServicio para crear una orden.
     *
     * @param carro
     * @return Orden
     */
    public Orden crearOrdenDesdeCarro(Carro carro) {

        log.debug("Creando una orden desde Carro servicio ");
        return this.ordenRepositorio.save(
                new Orden(
                        BigDecimal.ZERO,
                        EstadoOrden.CREADO,
                        null,
                        null,
                        null,
                        Collections.emptySet(),
                        carro
                ));
    }

    /**
     * Metodo que nos devuelve la lista de todas las ordenes y los parametriza a
     * objetos de tipo OrdenDTO .
     *
     * @return List OrdenDTO
     */
    public List<OrdenDTO> listarOrdenes() {

        log.debug("Peticion para listar todas las ordenes");

        return this.ordenRepositorio.findAll().stream()
                .map(OrdenServicio::mapearADto)
                .collect(Collectors.toList());
    }

    /**
     * Metodo para buscar una orden por su Id , retorna null si no lo encuentra
     * en la DB
     *
     * @param id
     * @return OrdenDTO
     */
    @Transactional(readOnly = true)
    public OrdenDTO buscarOrdenPorId(Long id) {

        log.debug("Peticion para buscar una orden por el id :" + id);

        return this.ordenRepositorio.findById(id).map(OrdenServicio::mapearADto).orElse(null);
    }

    /**
     * Metodo que devuelve las ordenes del cliente pasando su id.
     *
     * @param id
     * @return List OrdenDTO
     */
    public List<OrdenDTO> buscarOrdenesPorCliente(Long id) {

        log.debug("Peticion para buscar ordenes por el id :" + id);

        return this.ordenRepositorio.findByCarroCliente_Id(id)
                .stream()
                .map(OrdenServicio::mapearADto)
                .collect(Collectors.toList());

    }

    /**
     *Metodo para crear una orden en la base de datos , y nos retorna un Objeto DTO .
     * @param ordenDTO
     * @return OrdenDTO
     */
    public OrdenDTO crearOrden(OrdenDTO ordenDTO) {

        log.debug("Peticion para crear una orden nueva");

        return mapearADto(this.ordenRepositorio.save(
                new Orden(
                        BigDecimal.ZERO,
                        EstadoOrden.CREADO,
                        null,
                        null,
                        null,
                        Collections.emptySet(),
                        null
                )));
    }
    
    public void delete(Long id){
    
        log.debug("Peticion para borrar una orde por el id : "+ id);
        this.ordenRepositorio.deleteById(id);
    }

    /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Orden" a un objeto
     * de tipo DTO "OrdenDTO".
     *
     * @param orden
     * @return OrdenDTO
     */
    public static OrdenDTO mapearADto(Orden orden) {

        if (orden != null) {
            return new OrdenDTO(
                    orden.getId(),
                    orden.getPrecioTotal(),
                    orden.getEstadoOrden().name(),
                    orden.getEnviada(),
                    PagoServicio.mapearADto(orden.getPago()),
                    DireccionServicio.mapearADto(orden.getDireccionEnvio()),
                    orden.getItemsOrden().stream().map(ItemOrdenServicio::mapearADto)
                            .collect(Collectors.toSet())
            );

        }

        return null;
    }

}
