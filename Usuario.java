package GestionDeBiblioteca;

import java.util.ArrayList;

public class Usuario {
    String nombre;
    Integer IdUsuario;
    ArrayList<Libro> listaDeLibros=new ArrayList<>();

    Usuario(String nombre, Integer IdUsuario) {
        this.nombre = nombre;
        this.IdUsuario = IdUsuario;
    }

    public void mostrarDatos() {
        System.out.println("nombre: " + nombre);
        System.out.println("IdUsuario: " + IdUsuario);
        if (listaDeLibros.size() == 0){
            System.out.println("Lista de libros en :" + 0);
        } else {
            System.out.println("Los libros prestados :" + listaDeLibros.size());
        }
    }
    
    public void agregarPrestamo(Libro libro) {
        if (listaDeLibros.size() == 3) {
            System.out.println("Esto es lo maximo que puede agregar de libros");
        }
        listaDeLibros.add(libro);
    }

    public void devolverLibro(Libro libro) {
        listaDeLibros.remove(libro);
        System.out.println("El libro ya se devolvio" + listaDeLibros.remove(libro));
    }
    
    public void consultarLibrosPrestados() {
        if (listaDeLibros.size() == 0) {
            System.out.println("No tiene libros prestados");
        } else {
            listaDeLibros.forEach(libro -> {
                System.out.print(Biblioteca.findIndex(libro.titulo) + 1 + " ");
                System.out.println(libro.titulo);
            });
        }
    }
}