
package com.springboot.tienda.app.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagoDTO {
    
    private Long id;
    private String paypalPagoId;
    private String estadoPago;
    private Long idOrden;
    
    
}
