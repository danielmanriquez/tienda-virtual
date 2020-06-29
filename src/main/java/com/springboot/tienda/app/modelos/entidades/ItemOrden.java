/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.tienda.app.modelos.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data // Anotacion para generar Getters y Setters
@NoArgsConstructor // Anotacion para generar un constructor sin argumentos
@AllArgsConstructor // Anotacion para generar un constructor con todos los argumentos
@EqualsAndHashCode(callSuper = false) // Anotacion para generar metodo Equals y Hash , callSuper false para que no llame al metodo de la super clase
@Entity // Anotacion para mapear una clase con una tabla de la base de datos en el contexto de persistencia .
@Table(name = "item_orden")
public class ItemOrden extends EntidadAbstracta {
    
    @NotNull
    @Column (name = "cantidad")
    private Long cantidad;
    
    @ManyToOne
    private Producto producto ;
    
    @ManyToOne
    private Orden orden;
    
    
    
}
