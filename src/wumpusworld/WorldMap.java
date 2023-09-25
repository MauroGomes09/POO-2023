package wumpusworld;

import java.util.Vector;
import java.awt.Point;

/**
 * Esta classe representa uma leitura do mapa Wumpus World
 * do arquivo(maps).
 * 
 */
public class WorldMap {
    private int size;
    private Point wumpus;
    private Point monstro2;
    private Point gold;
    private Vector<Point> pits;

    /**
     * Cria uma nova instância de mapa.
     *
     * @param size Tamanho do mapa mundial
     */
    public WorldMap(int size) {
        this.size = size;
        pits = new Vector<Point>();
    }

    /**
     * Adiciona o Wumpus.
     *
     * @param x posição X
     * @param e posição Y
     */
    public void addWumpus(int x, int y) {
        wumpus = new Point(x, y);
    }

    // Adiciona o Monstro2.
    public void addMonster2(int x, int y) {
        monstro2 = new Point(x, y);
    }

    /**
     * Adiciona o tesouro de ouro.
     *
     * @param x posição X
     * @param e posição Y
     */
    public void addGold(int x, int y) {
        gold = new Point(x, y);
    }

    /**
     * Adiciona um poço. O mapa pode ter qualquer número de poços.
     *
     * @param x posição X
     * @param e posição Y
     */
    public void addPit(int x, int y) {
        pits.add(new Point(x, y));
    }

    /**
     * Retorna o tamanho do mapa mundial.
     *
     * @return O tamanho
     */
    public int getSize() {

        return size;
    }

    /**
     * Retorna posição para o Wumpus.
     *
     * @return A posição
     */
    public Point getWumpus() {
        return wumpus;
    }

    /**
     * Retorna posição para o Monster 2.
     *
     * @return A posição
     */
    public Point getMonster2() {
        return monstro2;
    }

    /**
     * Retorna a posição do tesouro de ouro.
     *
     * @return A posição
     */
    public Point getGold() {
        return gold;
    }

    /**
     * Retorna posições para todos os boxes.
     *
     * @return Uma lista de posições para boxes
     */
    public Vector<Point> getPits() {
        return pits;
    }

    /**
     * Verifica se existe um poço no local especificado.
     *
     * @param x coordenada X
     * @param e coordenada Y
     * @return True se houver um buraco, false se não
     */
    public boolean hasPit(int x, int y) {
        for (Point p : pits) {
            if (p.x == x && p.y == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gera uma instância do Wumpus World a partir deste mapa.
     *
     * @return Instância do Wumpus World
     */
    public World generateWorld() {
        World w = new World(size);
        w.addWumpus(wumpus.x, wumpus.y);
        w.addMonstro2(monstro2.x, monstro2.y);
        w.addGold(gold.x, gold.y);
        for (int i = 0; i < pits.size(); i++) {
            w.addPit(pits.get(i).x, pits.get(i).y);
        }
        return w;
    }
}
