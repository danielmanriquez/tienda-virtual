package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.ProductoDTO;
import com.springboot.tienda.app.modelos.entidades.EstadoProducto;
import com.springboot.tienda.app.modelos.entidades.Producto;
import com.springboot.tienda.app.modelos.repositorios.CategoriaRepositorio;
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
public class ProductoServicio {
    
    private final ProductoRepositorio productoRepositorio ;
    
    
    private final CategoriaRepositorio categoriaRepositorio ;
    
    /**
     *Metodo que nos devuelve la lista de todos los productos y los parametriza a objetos de tipo ProductoDTO .
     * 
     * @return List ProductoDTO 
     */
    public List<ProductoDTO> listarProductos(){
    
        log.debug("Peticion para listar todos los productos");
        
        return this.productoRepositorio.findAll().stream()
                .map(ProductoServicio::mapearADto)
                .collect(Collectors.toList());
    }
    
    /**
     *Metodo para buscar un producto por su Id , retorna null si no encuentra el producto en la DB.
     * @param id
     * @return ProductoDTO
     */
    @Transactional (readOnly = true)
    public ProductoDTO buscarProductoPorId(Long id){
    
        log.debug("Peticion para buscar un producto por el id :" +id );
        
        return this.productoRepositorio.findById(id).map(ProductoServicio::mapearADto)
                .orElse(null);
    }
    
    /**
     *Metodo para crear un producto en la base de datos , y nos retorna un Objeto DTO .
     * 
     * @param productoDTO
     * @return ProductoDTO
     */
    public ProductoDTO crearProducto(ProductoDTO productoDTO){
    
        log.debug("Peticion para crear un nuevo producto");
        
        return mapearADto(this.productoRepositorio.save(new Producto(
                productoDTO.getNombre() ,
                productoDTO.getDescripcion() ,
                productoDTO.getPrecio() ,
                productoDTO.getCantidad() ,
                EstadoProducto.valueOf(productoDTO.getEstadoProducto()) ,
                productoDTO.getContadorVentas() ,
               null ,
                this.categoriaRepositorio.findById(productoDTO.getId()).orElse(null)
                
        
        )));
    }
    
    /**
     *Metodo para borrar un producto de la Base de datos .
     * @param id
     */
    public void borrarCategoria (Long id ){
    
        log.debug("Peticion para borrar un producto con id :" + id);
        
        this.productoRepositorio.deleteById(id);
    }
    
    /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Producto" a un objeto de tipo DTO "ProductoDTO".
     * 
     * @param producto
     * @return ProductoDto
     */
    public static ProductoDTO mapearADto(Producto producto) {
        
        if (producto != null) {
            
            return new ProductoDTO(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad(),
                    producto.getEstadoProducto().name(),
                    producto.getContadorVentas(),
                    producto.getRevisiones().stream()
                            .map(RevisionServicio::mapearADto)
                            .collect(Collectors.toSet()),
                    CategoriaServicio.mapearADto(producto.getCategoria())
            );
            
        }
        
        return null;
    }
}
