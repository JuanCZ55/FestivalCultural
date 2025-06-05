/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package festivalcultural;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author juancz55
 */
public class Grilla {

    private ArrayList<Taller> talleres;
    private ArrayList<Dependencia> dependencias;

    public Grilla() {

    }

    public boolean addTaller(String nombre, LocalTime horaInicio, LocalTime horaFin, String lugar) {
        if (buscarTallerXNombre(nombre) != null) {
            System.out.println("Ya esta cargado ese taller");
            return false;
        }
        Taller taller = new Taller(nombre, horaInicio, horaFin, lugar);
        return talleres.add(taller);

    }

    public boolean addDependencia(String one, String two, Double distancia) {
        Taller origen = buscarTallerXNombre(one);
        Taller destino = buscarTallerXNombre(two);
        if (origen != null && destino != null) {
            Dependencia depen = new Dependencia(origen, destino, distancia);
            return dependencias.add(depen);
        }
        if (origen != null) {
            System.out.println("El taller " + one + " no existe, agregalo");
        }
        if (destino != null) {
            System.out.println("El taller " + two + " no existe, agregalo");
        }
        return false;
    }

    private Taller buscarTallerXNombre(String nombre) {
        for (Taller tallere : talleres) {
            if (tallere.getNombre().equals(nombre)) {
                return tallere;
            }
        }
        return null;
    }

    private Dependencia buscarArcoXNombre(Taller one, Taller two) {
        for (Dependencia depen : dependencias) {
            if (depen.getTallerOne().equals(one) && depen.getTallerTwo().equals(two)) {
                return depen;
            }
        }
        return null;
    }
}
