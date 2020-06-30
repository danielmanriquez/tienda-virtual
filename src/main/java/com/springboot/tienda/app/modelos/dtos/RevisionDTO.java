
package com.springboot.tienda.app.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevisionDTO {
    
    private Long id ;
    private String titulo ; 
    private String descripcion ;
    private Long valoracion ;
    
}
