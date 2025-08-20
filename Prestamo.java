package GestionDeBiblioteca;

public class Prestamo {
    String fechaPrestamo;
    Integer diasPrestados;
    String nombreUsuario;
    String tituloLibro;

    Prestamo(String fechaPrestamo, Integer diasPrestados, String nombreUsuario, String tituloLibro) {
        this.fechaPrestamo = fechaPrestamo;
        this.diasPrestados = diasPrestados;
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
    }

    public String prestamoHecho() {
        return "Fecha de prestamo " + fechaPrestamo + " dias de prestamo " + diasPrestados + " Usuario de pretamos "
                + nombreUsuario + " Nombre del libro " + tituloLibro;
    }
    
    public Integer recargoPorRetraso(Integer dias) {
        Integer diasRetraso = dias - diasPrestados;
        if (diasRetraso < 0) {
            return 0;
        } else {
            return diasRetraso * 500;
        }
    }
}
