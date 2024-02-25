package org.example;

import org.example.domain.tuberculo.Tuberculo;
import org.example.domain.tuberculo.TuberculoDAO;
import org.example.enums.Estado;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Tuberculo tuberculo = new Tuberculo();
        tuberculo.setNombre("Patata");
        try {
            tuberculo.setFecha_caducidad(simpleDateFormat.parse("2024-04-23"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        tuberculo.setEstado(Estado.bueno);
        tuberculo.setObservaciones("Las patatas están súper buenas");

        TuberculoDAO tuberculoDAO = new TuberculoDAO();
        tuberculoDAO.save(tuberculo);
        System.out.println(tuberculoDAO.getAll());

        Tuberculo tuberculo2 = new Tuberculo();
        tuberculo2.setNombre("Zanahoria");
        try {
            tuberculo2.setFecha_caducidad(simpleDateFormat.parse("2024-06-23"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        tuberculo2.setEstado(Estado.malo);
        tuberculo2.setObservaciones("Las zanahorias están súper malas");

        tuberculoDAO.save(tuberculo2);
        System.out.println(tuberculoDAO.get(2));

        tuberculo2.setNombre("Rábano");
        tuberculo2.setObservaciones("Los rábanos están súper malos");

        tuberculoDAO.update(tuberculo2);

        System.out.println(tuberculoDAO.getAll());

        tuberculoDAO.delete(tuberculo);

        System.out.println(tuberculoDAO.getAll());
    }
}