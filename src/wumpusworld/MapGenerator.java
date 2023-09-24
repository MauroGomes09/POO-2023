package wumpusworld;

import java.util.Random;
/**
  * Esta classe gera mapas Wumpus World aleatórios.
  */
public class MapGenerator 
{
  /**
      * Gera um mapa mundial Wumpus aleatório.
      *
      * @param seed Semente para o randomizador. A mesma semente sempre resulta no mesmo mapa aleatório.
      * @return Mundo Wumpus gerado
      */
    public static WorldMap getRandomMap(int seed)
    {
        Random rnd = new Random(seed);
        WorldMap w = new WorldMap(15);
        
        addRandomWumpus(w,rnd);
        addRandomGold(w,rnd);
        addRandomPit(w,rnd);
        addRandomPit(w,rnd);
        addRandomPit(w,rnd);
        
        return w;
    }
    
   /**
      * Adiciona um buraco a um quadrado aleatório.
      *
      * @param w Wumpus Mundo
      * @param r Randomizador
      */
    private static void addRandomPit(WorldMap w, Random r)
    {
        boolean valid = false;
        while (!valid)
        {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1) && !w.hasPit(x, y))
            {
                valid = true;
                w.addPit(x, y);
            }
        }
    }
    
    /**
     * Adds the Wumpus to a random square.
     * 
     * @param w Wumpus World
     * @param r Randomizer
     */
    private static void addRandomWumpus(WorldMap w, Random r)
    {
        boolean valid = false;
        while (!valid)
        {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1))
            {
                valid = true;
                w.addWumpus(x, y);
            }
        }
    }
    
    /**
     * Adds the gold treasure to a random square.
     * 
     * @param w Wumpus World
     * @param r Randomizer
     */
    private static void addRandomGold(WorldMap w, Random r)
    {
        boolean valid = false;
        while (!valid)
        {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1))
            {
                valid = true;
                w.addGold(x, y);
            }
        }
    }
    
    /**
     * 
     * 
     * @param rnd
     * @return 
     */
    private static int rnd(Random rnd)
    {
        return rnd.nextInt(15) + 1;
    }
}
