package festivalcultural;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public Menu() {

    }

    ;

  public String menuAdmin(ArrayList<Persona> list, Grilla grila, Scanner scan) {
        int entrada;
        do {
            System.out.println("Seleccione una de las siguientes opciones:"
                    + "\n 1_ Agregar una persona"
                    + "\n 2_ Eliminar una persona"
                    + "\n 3_ Agregar un taller nuevo"
                    + "\n 4_ Eliminar un taller"
                    + "\n 5_ Agregar una dependecia a un taller"
                    + "\n 6_ Eliminar una dependecia a un taller"
                    + "\n 7_ Mostar Talleres"
                    + "\n 8_ Mostar Dependencias"
                    + "\n 9_ Salir");
            entrada = scan.nextInt();
            switch (entrada) {
                case 1:
                    agregarPersona(list, scan);
                    break;
                case 2:
                    caseDos(list, scan);
                    break;
                case 3:
                    agregarTaller(grila, scan);
                    break;
                case 4:
                    caseCuatro(grila, scan);
                    break;
                case 5:
                    agregarDependencia(grila, scan);
                    break;
                case 6:
                    caseSeis(grila, scan);
                    break;
                case 7:
                    imprimirTalleres(grila);
                    break;
                case 8:
                    imprimirDependencias(grila);
                    break;
                case 9:
                    return "9";
            }
        } while (entrada != 9);
        return "9";
    }

    ;

  /**
   * Muestra un menú para que el usuario seleccione una persona almacenada y la
   * elimine
   * 
   * @param list Lista de personas disponibles.
   * @param scan Objeto Scanner para entrada por teclado.
   * 
   * @author Ariel Ismael Miranda Salmin
   */
  public void caseDos(ArrayList<Persona> personas, Scanner scan) {
        if (personas.size() < 1) {
            System.out.println("No hay personas suficientes para hacer la operacion");
            return;
        }

        System.out.println("Seleccione una persona");
        int resul;

        // Seleccionar una persona a eliminar
        do {
            resul = seleccionarPersona(personas, scan);
            if (resul >= 0) {
                System.out.println("La siguiente persana fue eliminada:");
                System.out.println(personas.remove(resul));
            }
        } while (resul == -2);

        if (resul == -1) {
            System.out.println("Se cancelara la operacion");
            return;
        }
    }

    /**
     * Muestra una lista de personas disponibles y permite seleccionar uno o
     * cancelar la selección
     *
     * @param lista Lista de personas a mostrar.
     * @param scan Objeto Scanner para la entrada del usuario.
     * @return índice del taller seleccionado, -1 si se selecciona "Salir", -2
     * si la entrada es inválida.
     *
     * @author Ariel Ismael Miranda Salmin
     */
    public int seleccionarPersona(ArrayList<Persona> list, Scanner scane) {
        Scanner scan=new Scanner(System.in);
        try {
            int i = 0;
            for (Persona persona : list) {
                System.out.println(i + "_ " + persona.toString());
                i++;
            }
            System.out.println(i + "_ Salir");
            int valor = Integer.parseInt(scan.nextLine());
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

  public void caseCuatro(Grilla grilla, Scanner scane) {
              Scanner scan=new Scanner(System.in);

        if (grilla.getTalleres().size() < 1) {
            System.out.println("No hay talleres para eliminar");
        }

        System.out.println("Seleccione una dependencia");
        int resul;

        // Seleccionar un taller para eliminar
        do {
            resul = seleccionarTaller(grilla.getTalleres(), scan);
            if (resul >= 0) {
                System.out.println("El siguiente taller fue eliminadd:");
                Taller taller = grilla.getTalleres().remove(resul);
                eliminarDependenciasDeTaller(grilla, taller);
                System.out.println(taller);
            }
        } while (resul == -2);

        if (resul == -1) {
            System.out.println("Se cancelara la operacion");
            return;
        }
    }

    ;
  public void eliminarDependenciasDeTaller(Grilla grilla, Taller taller) {
        for (int i = grilla.getDependencias().size() - 1; i >= 0; i--) {
            Dependencia depen = grilla.getDependencias().get(i);
            if (depen.getTallerOne().equals(taller) || depen.getTallerTwo().equals(taller)) {
                grilla.getDependencias().remove(i);
            }
        }
    }

    public int seleccionarTaller(ArrayList<Taller> list, Scanner scan) {
        try {
            int i = 0;
            for (Taller taller : list) {
                System.out.println(i + "_ " + taller.toString());
                i++;
            }
            System.out.println(i + "_ Salir");
            int valor = Integer.parseInt(scan.nextLine());
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

  public void caseSeis(Grilla grilla, Scanner scane) {
              Scanner scan=new Scanner(System.in);

        if (grilla.getDependencias().size() < 1) {
            System.out.println("No hay dependencias para eliminar");
        }

        System.out.println("Seleccione una dependencia");
        int resul;

        // Seleccionar una dependencia a eliminar
        do {
            resul = seleccionarDependencia(grilla.getDependencias(), scan);
            if (resul >= 0) {
                System.out.println("La siguiente dependencia fue eliminada:");
                System.out.println(grilla.getDependencias().remove(resul));
            }
        } while (resul == -2);

        if (resul == -1) {
            System.out.println("Se cancelara la operacion");
            return;
        }
    }

    ;

  public int seleccionarDependencia(ArrayList<Dependencia> list, Scanner scan) {
        try {
            int i = 0;
            for (Dependencia dependencia : list) {
                System.out.println(i + "_ " + dependencia.toString());
                i++;
            }
            System.out.println(i + "_ Salir");
            int valor = Integer.parseInt(scan.nextLine());
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

  public void agregarPersona(ArrayList<Persona> list, Scanner scan) {
      Scanner scane=new Scanner(System.in);
        System.out.println("Ingrese un nombre");
        String nombre;
        nombre = scane.nextLine();
        Persona perso = new Persona(nombre);
        list.add(perso);
    }

    public void agregarTaller(Grilla gr, Scanner scan) {
                Scanner scanner=new Scanner(System.in);

        System.out.println("Ingrese un nombre para taller");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese un lugar");
        String lugar = scanner.nextLine();
        LocalTime horaInicio = null;
        LocalTime horaFin = null;
        while (true) {
            System.out.print("Ingrese hora de inicio (HH:mm): ");
            try {
                horaInicio = LocalTime.parse(scanner.nextLine().trim());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato invalido. Use HH:mm (por ejemplo, 14:30)");
            }
        }

        while (true) {
            System.out.print("Ingrese hora de fin (HH:mm): ");
            try {
                horaFin = LocalTime.parse(scanner.nextLine().trim());
                if (horaFin.isAfter(horaInicio)) {
                    break;
                } else {
                    System.out.println("La hora de fin debe ser posterior a la de inicio");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato invalido. Use HH:mm (por ejemplo, 15:30)");
            }
        }

        boolean agregado = gr.addTaller(nombre, horaInicio, horaFin, lugar);
        if (agregado) {
            System.out.println("Taller agregado con exito");
        } else {
            System.out.println("No se pudo agregar el taller. Posiblemente ya exista uno con ese nombre");
        }
    }

    public void agregarDependencia(Grilla gr, Scanner scane) {
        Scanner scan=new Scanner(System.in);

        String one, two;
        Double distancia;
        do {
            int eleccion = 0;
            int i = 0;
            do {
                System.out.println("Eliga el primer taller");
                i = 0;

                for (Taller tallere : gr.getTalleres()) {
                    System.out.println(i + "- " + tallere.getNombre());
                    i++;

                }
                eleccion = scan.nextInt();
                if (eleccion < 0 || eleccion > i - 1) {
                    System.out.println("-------------------------------------------");
                    System.out.println("Ingrese un valor correcto entre 0 y " + i--);
                } else {
                    one = gr.getTalleres().get(eleccion).getNombre();
                    break;
                }

            } while (true);
            do {
                System.out.println("Eliga el segundo taller");
                i = 0;
                for (Taller tallere : gr.getTalleres()) {
                    System.out.println(i + "- " + tallere.getNombre());
                    i++;
                }
                eleccion = scan.nextInt();
                if (eleccion < 0 || eleccion > i - 1) {
                    System.out.println("-------------------------------------------");
                    System.out.println("Ingrese un valor correcto entre 0 y " + i--);
                } else {
                    two = gr.getTalleres().get(eleccion).getNombre();
                    break;
                }

            } while (true);
            if (one.equals(two)) {
                System.out.println("Ambos talleres son iguales, eliga talleres distintos");
            }
        } while (one.equals(two));
        do {
            System.out.println("Indique una distancia");
            distancia = scan.nextDouble();
            if (distancia < 0) {
                System.out.println("Eliga una distancia mayor a 0");
            } else {

                break;
            }
        } while (true);
        if (gr.addDependencia(one, two, distancia)) {
            System.out.println("Se agrego correctamente la dependencia");
        } else {
            System.out.println("No se pudo agregar la dependencia, seguro sos feo");
        }
    }

    public void imprimirTalleres(Grilla gr) {
        System.out.println("Lista de talleres: ");
        gr.imprimirListaTalleres(gr.getTalleres());
        System.out.println("");

    }

    public void imprimirDependencias(Grilla gr) {
        System.out.println("Lista de Dependencias: ");
        if (gr.getDependencias().isEmpty()) {
            System.out.println("No hay dependencias");
        } else {
            for (Dependencia dependencia : gr.getDependencias()) {
                String one = dependencia.getTallerOne().getNombre();
                String two = dependencia.getTallerTwo().getNombre();
                System.out.println(". " + one + " --> " + two);
            }
        }
        System.out.println("");
    }
}
