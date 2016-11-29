package hanoitowers_backtracking_kk;

import java.util.Scanner;
import java.util.Stack;
import Components.Route;
import Components.Disk;
import Components.Tower;
import java.util.ArrayList;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class HanoiTowers {

    private Stack gameStack; // Pila principal de caminos del juego
    private int diskNum; // Cantidad de discos del juego

    /**
     * Constructos principal, recorrido del programa.
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
        if (!gameStack.empty()) {
            showResult();
        }
    }

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
