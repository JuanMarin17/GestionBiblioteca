package GestionDeBiblioteca;
import java.util.Scanner;
import java.util.ArrayList;

public class Biblioteca {
    static ArrayList<Libro> listaLibros = new ArrayList<>();
    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static ArrayList<Prestamo> historialPrestamos = new ArrayList<>();

    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }
    
    public static void menu() {
        while (true) {
            System.out.println("Bienvenido al prestamo de libros");
            System.out.println(
                    "Que deseas hacer hoy \n1 Consultar libros prestados \n2 Devolver libro \n3 Prestar libro \n4 Agregar libro \n5 Agregar usuarios\n6 Consultar prestamos\n7 Consultar usuarios\n8 Salir");
            Integer menuOpcion = scanner.nextInt();
            if (menuOpcion == 1) {
                consultarLibros(); // Esta listo
            } else if (menuOpcion == 2) {
                devolverLibro();
            } else if (menuOpcion == 3) {
                prestarLibro();
            } else if (menuOpcion == 4) {
                agregarLibro(); // Esta listo
            }else if(menuOpcion == 5){
                agregarUsuario(); // Esta listo
            } else if (menuOpcion == 6) {
                historialPrestamos();
            } else if (menuOpcion == 7) {
                consultarUsuarios();
            } else if (menuOpcion == 8) {
                break;
            }
        }
        scanner.close();
    }

    public static void consultarLibros() {
        if (listaLibros.size() == 0) {
            System.out.println("No hay libros en la base de datos");
        } else {
            for (Libro libro : listaLibros) {
                if (libro.disponible == true) {
                    System.out.print(libro.codigo);
                    System.out.println(libro.titulo);
                }
            }
        }
    }
    
    public static void prestarLibro() {
        System.out.println("Que libro le interesaría que le prestarán?");
        consultarLibros();
        System.out.println("Digite el codigo del libro");
        Integer codigo = scanner.nextInt();
        if (codigo == -1) {
            System.out.println("El libro no existe");
        }
        Libro libroPrestamo = listaLibros.get(codigo - 1);
        libroPrestamo.mostrarDatos();
        System.out.println("Ingrese el nombre del usuario que va a prestar el libro");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Integer usuarioPrestamoIndex = findIndexUser(nombre);
        Usuario usuarioPrestamo = listaUsuarios.get(usuarioPrestamoIndex);
        if (usuarioPrestamo.listaDeLibros.size() == 3) {
            System.out.println("No te podemos prestar mas libros");
        } else {
            System.out.print("Que fecha se hizo el prestamo: ");
            String fecha = scanner.nextLine();
            System.out.println("Cuántos dias se va a prestar el libro");
            Integer dias = scanner.nextInt();
            Prestamo prestamo = new Prestamo(fecha, dias, nombre, libroPrestamo.titulo);
            historialPrestamos.add(prestamo);
            System.out.println("El prestamo se ha hecho correctamente");
            usuarioPrestamo.agregarPrestamo(libroPrestamo);
            libroPrestamo.cambiarEstado();
        }
    }
    
    public static void devolverLibro() {
        System.out.print("Nombre del usuario que va a devolver el libros: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Integer userIndex = findIndexUser(nombre);
        if (userIndex == -1){
            System.out.println("El usuario no existe");
        }
        Usuario usuario = listaUsuarios.get(userIndex);
        System.out.println("Este usuario ha prestado los siguientes libro:");
        usuario.consultarLibrosPrestados();
        System.out.println("Ingrese el codigo del libro que desea devolver");
        Integer codigoLibro = scanner.nextInt();
        Libro libroDevolver = listaLibros.get(codigoLibro - 1);
        System.out.println("Esta es la información del libro a devolver");
        libroDevolver.mostrarDatos();
        System.out.println("Cuantos dias demoro en entregar el libro");
        Integer dias = scanner.nextInt();
        Prestamo prestamo = historialPrestamos.get(findIndexPrestamo(libroDevolver.titulo));
        System.out.println("Se devolvio el libro " + prestamo.tituloLibro + " que se le presto al usuario "
                + prestamo.nombreUsuario + " demoro " + dias + " dias en devolverlo, tendra un recargo por atraso de "
                + prestamo.recargoPorRetraso(dias));
        libroDevolver.cambiarEstado();
        usuario.listaDeLibros.remove(codigoLibro - 1);
        System.out.println("Gracias por visitar nuestra biblioteca");
    }
    
    public static void agregarLibro() {
        System.out.println("Ingresar nuevo libro");
        System.out.println("ingrese el titulo del libro");
        scanner.nextLine();
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el autor del libro");
        String autor = scanner.nextLine();
        Libro libro = new Libro(titulo, autor, true, listaLibros.size() + 1);

        listaLibros.add(libro);
    }

    public static void agregarUsuario() {
        System.out.println("Ingresar usuario");
        System.out.println("Ingerese el nombre de usuario");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Integer id = listaUsuarios.size() + 1;
        Usuario usuario = new Usuario(nombre, id);
        listaUsuarios.add(usuario);
    }

    public static Integer findIndex(String titulo) {
        Integer index = -1;
        for (Integer i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).titulo.equals(titulo)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void historialPrestamos() {
        if (historialPrestamos.size() == 0) {
            System.out.println("No se han hecho prestamos");
        } else {
            historialPrestamos.forEach(prestamo -> {
                prestamo.prestamoHecho();
            });
        }
    }
    
    public static void consultarUsuarios() {
        if (listaUsuarios.size() == 0) {
            System.out.println("No hay usuarios en la base de datos");
        } else {
            for (Usuario usuario : listaUsuarios) {
                usuario.mostrarDatos();
            }
        }
    }

    public static Integer findIndexUser(String nombre) {
        Integer index = -1;
        for (Integer i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).nombre.equals(nombre)) {
                index = i;
                break;
            }
        }
        return index;

    }
    
    public static Integer findIndexPrestamo(String titulo) {
        Integer index = -1;
        for (Integer i = 0; i < historialPrestamos.size(); i++) {
            if (historialPrestamos.get(i).tituloLibro.equals(titulo)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
