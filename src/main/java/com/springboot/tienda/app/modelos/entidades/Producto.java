
package com.springboot.tienda.app.modelos.entidades;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "producto")
public class Producto extends EntidadAbstracta {
    
    @NotNull
    @Column( name= "nombre" , nullable = false )
    private String nombre;
    
    @NotNull
    @Column( name= "descripcion" , nullable = false )
    private String descripcion;
    
    @NotNull
    @Column( name= "precio" , nullable = false )
    private BigDecimal precio;
    
    @NotNull
    @Column( name= "cantidad" , nullable = false )
    private Integer cantidad;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_producto" , nullable = false)
    private EstadoProducto estadoProducto;
    
    @Column( name= "contador_ventas" , nullable = false )
    private Integer contadorVentas;
    
    @OneToMany
    private Set<Revision> revisiones = new HashSet <>();
    
    @ManyToOne
    private Categoria categoria;
    
    
    
}
