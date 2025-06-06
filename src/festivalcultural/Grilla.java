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
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * *menu para Agregar un taller a una persona
     *
     * @author Juan Cruz Zegarra
     * @param person objeto de tipo persona
     */
    public void menu1(Persona person, Scanner scan) {
        System.out.println("¿Que taller desea hacer?");
        int i = 0;
        if (talleres.isEmpty()) {
            System.out.println("No hay talleres");
            return;
        }
        for (Taller tallere : talleres) {
            System.out.println(i + "-" + tallere);
            i++;

        }
        int eleccion = scan.nextInt();
        while (eleccion < 0 || eleccion >= talleres.size()) {
            System.out.println("Ingrese un numero valido");
            i = 0;
            for (Taller tallere : talleres) {
                System.out.println(i + "-" + tallere);
                i++;

            }
            eleccion = scan.nextInt();
        }
        Taller elegido = talleres.get(eleccion);
        if (tallerRequeridoPersona(elegido.getNombre(), person)) {
            person.addTaller(elegido);
            System.out.println("Te Incribiste Correctamente a " + elegido.getNombre());
            System.out.println("Queda en : " + elegido.getLugar());
            System.out.println("Empieza: " + elegido.getHoraInicio() + "Termina: " + elegido.getHoraFin());
        } else {
            ArrayList<Taller> requeridos = talleresRequeridosAntes(elegido.getNombre());
            requeridos.removeAll(person.getTalleres());
            System.out.println("Te faltan realizar estos talleres primero: ");
            imprimirListaTalleres(requeridos);
        }

    }

    /**
     * menu para saber que talleres necesita hacer previamente
     *
     * @author Juan Cruz Zegarra
     * @param scan
     */
    public void menu3(Scanner scan) {
        System.out.println("Elegi un taller");
        int i = 0;
        if (talleres.isEmpty()) {
            System.out.println("No hay talleres");
            return;
        }
        for (Taller tallere : talleres) {
            System.out.println(i + "-" + tallere);
            i++;
        }
        int eleccion = scan.nextInt();
        while (eleccion < 0 || eleccion >= talleres.size()) {
            System.out.println("Ingrese un numero valido");
            i = 0;
            for (Taller tallere : talleres) {
                System.out.println(i + "-" + tallere);
                i++;
            }
            eleccion = scan.nextInt();
        }
        Taller elegido = talleres.get(eleccion);

        System.out.println("Para hacer el taller " + elegido.getNombre() + " necesitas hacer estos:");
        ArrayList<Taller> requeridos = talleresRequeridosAntes(elegido.getNombre());
        imprimirListaTalleres(requeridos);

    }

    public boolean tallerRequeridoPersona(String nombre, Persona person) {
        if (nombre == null || nombre.equals("")) {
            System.out.println("Tu eleccion es erronea");
            return false;
        }
        if (person == null) {
            System.out.println("Error con persona");
            return false;
        }
        ArrayList<Taller> requeridos = talleresRequeridosAntes(nombre);
        if (person.getTalleres().containsAll(requeridos)) {
            //System.out.println("Puedes realizar el taller: " + nombre);
            return true;
        } else {
            //requeridos.removeAll(person.getTalleres());
            //System.out.println("Te faltan realizar estos: ");
            //imprimirListaTalleres(requeridos);
            return false;
        }
    }

    public void imprimirListaTalleres(ArrayList<Taller> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay talleres en la lista.");
            return;
        }

        for (Taller t : lista) {
            System.out.println("- " + t.getNombre());
        }
    }

    public ArrayList<Taller> talleresRequeridosAntes(String nombre) {
        Taller tal = buscarTallerXNombre(nombre);

        if (tal == null) {
            System.out.println("El taller " + nombre + " no esta cargado en el sistema");
            return new ArrayList<>();
        }
        HashSet<String> visitados = new HashSet<>();
        ArrayList<Taller> resultado = new ArrayList<>();

        auxRecuTRA(tal, visitados, resultado);

        resultado.remove(tal);//quito el taller que se le pasa
        //si esta al dia con los talleres no necesita ningun otro
        if (resultado.size() == 0) {
            System.out.println("No requeris ningun taller previo, para hacer " + nombre);
        }
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

    public String buscarTalleresSiguientesATaller(Taller tallerInicial) {
        Set<Taller> visitados = new HashSet<>();
        buscarTalleres(tallerInicial, visitados);
        visitados.remove(tallerInicial);

        return visitados.stream()
                .map(Taller::getNombre)
                .collect(Collectors.joining(","));
    }

    private void buscarTalleres(Taller actual, Set<Taller> visitados) {
        for (Dependencia d : dependencias) {
            if (d.getTallerOne().equals(actual)) {
                Taller siguiente = d.getTallerTwo();
                if (visitados.add(siguiente)) {
                    buscarTalleres(siguiente, visitados);
                }
            }
        }
    }
    
    public boolean puedeHacerDosActividades(Persona persona, String nombreOne, String nombreTwo) {
        Persona person = persona;
        Taller tOne = buscarTallerXNombre(nombreOne);
        Taller tTwo = buscarTallerXNombre(nombreTwo);
        if (!Objects.nonNull(tOne) || !Objects.nonNull(tTwo)) {
            System.out.println("Uno de los talleres no existe");
            return false;
        }
        ArrayList<Taller> listaNecesitoTOne = talleresRequeridosAntesSec(tOne.getNombre()); // Talleres que necesito para hacer tOne
        ArrayList<Taller> listaNecesitoTTwo = talleresRequeridosAntesSec(tTwo.getNombre()); // Talleres que necesito para hacer tTwo
        if (tOne.getHoraFin().isBefore(tTwo.getHoraInicio())) {
            if(person.getTalleres().containsAll(listaNecesitoTOne) && person.getTalleres().containsAll(listaNecesitoTTwo)){
                System.out.println("Puede hacer ambas actividades");
                return true;
            }
            if (person.getTalleres().containsAll(listaNecesitoTOne)){ 
                if (!person.getTalleres().contains(tOne)) {
                    person.getTalleres().add(tOne);
                }
                if (person.getTalleres().containsAll(listaNecesitoTTwo)) {
                    System.out.println("Puedes hacer el primer taller, y luego hacer el segundo");
                    return true;
                } else { 
                    System.out.println("Únicamente puede hacer el primer taller");
                    return false;
                }
            }else if (person.getTalleres().containsAll(listaNecesitoTTwo)) { 
                System.out.println("Puede hacer el segundo taller, pero no el primero");
                return false;
            }else{ 
                System.out.println("No puede hacer ninguno de los dos talleres");
                return false;
            }
        } else if (tTwo.getHoraFin().isBefore(tOne.getHoraInicio())){
            if(person.getTalleres().containsAll(listaNecesitoTOne) && person.getTalleres().containsAll(listaNecesitoTTwo)){
                System.out.println("Puede hacer ambas actividades");
                return true;
            }
            if (person.getTalleres().containsAll(listaNecesitoTTwo)){
                if (!person.getTalleres().contains(tTwo)) {
                    person.getTalleres().add(tTwo);
                }
                if (person.getTalleres().containsAll(listaNecesitoTOne)) {
                System.out.println("Puedes hacer el segundo taller, y luego hacer el primero");
                return true;
                }else {
                    System.out.println("Únicamente puede hacer el segundo taller");
                    return false;
                }
                } if (person.getTalleres().containsAll(listaNecesitoTOne)){
                    System.out.println("Puede hacer el primer taller, pero no el segundo");
                    return false;
                }
                System.out.println("No puede hacer ninguno de los dos talleres");
                return false;
            }
            System.out.println("Los talleres se superponen");
            if (person.getTalleres().containsAll(listaNecesitoTOne)) {
                System.out.println(" - Puede hacer el primer taller, pero no el segundo");
            } else if (person.getTalleres().containsAll(listaNecesitoTTwo)) {
                System.out.println(" - Puede hacer el segundo taller, pero no el primero");
            } else {
                System.out.println(" - No puede hacer ninguno de los dos talleres");
            }

        
        return false;
    }


    private ArrayList<Taller> talleresRequeridosAntesSec(String nombre) {
        Taller tal = buscarTallerXNombre(nombre);
        if (tal == null) {
            System.out.println("El taller " + nombre + " no está cargado en el sistema");
            return null;
        }
        HashSet<String> visitados = new HashSet<>();
        ArrayList<Taller> resultado = new ArrayList<>();
        auxRecuTRASec(tal, visitados, resultado);
        resultado.remove(tal);  // Asegura que no incluya el mismo taller
        return resultado;
    }


    private void auxRecuTRASec(Taller taller, HashSet<String> visitados, ArrayList<Taller> resultado) {
        if (visitados.contains(taller.getNombre())) {
            return;
        }
        visitados.add(taller.getNombre());
        for (Dependencia dep : dependencias) {
            if (dep.getTallerTwo().equals(taller)) {
                System.out.println("Dependencia detectada: " + dep.getTallerOne().getNombre() + " → " + dep.getTallerTwo().getNombre());

                Taller previo = dep.getTallerOne();
                auxRecuTRASec(previo, visitados, resultado);

                // Solo agregar si no es igual al taller original
                if (!resultado.contains(previo)) {
                    resultado.add(previo);
                }
            }
        }
    }


}
