
package com.springboot.tienda.app.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemOrdenDTO {
    
    private Long id;
    private Long cantidad;
    private Long idProducto;
    private Long idOrden;
    
}
