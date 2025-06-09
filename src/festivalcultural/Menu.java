package festivalcultural;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
  public Menu() {

  };

  public String menuAdmin(ArrayList<Persona> list, Grilla grila, Scanner scan) {
    String entrada;
    do {
      System.out.println("Seleccione una de las siguientes opciones:"
          + "\n 1_ Agregar una persona"
          + "\n 2_ Eliminar una persona"
          + "\n 3_ Agregar un taller nuevo"
          + "\n 4_ Eliminar un taller"
          + "\n 5_ Agregar una dependecia a un taller"
          + "\n 6_ Eliminar una dependecia a un taller"
          + "\n 7_ Salir");
      entrada = scan.nextLine();
      switch (entrada) {
        case "1":
          break;
        case "2":
          caseDos(list, scan);
          break;
        case "3":
          break;
        case "4":
          caseCuatro(grila, scan);
          break;
        case "5":
          break;
        case "6":
          caseSeis(grila, scan);
          break;
        case "7":
          return "7";
      }
      ;
    } while (entrada != "7");
    return "7";
  };

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
   * Muestra una lista de personas disponibles y permite seleccionar uno o cancelar la
   * selección
   *
   * @param lista Lista de personas a mostrar.
   * @param scan  Objeto Scanner para la entrada del usuario.
   * @return índice del taller seleccionado, -1 si se selecciona "Salir", -2 si la
   *         entrada es inválida.
   * 
   * @author Ariel Ismael Miranda Salmin
   */
  public int seleccionarPersona(ArrayList<Persona> list, Scanner scan) {
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
  };

  public void caseCuatro(Grilla grilla, Scanner scan) {
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
  };
  public void eliminarDependenciasDeTaller(Grilla grilla, Taller taller) {
    for (int i = grilla.getDependencias().size() - 1; i >= 0; i--) {
        Dependencia depen =  grilla.getDependencias().get(i);
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
  };

  public void caseSeis(Grilla grilla, Scanner scan) {
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
  };

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
  };

}
