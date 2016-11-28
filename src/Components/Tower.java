package Components;

import java.util.ArrayList;

/**
 * Clase 'Tower', usada para establecer las propiedades de cada torre, estas 
 * propiedades son una lista de objetos de la clase 'Disk'.
 * 
 * @author Miquel Ginés Borràs
 */

public class Tower {
    
    private ArrayList tower; // Lista de Disks que forman la torre
    
    /**
     * Constructor de la torre, inicializa la lista de Disks.
     */
    public Tower() {
        tower = new ArrayList();
    }
    
    /**
     * Función que simula el comportamiento del PUSH de una pila, añade un Disk
     * a la parte superior de la torre.
     * @param d Disk - Disco a añadir
     */
    public void pushDisk(Disk d) {
        tower.add(d);
    }
    
    /**
     * Función que simula el comportamiento del POP de una pila, devuelve el
     * Disk que se encuentra en la parte superior de la torre.
     * @return Disk - Disco en la parte superior de  la torre
     */
    public Disk popDisk() {
        return (Disk) tower.get(tower.size() - 1);
    }
    
    /**
     * Elimina el disco que se encuentra en la parte superior de la torre.
     */
    public void removeTopDisk() {
        tower.remove(tower.size() - 1);
    }

    /**
     * Devuelve la lista de discos de la torre.
     * @return ArrayList - Lista de discos de la torre actual
     */
    public ArrayList getTower() {
        return tower;
    }
    
    /**
     * Establece una nueva lista de discos para la torre actual, sobreescribe
     * la actualmente existente.
     * @param tower ArrayList - Lista de discos para la torre
     */
    public void setTower(ArrayList tower) {
        this.tower = tower;
    }
    
}
