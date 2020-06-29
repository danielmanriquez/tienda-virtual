package com.springboot.tienda.app.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *Clase abstracta que define los atributos comunes de las entidades.
 * 
 * 
 * @author Daniel
 */


@Getter 
@Setter // Usamos las anotaciones de lombok para generar los metodos getters y setters.
@MappedSuperclass // Anotamos esta clase base que contiene los atributos que van a ser heredados
@EntityListeners(AuditingEntityListener.class) // los campos anotados se completarán automáticamente en función de la anotación correspondiente por el
public abstract class EntidadAbstracta  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @CreatedDate
    @Column(name = "fecha_creacion"  , nullable = false)
    @JsonIgnore
    private Instant fechaCreacion = Instant.now();
    
    
    @LastModifiedDate
    @Column(name="fecha_ultima_modificacion")
    @JsonIgnore
    private Instant fechaUltimaModificacion = Instant.now();
    
    
    
    
    
    
}
