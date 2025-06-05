/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package festivalcultural;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author juancz55
 */
public class Grilla {

    private ArrayList<Taller> talleres;
    private ArrayList<Dependencia> dependencias;

    public Grilla() {
        this.talleres = new ArrayList<>();
        this.dependencias = new ArrayList<>();
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
        if (distancia < 0) {
            System.out.println("Ingresea una distancia mayor a 0");
            return false;
        }
        if (origen == null) {
            System.out.println("El taller " + one + " no existe, agregalo");
        }
        if (destino == null) {
            System.out.println("El taller " + two + " no existe, agregalo");
        }
        return false;
    }

    public ArrayList<Taller> talleresRequeridosAntes(String nombre) {
        Taller tal = buscarTallerXNombre(nombre);

        if (tal == null) {
            System.out.println("El taller " + nombre + " no esta cargado en el sistema");
            return null;
        }
        HashSet<String> visitados = new HashSet<>();
        ArrayList<Taller> resultado = new ArrayList<>();

        auxRecuTRA(tal, visitados, resultado);

        resultado.remove(tal);//quito el taller que se le pasa

        return resultado;

    }

    private void auxRecuTRA(Taller taller, HashSet<String> visitados, ArrayList<Taller> resultado) {
        if (visitados.contains(taller.getNombre())) {
            return;
        }
        visitados.add(taller.getNombre());
        for (Dependencia dep : dependencias) {
            if (dep.getTallerTwo().equals(taller)) {
                Taller previo = dep.getTallerOne();
                auxRecuTRA(previo, visitados, resultado);
            }
        }
        resultado.add(taller);
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
