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

    /*----------------------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------------------*/
    /**
     * addTaller(): Agrega un taller si no existe otro con el mismo nombre.
     *
     * @param nombre nombre del taller a agregar
     * @param horaInicio hora de inicio del taller
     * @param horaFin hora de finalizacion del taller
     * @param lugar lugar donde se dicta el taller
     * @return true si se agrego correctamente, false si ya existia uno con ese
     * nombre
     * @author Juan Cruz Zegarra
     *
     */
    public boolean addTaller(String nombre, LocalTime horaInicio, LocalTime horaFin, String lugar) {
        if (buscarTallerXNombre(nombre) != null) {
            System.out.println("Ya esta cargado ese taller");
            return false;
        }
        Taller taller = new Taller(nombre, horaInicio, horaFin, lugar);
        return talleres.add(taller);

    }

    /**
     * addDependencia(): Agrega una dependencia entre dos talleres si ambos
     * existen.
     *
     * @param one nombre del taller origen
     * @param two nombre del taller destino
     * @param distancia distancia entre los dos talleres
     * @return true si se agrego correctamente, false si alguno de los talleres
     * no existe
     * @author Juan Cruz Zegarra
     */
    public boolean addDependencia(String one, String two, Double distancia) {
        Taller origen = buscarTallerXNombre(one);
        Taller destino = buscarTallerXNombre(two);
        if (origen != null && destino != null) {
            Dependencia depen = new Dependencia(origen, destino, distancia);
            return dependencias.add(depen);
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
     * tallerRequeridoPersona(): Verifica si una persona cumple con los talleres
     * requeridos antes de hacer uno nuevo.
     *
     * @param nombre nombre del taller que se desea realizar
     * @param person persona que desea realizar el taller
     * @return true si la persona cumple con todos los talleres requeridos,
     * false en caso contrario
     * @author Juan Cruz Zegarra
     */
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
            return true;
        } else {
           
            return false;
        }
    }


    /**
     * talleresRequeridosAntes(): Obtiene la lista de talleres requeridos antes
     * de uno dado.
     *
     * @param nombre nombre del taller a consultar
     * @return lista de talleres que deben realizarse antes del taller indicado
     * @author Juan Cruz Zegarra
     */
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

    /**
     * buscarTallerXNombre(): Busca un taller en la lista por su nombre.
     *
     * @param nombre nombre del taller a buscar
     * @return el taller encontrado o null si no existe
     * @author Juan Cruz Zegarra
     */
    private Taller buscarTallerXNombre(String nombre) {
        for (Taller tallere : talleres) {
            if (tallere.getNombre().equals(nombre)) {
                return tallere;
            }
        }
        return null;
    }

    
    /**
     * imprimirListaTalleres(): Imprime los nombres de los talleres en la lista.
     *
     * @param lista lista de talleres a imprimir
     * @author Juan Cruz Zegarra
     */
    public void imprimirListaTalleres(ArrayList<Taller> lista) {
        if (lista == null || lista.isEmpty()) {
            return;
        }

        for (Taller t : lista) {
            System.out.println("- " + t.getNombre());
        }
    }
    /**
     * buscarArcoXNombre(): Busca una dependencia entre dos talleres
     * especificos.
     *
     * @param one taller origen de la dependencia
     * @param two taller destino de la dependencia
     * @return la dependencia encontrada o null si no existe
     * @author Juan Cruz Zegarra
     */
    private Dependencia buscarArcoXNombre(Taller one, Taller two) {
        for (Dependencia depen : dependencias) {
            if (depen.getTallerOne().equals(one) && depen.getTallerTwo().equals(two)) {
                return depen;
            }
        }
        return null;
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
        ArrayList<Taller> requeridos = talleresRequeridosAntes(elegido.getNombre());

        if (requeridos.size() > 0) {

            System.out.println("Para hacer el taller " + elegido.getNombre() + " necesitas hacer estos:");
            imprimirListaTalleres(requeridos);
        }

    }

    /*----------------------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------------------*/
    /**
     * Verifica si una persona puede realizar dos talleres en función de sus
     * horarios y de los requisitos previos necesarios para cada taller.
     *
     * Si los horarios no se superponen y la persona ha cumplido con los
     * requisitos previos de ambos talleres para ver si puede hacer ambos, uno o
     * ninguno.
     *
     * @param persona Persona que desea realizar los talleres.
     * @param nombreOne Nombre del primer taller.
     * @param nombreTwo Nombre del segundo taller.
     * @return true si puede hacer ambos talleres (en cualquier orden válido),
     * false en caso contrario.
     *
     * @author Ariel Ismael Miranda Salmin
     */
    public boolean puedeHacerDosActividades(Persona persona, String nombreOne, String nombreTwo) {
        Persona person = persona;
        Taller tOne = buscarTallerXNombre(nombreOne);
        Taller tTwo = buscarTallerXNombre(nombreTwo);
        if (!Objects.nonNull(tOne) || !Objects.nonNull(tTwo)) {
            System.out.println("\nUno de los talleres no existe");
            return false;
        }
        ArrayList<Taller> listaNecesitoTOne = talleresRequeridosAntesSec(tOne.getNombre()); // Talleres que necesito para hacer tOne
        ArrayList<Taller> listaNecesitoTTwo = talleresRequeridosAntesSec(tTwo.getNombre()); // Talleres que necesito para hacer tTwo
        if (tOne.getHoraFin().isBefore(tTwo.getHoraInicio())) {
            if (person.getTalleres().containsAll(listaNecesitoTOne) && person.getTalleres().containsAll(listaNecesitoTTwo)) {
                System.out.println("\nPuede hacer ambas actividades");
                return true;
            }
            if (person.getTalleres().containsAll(listaNecesitoTOne)) {
                if (!person.getTalleres().contains(tOne)) {
                    person.getTalleres().add(tOne);
                }
                if (person.getTalleres().containsAll(listaNecesitoTTwo)) {
                    System.out.println("\nPuedes hacer el primer taller, y luego hacer el segundo");
                    return true;
                } else {
                    System.out.println("\nÚnicamente puede hacer el primer taller");
                    return false;
                }
            } else if (person.getTalleres().containsAll(listaNecesitoTTwo)) {
                System.out.println("\nPuede hacer el segundo taller, pero no el primero");
                return false;
            } else {
                System.out.println("\nNo puede hacer ninguno de los dos talleres");
                return false;
            }
        } else if (tTwo.getHoraFin().isBefore(tOne.getHoraInicio())) {
            if (person.getTalleres().containsAll(listaNecesitoTOne) && person.getTalleres().containsAll(listaNecesitoTTwo)) {
                System.out.println("\nPuede hacer ambas actividades");
                return true;
            }
            if (person.getTalleres().containsAll(listaNecesitoTTwo)) {
                if (!person.getTalleres().contains(tTwo)) {
                    person.getTalleres().add(tTwo);
                }
                if (person.getTalleres().containsAll(listaNecesitoTOne)) {
                    System.out.println("\nPuedes hacer el segundo taller, y luego hacer el primero");
                    return true;
                } else {
                    System.out.println("\nÚnicamente puede hacer el segundo taller");
                    return false;
                }
            }
            if (person.getTalleres().containsAll(listaNecesitoTOne)) {
                System.out.println("\nPuede hacer el primer taller, pero no el segundo");
                return false;
            }
            System.out.println("\nNo puede hacer ninguno de los dos talleres");
            return false;
        }
        System.out.println("\nLos talleres se superponen");
        if (person.getTalleres().containsAll(listaNecesitoTOne)) {
            System.out.println(" - Puede hacer el primer taller, pero no el segundo");
        } else if (person.getTalleres().containsAll(listaNecesitoTTwo)) {
            System.out.println(" - Puede hacer el segundo taller, pero no el primero");
        } else {
            System.out.println(" - No puede hacer ninguno de los dos talleres");
        }

        return false;
    }

    /**
     * talleresRequeridosAntesSec(): Obtiene la lista de talleres requeridos
     * antes de uno dado. Metodo modificado de talleresRequeridosAntes():
     *
     * @param nombre nombre del taller a consultar
     * @return lista de talleres que deben realizarse antes del taller indicado
     * @author Juan Cruz Zegarra
     * @author Ariel Ismael Miranda Salmimn
     */
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

    /**
     * Muestra un menú para que el usuario seleccione dos talleres y consulta si
     * la persona puede realizarlos ambos
     *
     * @param persona Persona que desea consultar sobre los talleres.
     * @param lista Lista de talleres disponibles.
     * @param scan Objeto Scanner para entrada por teclado.
     *
     * @author Ariel Ismael Miranda Salmin
     */
    public void caseDos(Persona persona, ArrayList<Taller> lista, Scanner scan) {
        ArrayList<Taller> talleres = new ArrayList<>(lista);
        if (talleres.size() < 2) {
            System.out.println("\nNo hay talleres suficientes para hacer la consulta");
            return;
        }

        Taller[] selec = new Taller[2];

        System.out.println("Seleccione dos talleres, para ver si se pueden realizar:");

        int resul;

        // Seleccionar el primer taller
        do {
            System.out.println("\nPrimer taller: ");
            resul = seleccionarTaller(talleres, scan);
            if (resul >= 0) {
                selec[0] = talleres.get(resul);
                talleres.remove(resul);
            }
        } while (resul == -2);

        if (resul == -1 || selec[0] == null) {
            System.out.println("\nSe cancelara la seleccion");
            return;
        }

        // Seleccionar el segundo taller
        do {
            System.out.println("\nSegundo taller: ");
            resul = seleccionarTaller(talleres, scan);
            if (resul >= 0) {
                selec[1] = talleres.get(resul);
                talleres.remove(resul);
            }
        } while (resul == -2);

        if (Objects.nonNull(selec[1])) {
            puedeHacerDosActividades(persona, selec[0].getNombre(), selec[1].getNombre());
        } else {
            System.out.println("\nSe cancelara la seleccion");
        }
    }

    ;


    /**
     * Muestra una lista de talleres disponibles y permite uno o cancelar la selección
     *
     * @param lista Lista de talleres a mostrar.
     * @param scan  Objeto Scanner para la entrada del usuario.
     * @return índice del taller seleccionado, -1 si se selecciona "Salir", -2 si la entrada es inválida.
     * 
     * @author Ariel Ismael Miranda Salmin
     */
    public int seleccionarTaller(ArrayList<Taller> lista, Scanner scan) {
        try {
            int i = 0;
            for (Taller taller : lista) {
                System.out.println(i + "_ " + taller.toString());
                i++;
            }
            System.out.println(i + "_ Salir");
            int valor = scan.nextInt();
            if (valor > i || valor < 0) {
                System.out.println("Seleccione una de las opciones");
                return -2;
            } else if (valor == i) {
                return -1;
            } else {
                return valor;
            }
        } catch (NumberFormatException e) {
            System.out.println("No es un número válido.");
            return -2;
        }
    }

    ;

    public ArrayList<Taller> getTalleres() {
        return talleres;
    }

    public ArrayList<Dependencia> getDependencias() {
        return dependencias;
    }

    /*----------------------------------------------------------------------------------------*/
 /*----------------------------------------------------------------------------------------*/
    /**
     * Esta funcion busca todos los talleres que dependen de un taller dado El
     * funcionamiento de este metodo es simple, a partir de un taller que el
     * usuario selecciona se llama a buscarTalleres, al cual se le pasa el
     * taller seleccionado y un Set de talleres vacio el buscarTalleres, lo que
     * hace es iterar sobre la lista de dependencias hasta encontrar el taller
     * actual, una vez que lo encuentra, se saca el taller a donde apunta el
     * arco (ya que las dependencias son los arcos) se agrega este taller al set
     * de visitados y se llama recursivamente a la funcion con este nuevo taller
     * una ves que finalizo, removemos el taller inicial del SET, ya que no nos
     * interesa y luego transformamos el set en una lista para poder iterar
     * sobre el listado y mostrarlo
     *
     * @param tallerInicial taller al cual voy a fijarme que otros talleres
     * dependen del mismo
 * *
     */
    public ArrayList<Taller> buscarTalleresSiguientesATaller(Taller tallerInicial) {
        Set<Taller> visitados = new HashSet<>();
        buscarTalleres(tallerInicial, visitados);
        visitados.remove(tallerInicial);
        return new ArrayList<>(visitados);
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

    /**
     * Menu para seleccionar un taller y a partir de ese taller ver que mas se
     * puede hacer *
     */
    public void menu4(Scanner scan) {
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


        ArrayList<Taller> siguientes = buscarTalleresSiguientesATaller(elegido);
        String res = siguientes.size() > 0 ? "A partir de este taller " + elegido.getNombre() + " usted puede hacer estos:" : "No se pueden hacer mas talleres";
        System.out.println(res);
        imprimirListaTalleres(siguientes);
    }

}
