package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.ItemOrdenDTO;
import com.springboot.tienda.app.modelos.entidades.ItemOrden;
import com.springboot.tienda.app.modelos.entidades.Orden;
import com.springboot.tienda.app.modelos.entidades.Producto;
import com.springboot.tienda.app.modelos.repositorios.ItemOrdenRepositorio;
import com.springboot.tienda.app.modelos.repositorios.OrdenRepositorio;
import com.springboot.tienda.app.modelos.repositorios.ProductoRepositorio;
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
public class ItemOrdenServicio {

    private final ItemOrdenRepositorio itemOrdenRepositorio;

    private final OrdenRepositorio ordenRepositorio;

    private final ProductoRepositorio productoRepositorio;

    /**
     * Metodo que nos devuelve la lista de todos items de una orden y los
     * parametriza a objetos de tipo ItemOrdenDTO .
     *
     * @return List ItemOrdenDTO
     */
    public List<ItemOrdenDTO> listarTodosLosItems() {

        log.debug("Peticion para listar todos los items");

        return this.itemOrdenRepositorio.findAll()
                .stream()
                .map(ItemOrdenServicio::mapearADto)
                .collect(Collectors.toList());

    }
    
    /**
     * Metodo para buscar un item de una orden por el id , nos retorna null si
     * no lo encuentra en la DB.
     *
     * @param id
     * @return ItemOrdenDTO
     */
    @Transactional(readOnly = true)
    public ItemOrdenDTO buscarItemOrdenPorId(Long id) {

        log.debug("Peticion para buscar un item de orden con id :" + id);

        return this.itemOrdenRepositorio.findById(id).map(ItemOrdenServicio::mapearADto)
                .orElse(null);

    }
    
    /**
     *
     * @param itemOrdenDTO
     * @return ItemOrdenDTO
     */
    public ItemOrdenDTO crearItemOrden(ItemOrdenDTO itemOrdenDTO) {

        log.debug("Peticion para crear un item de orden " + itemOrdenDTO.getIdProducto());

        Orden orden = this.ordenRepositorio.findById(itemOrdenDTO.getId())
                .orElseThrow(() -> new IllegalStateException("La orden no existe"));

        Producto producto = this.productoRepositorio.findById(itemOrdenDTO.getIdProducto())
                .orElseThrow(() -> new IllegalStateException("El producto no existe"));

        return mapearADto(this.itemOrdenRepositorio.save(
                new ItemOrden(
                        itemOrdenDTO.getCantidad() ,
                        producto ,
                        orden
                )));
    }
    
    /**
     *Metodo para borrar un item de la orden por el id 
     * @param id
     */
    public void borrarItemOrden(Long id){
    
        log.debug("Peticion para borrar un item de la orden ");
        
        this.itemOrdenRepositorio.deleteById(id);
        
    }
    
    
    /**
     * Metodo estatico para mapear un objeto de tipo entidad "ItemOrden" a un
     * objeto de tipo DTO "ItemOrdenDTO".
     *
     * @param itemOrden
     * @return ItemOrdenDTO
     */
    public static ItemOrdenDTO mapearADto(ItemOrden itemOrden) {

        if (itemOrden != null) {

            return new ItemOrdenDTO(
                    itemOrden.getId(),
                    itemOrden.getCantidad(),
                    itemOrden.getProducto().getId(),
                    itemOrden.getOrden().getId()
            );
        }
        return null;
    }

}
