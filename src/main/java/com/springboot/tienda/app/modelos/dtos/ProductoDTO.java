
package com.springboot.tienda.app.modelos.dtos;

import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    
    private Long id ;
    private String nombre ;
    private String descripcion ;
    private BigDecimal precio ;
    private Integer cantidad ;
    private String estadoProducto ;
    private Integer contadorVentas ;
    private Set<RevisionDTO> revisiones ;
    private CategoriaDTO categoria ;
    
}
