package Components;

/**
 * Clase 'Disk', usada para establecer las propiedades ('size') de cada disco
 * contenido en las torres del juego de las torres de Hanoi.
 *
 * @author Miquel Ginés Borràs
 */

public class Disk {

    private int size; // Tamaño del disco

    /**
     * Constructor vacío.
     */
    public Disk() {

    }

    /**
     * Constructor con tamaño por parámetros.
     * @param size Integer - Tamaño del disco
     */
    public Disk(int size) {
        this.size = size;
    }

    /**
     * Devuelve el tamaño del disco actual.
     * @return Integer - Tamaño del disco
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño del disco actual.
     * @param size Integer - Tamaño del disco
     */
    public void setSize(int size) {
        this.size = size;
    }

}
