package org.example.domain.tuberculo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.bicho.Bicho;
import org.example.enums.Estado;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /*
    * El atributo 'mappedBy' debe hacer referencia al nombre del campo de la clase 'Bicho' que representa
    * la relación con 'Tuberculo'. En este caso, tuberculoid.*/
    @OneToMany(mappedBy = "tuberculoid", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Bicho> bichos = new ArrayList<>();

    @Override
    public String toString() {
        return "Tuberculo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha_caducidad=" + fecha_caducidad +
                ", estado=" + estado +
                ", observaciones='" + observaciones + '\'' +
                ", bichos=" + bichos +
                '}';
    }
}
