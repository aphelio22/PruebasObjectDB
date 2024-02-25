package org.example.domain.tuberculo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Estado;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Tuberculo implements Serializable {
    public Tuberculo(Integer id, String nombre, Date fecha_caducidad, Estado estado, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_caducidad = fecha_caducidad;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private Date fecha_caducidad;
    private Estado estado;
    private String observaciones;


    @Override
    public String toString() {
        return "Tuberculo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha_caducidad=" + fecha_caducidad +
                ", estado=" + estado +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
