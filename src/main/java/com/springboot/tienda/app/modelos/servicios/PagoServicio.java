package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.PagoDTO;
import com.springboot.tienda.app.modelos.entidades.EstadoPago;
import com.springboot.tienda.app.modelos.entidades.Orden;
import com.springboot.tienda.app.modelos.entidades.Pago;
import com.springboot.tienda.app.modelos.repositorios.OrdenRepositorio;
import com.springboot.tienda.app.modelos.repositorios.PagoRepositorio;
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
public class PagoServicio {

    private final PagoRepositorio pagoRepositorio;

    private final OrdenRepositorio ordenRepositorio;
    
   /**
     *Metodo que nos devuelve la lista de todas los pagos y los parametriza a objetos de tipo PagoDTO .
     * 
     * @return List PagoDTO 
     */
    public List<PagoDTO> listarPagos(){
    
        log.debug("Peticion para listar todos los pagos .");
        
        return this.pagoRepositorio.findAll().stream()
                .map(PagoServicio::mapearADto)
                .collect(Collectors.toList());
    }
    
    /**
     *Metodo para buscar un pago por su Id , retorna null si no encuentra el pago en la BD.
     * @param id
     * @return PagoDTO
     */
    @Transactional(readOnly=true)
    public PagoDTO buscarPagoPorId(Long id){
    
        log.debug("Peticion para buscar pago por el id : " + id);
        
        return this.pagoRepositorio.findById(id).map(PagoServicio::mapearADto).orElse(null);
    
    }
    
    
    /**
     *Metodo para crear un pago en la base de datos , lanza una Excepcion del tipo IllegalStateException si la orden no existe.
     * 
     * @param pagoDTO
     * @return PagoDTO
     */
    public PagoDTO crearPago(PagoDTO pagoDTO){
    
        log.debug("Peticion para crear un nuevo pago ");
        
        Orden orden = this.ordenRepositorio.findById(pagoDTO.getId()).orElseThrow(() ->
                new IllegalStateException("La orden no existe")
        );
        
        return mapearADto(this.pagoRepositorio.save(new Pago(
        
                pagoDTO.getPaypalPagoId() ,
                EstadoPago.valueOf(pagoDTO.getEstadoPago()) ,
                orden
        )));
    }
    
    /**
     *Metodo para borrar un pago de la Base de datos .
     * @param id
     */
    public void borrarPagoPorId(Long id){
    
        log.debug("Peticion para borrar un pago con el id : " + id);
        this.pagoRepositorio.deleteById(id);
    
    }
    
    /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Pago" a un objeto
     * de tipo DTO "PagoDTO".
     *
     * @param categoria
     * @return categoriaDto
     */
    public static PagoDTO mapearADto(Pago pago) {

        if (pago != null) {

            return new PagoDTO(
                    pago.getId(),
                    pago.getPaypalPagoId(),
                    pago.getEstadoPago().name(),
                    pago.getOrden().getId()
            );
        }

        return null;
    }

}
