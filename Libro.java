package GestionDeBiblioteca;

public class Libro {
    String titulo;
    String autor;
    Boolean disponible;
    Integer codigo;

    Libro(String titulo, String autor, Boolean disponible, Integer codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
        this.codigo = codigo;
    }
    
    public void mostrarDatos() {
        System.out.println("titulo : " + titulo);
        System.out.println("autor : " + autor);
        System.out.println("disponible : " + disponible);
        System.out.println("codigo : " + codigo);

    }
    
    public void cambiarEstado() {
        disponible = !disponible;
    }
}
