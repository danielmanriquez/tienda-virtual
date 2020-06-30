package com.springboot.tienda.app.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "orden")
public class Orden extends EntidadAbstracta {
    
    @NotNull
    @Column(name ="precio_total" , precision = 10 , scale= 2 , nullable= false)
    private BigDecimal precioTotal;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_carro" , nullable = false)
    private EstadoOrden estadoOrden ;
    
    @Column(name ="enviada")
    private ZonedDateTime enviada ;
    
    @OneToOne
    @JoinColumn(unique = true)
    private Pago pago;
    
    @Embedded
    private Direccion direccionEnvio;
    
    @OneToMany(mappedBy = "orden")
    @JsonIgnore
    private Set<ItemOrden> itemsOrden;
    
    @OneToOne(mappedBy = "orden")
    @JsonIgnore
    private Carro carro ;
    
    
    
    
    
    
    
    
}
