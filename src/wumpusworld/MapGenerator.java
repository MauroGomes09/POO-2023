package wumpusworld;

import java.util.Random;

/**
 * Esta classe gera mapas Wumpus World aleatórios.
 */
public class MapGenerator {
    /**
     * Gera um mapa mundial Wumpus aleatório.
     */
    public static WorldMap getRandomMap(int seed) {
        Random rnd = new Random(seed);
        WorldMap w = new WorldMap(15);

        addRandomWumpus(w, rnd);
        addRandomMonstro2(w, rnd);
        addRandomGold(w, rnd);
        addRandomPit(w, rnd);
        addRandomPit(w, rnd);
        addRandomPit(w, rnd);

        return w;
    }

    /**
     * Adiciona um buraco a um quadrado aleatório.
     */
    private static void addRandomPit(WorldMap w, Random r) {
        for (int i = 0; i < 5; i++) {
            boolean valid = false;
            while (!valid) {
                int x = rnd(r);
                int y = rnd(r);
                if (!(x == 1 && y == 1) && !w.hasPit(x, y)) {
                    valid = true;
                    w.addPit(x, y);
                }
            }
        }
    }

    /**
     * Adiciona o Wumpus a um quadrado aleatório.
     */
    private static void addRandomWumpus(WorldMap w, Random r) {
        for (int i = 0; i < 2; i++) {
            boolean valid = false;
            while (!valid) {
                int x = rnd(r);
                int y = rnd(r);
                if (!(x == 1 && y == 1)) {
                    valid = true;
                    w.addWumpus(x, y);
                }
            }
        }
    }

    /**
     * Adiciona o Monstro 2 a um quadrado aleatório.
     */
    private static void addRandomMonstro2(WorldMap w, Random r) {
        for (int i = 0; i < 2; i++) {
            boolean valid = false;
            while (!valid) {
                int x = rnd(r);
                int y = rnd(r);
                if (!(x == 1 && y == 1)) {
                    valid = true;
                    w.addMonster2(x, y);
                }
            }
        }
    }

    /**
        Adiciona o tesouro e casas aleatorias do mapa
     */
    private static void addRandomGold(WorldMap w, Random r) {
        for (int i = 0; i < 2; i++) {
            boolean valid = false;
            while (!valid) {
                int x = rnd(r);
                int y = rnd(r);
                if (!(x == 1 && y == 1)) {
                    valid = true;
                    w.addGold(x, y);
                }
            }
        }
    }

    /**
     * 
     * 
     * @param rnd
     * @return
     */
    private static int rnd(Random rnd) {
        return rnd.nextInt(15) + 1;
    }
}
