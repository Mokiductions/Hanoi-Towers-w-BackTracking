package hanoitowers_backtracking_kk;

import java.util.Scanner;
import java.util.Stack;
import Components.Route;
import Components.Disk;
import Components.Tower;
import java.util.ArrayList;

/**
 * Clase 'HanoiTowers', que contiene la ejecución principal del juego, esta
 * clase solicita al usuario los datos (cantidad de discos) para iniciar el 
 * juego, inicializa estado inicial (todos los discos apilados en el orden
 * correcto en la primera torre), y empieza la iteración para resolver el juego.
 * Al final muestra los pasos necesarios para resolverlo.
 * 
 * Esta forma de resolverlo presenta un enorme déficit de velocidad en 
 * comparación a otros métodos posibles para resolver el mismo juego, como un
 * método recursivo, o un método basado en el calculo de los pasos a realizar
 * de forma algorítmica antes de realizarlos.
 * 
 * Pese a ello, es una buena forma de practicar la estructura principal del 
 * backtracking.
 * 
 * @author Miquel Ginés Borràs
 */
public class HanoiTowers {

    private Stack gameStack; // Pila principal de caminos del juego
    private int diskNum; // Cantidad de discos del juego

    /**
     * Constructor principal, recorrido del programa.
     */
    public HanoiTowers() {
        gameStack = new Stack();
        gameMenu();
        genGame();
        solveGame();
    }

    /**
     * Menú del juego, solicita al usuario la cantidad de discos que va a querer
     * en el juego.
     */
    private void gameMenu() {
        System.out.println("\nIntroduzca la cantidad de discos para el juego. "
                + "(Número mayor que 0)");
        diskNum = readInt();
    }

    /**
     * Función para leer un número entero por teclado, sólo permite la
     * introducción de números positivos mayores que 0.
     * @return Integer - Número introducido por el usuario
     */
    private int readInt() {
        Scanner sc = new Scanner(System.in);
        int i;
        do {
            try {
                i = sc.nextInt();
                if (i < 1) {
                    System.out.println("\nDebe introducir un número mayor que 0.\n"
                        + "Inténtelo de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("\nDebe introducir un número mayor que 0.\n"
                        + "Inténtelo de nuevo.");
                i = readInt();
            }
        } while (i < 1);
        return i;
    }

    /**
     * Generación inicial del juego, crea un primer objeto Route de la cantidad
     * de discos indicada por el usuario, y añade los posibles caminos de esta
     * ruta a la pila principal del juego.
     */
    private void genGame() {
        Route route = new Route(diskNum);
        Stack stackAux = route.nextMoves();
        while (!stackAux.isEmpty()) {
            gameStack.push(stackAux.pop());
        }
    }

    /**
     * Método principal de resolución del juego, itera mientras queden objetos
     * en la pila de caminos, y no se encuentre resuelto el juego.
     * Para ello coge cada camino, y va recorriendo los posibles movimientos de 
     * dicho camino.
     */
    private void solveGame() {
        System.out.println("\nResolviendo el juego...");
        while (!gameStack.isEmpty() && !isSolved()) {
            Route route = (Route) gameStack.pop();
            if (route.getMovePos() < route.getMoveMax()) {
                Stack newRoutes = route.nextMoves();
                while (!newRoutes.isEmpty()) {
                    gameStack.push(newRoutes.pop());
                }
            }
        }
        // Si sale del bucle anterior, y la pila principal no se encuentra
        // vacía, entonces el juego está resuelto y visualiza el resultado.
        if (!gameStack.empty()) {
            showResult();
        }
    }

    /**
     * Función que devuelve el estado de la solución del juego.
     * Para ello comprueba el estado actual del juego, y comprueba si los discos
     * se encuentran en la torre de destino y en el orden correcto.
     * @return Boolean - True si está resuelto, False si no lo está.
     */
    private boolean isSolved() {
        Stack stackAux = (Stack) gameStack.clone();
        Route route = (Route) stackAux.pop();
        Tower[] towersAux = new Tower[3];
        for (int i = 0; i < 3; i++) {
            towersAux[i] = new Tower();
            towersAux[i].setTower((ArrayList) route.getTowers()[i].getTower().clone());
        }
        Disk diskAux;
        if (!towersAux[2].getTower().isEmpty() && towersAux[2].getTower().size() == diskNum) {
            for (int i = 0; i < towersAux[2].getTower().size(); i++) {
                diskAux = (Disk) towersAux[2].getTower().get(i);
                if (diskAux.getSize() != towersAux[2].getTower().size() - i) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Método que muestra los pasos necesarios para resolver el juego, según el 
     * camino seguido.
     */
    private void showResult() {
        System.out.println("\nJuego solucionado.\n\nEstos son los pasos a seguir para"
                + " la resolución del juego de las Torres de Hanoi con " + diskNum
                + " discos:\n");
        Route routeOk = (Route) gameStack.pop();
        String[] solution = routeOk.getMoves();
        for (int i = 0; i < solution.length; i++) {
            System.out.println("Movimiento " + (i + 1) + ": " + solution[i]);
        }
    }
}
