package Components;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Clase 'Route', usada para establecer cada uno de los posibles caminos, cada
 * uno de estos posibles caminos generará X subcaminos, dependiendo de la
 * validez de cada uno para ser recorrido.
 *
 * @author Miquel Ginés Borràs
 */
public class Route {

    private int routeLevel = 0;
    private int subRoutesMax = 0;
    private int subRouteCounter = 0;
    private int movePos = 0;
    private int moveMax;
    private Tower[] towers;
    private String[] moves;

    public Route() {
    }

    public Route(int diskNum) {
        genTowers(diskNum);
        moveMax = genMoveMax(diskNum);
        moves = new String[moveMax];
        genMaxSubRoutes();
    }

    public Route(Tower[] towers, String[] moves, int moveMax, int movePos,
            int subRoutes, int routeLevel) {
        this.towers = towers;
        this.moves = moves;
        this.moveMax = moveMax;
        this.movePos = movePos;
        this.subRouteCounter = subRoutes;
        this.routeLevel = routeLevel;
        genMaxSubRoutes();
        
    }

    private void genTowers(int diskNum) {
        towers = new Tower[3];
        Disk d;
        for (int i = 0; i < 3; i++) {
            towers[i] = new Tower();
        }
        for (int i = diskNum; i > 0; i--) {
            d = new Disk(i);
            towers[0].pushDisk(d);
        }
    }

    private int genMoveMax(int diskNum) {
        int max = 1;
        for (int i = 0; i < diskNum; i++) {
            max *= 2;
        }
        return max - 1;
    }

    private void genMaxSubRoutes() {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (k != i) {
                    if (!towers[i].getTower().isEmpty() && (towers[k].getTower().isEmpty()
                            || towers[i].popDisk().getSize() < towers[k].popDisk().getSize())) {
                        subRoutesMax++;
                    }
                }
            }
        }
    }

    public Stack nextMoves() {
        Stack newMoves = new Stack();
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (k != i) {
                    if (!towers[i].getTower().isEmpty()) {
                        if (towers[k].getTower().isEmpty()
                                || towers[i].popDisk().getSize() < towers[k].popDisk().getSize()) {
                            if (counter == this.subRouteCounter) {
                                newMoves.push(nextRoute(i, k));
                                counter++;
                            } else {
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        if (subRouteCounter < this.subRoutesMax) {
            newMoves.push(nextRouteFather());
        }
        return newMoves;
    }

    private Route nextRouteFather() {
        return new Route(towers, moves, moveMax, movePos,
                subRouteCounter + 1, routeLevel);
    }

    private Route nextRoute(int a, int b) {
        Tower[] towersAux = new Tower[3];
        for (int i = 0; i < 3; i++) {
            towersAux[i] = new Tower();
            towersAux[i].setTower((ArrayList) towers[i].getTower().clone());
        }
        Disk d = new Disk(towersAux[a].popDisk().getSize());
        towersAux[a].removeTopDisk();
        towersAux[b].pushDisk(d);
        String[] movesAux = moves.clone();
        movesAux[movePos] = "Torre " + (a + 1) + " --> Torre " + (b + 1);
        return new Route(towersAux, movesAux, moveMax, movePos + 1,
                0, routeLevel + 1);
    }

    public Tower[] getTowers() {
        return towers;
    }

    public int getMovePos() {
        return movePos;
    }

    public int getMoveMax() {
        return moveMax;
    }

    public String[] getMoves() {
        return moves;
    }

}
