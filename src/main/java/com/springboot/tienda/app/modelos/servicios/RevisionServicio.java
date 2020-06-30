package com.springboot.tienda.app.modelos.servicios;

import com.springboot.tienda.app.modelos.dtos.ProductoDTO;
import com.springboot.tienda.app.modelos.dtos.RevisionDTO;
import com.springboot.tienda.app.modelos.entidades.Revision;
import com.springboot.tienda.app.modelos.repositorios.RevisionRepositorio;
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
public class RevisionServicio {
    
    private final RevisionRepositorio revisionRepositorio ;
    
    /**
     *Metodo que nos devuelve la lista de todos las revisiones y los parametriza a objetos de tipo RevisionDTO .
     * 
     * @return List RevisionDTO 
     */
    public List<RevisionDTO> listarRevisiones(){
    
        log.debug("Peticion para listar todas las revisiones");
        
        return this.revisionRepositorio.findAll().stream()
                .map(RevisionServicio::mapearADto)
                .collect(Collectors.toList());
    }
    
    /**
     *Metodo para buscar una revision por su Id , retorna null si no encuentra la revision en la DB.
     * @param id
     * @return RevisionDTO
     */
    @Transactional (readOnly = true)
    public RevisionDTO buscarRevisionPorId(Long id){
    
        log.debug("Peticion para buscar una revision por el id :" +id );
        
        return this.revisionRepositorio.findById(id).map(RevisionServicio::mapearADto)
                .orElse(null);
    }
    
    /**
     *Metodo para crear una revision en la base de datos , y nos retorna un Objeto DTO .
     * 
     * @param revisionDTO
     * @return RevisionDTO
     */
    public RevisionDTO crearRevision (RevisionDTO revisionDTO){
        
        log.debug("Peticion para crear una nueva Revision");
        
        return mapearADto(this.revisionRepositorio.save(new Revision(
                
                revisionDTO.getTitulo() ,
                revisionDTO.getDescripcion() ,
                revisionDTO.getValoracion() 
        
        
        )));
        
    
    }
    
    /**
     *Metodo para borrar una revision de la Base de datos .
     * @param id
     */
    public void borrarRevision(Long id){
    
        log.debug("Peticion para borrar una Revision con id : "+ id);
        this.revisionRepositorio.deleteById(id);
    
    }
    
    
    
     /**
     *
     * Metodo estatico para mapear un objeto de tipo entidad "Revision" a un objeto de tipo DTO "RevisionDTO".
     * 
     * @param producto
     * @return ProductoDto
     */
    public static RevisionDTO mapearADto(Revision revision) {

        if (revision != null) {

            return new RevisionDTO(
                    revision.getId(),
                    revision.getTitulo(),
                    revision.getDescripcion(),
                    revision.getValoracion()
            );
        
        }

        return null;
    }
}
