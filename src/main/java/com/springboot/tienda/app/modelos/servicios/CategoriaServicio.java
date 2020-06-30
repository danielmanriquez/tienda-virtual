package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.CategoriaDTO;
import com.springboot.tienda.app.modelos.entidades.Categoria;
import com.springboot.tienda.app.modelos.repositorios.CategoriaRepositorio;
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
public class CategoriaServicio {
    

    private final CategoriaRepositorio categoriaRepositorio ;
    
     /**
     *Metodo que nos devuelve la lista de todas las categorias y los parametriza a objetos de tipo CategoriaDTO .
     * 
     * @return List CategoriaDTO 
     */
    public List<CategoriaDTO> listarTodasLasCategorias(){
        
        
        log.debug("Peticion que devuelve todas las categorias ");
        return this.categoriaRepositorio.findAll().stream()
                .map(CategoriaServicio::mapearADto)
                .collect(Collectors.toList());
               
    }
    
    /**
     *Metodo para buscar una categoria por su Id , nos lanzara una excepcion del tipo IllegalStateException si 
     * no encuentra la categoria .
     * @param id
     * @return CategoriaDTO
     */
    @Transactional (readOnly = true)
    public CategoriaDTO buscarCategoriaPorId(Long id){
    
        log.debug("Peticion para buscar una categoria por el id :" +id );
        
        return this.categoriaRepositorio.findById(id).map(CategoriaServicio::mapearADto)
                .orElseThrow(IllegalStateException:: new);
    }
    
    /**
     *Metodo para crear una categoria en la base de datos , y nos retorna un Objeto DTO .
     * 
     * @param categoriaDTO
     * @return CategoriaDTO
     */
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO){
    
        log.debug("Peticion para crear una nueva categoria :" + categoriaDTO.getNombre());
        
        
        return mapearADto(categoriaRepositorio.save(new Categoria ( categoriaDTO.getNombre() ,  
                categoriaDTO.getDescripcion() ,
                Collections.emptySet()
        )));
    }
    
    /**
     *Metodo para borrar una categoria de la Base de datos .
     * @param id
     */
    public void borrarCategoria (Long id ){
    
        log.debug("Peticion para borrar una categoria .");
        
        this.categoriaRepositorio.deleteById(id);
    }
   
    /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Categoria" a un objeto de tipo DTO "CategoriaDTO".
     * 
     * @param categoria
     * @return categoriaDto
     */
    public static CategoriaDTO mapearADto(Categoria categoria){
        
        if(categoria != null){
            return new CategoriaDTO(categoria.getId() ,
                    categoria.getNombre() ,
                    categoria.getDescripcion() ,
                    categoria.getProductos().stream()
                    .map(ProductoServicio::mapearADto)
                    .collect(Collectors.toSet())
            );
        }
        return null;
        
    }
    
}
