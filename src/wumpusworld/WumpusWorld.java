package wumpusworld;

import java.util.Vector;
/**
  *Aula inicial do programa Wumpus World. O programa
  * tem três opções: 1) Executar uma GUI onde o Wumpus World possa ser
  * resolvido passo a passo manualmente ou por um agente, ou 2) executar
  * uma simulação com mundos aleatórios em vários jogos,
  * ou 3) executar uma simulação sobre os mundos lidos em um arquivo de mapa.
 
  */
public class WumpusWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        WumpusWorld ww = new WumpusWorld();
    }
    
    /**
     * inicioa o programa 
     * 
     */
    public WumpusWorld()
    {
        String option = Config.getOption();
        
        if (option.equalsIgnoreCase("gui"))
        {
            showGUI();
        }
        if (option.equalsIgnoreCase("sim"))
        {
            runSimulator();
        }
        if (option.equalsIgnoreCase("simdb"))
        {
            runSimulatorDB();
        }
    }
    
    /**
     * inicia o programa no modo gui
     */
    private void showGUI()
    {
        GUI g = new GUI();
    }
    
    /**
      * Inicia o programa em modo simulador com
      * mapas lidos de um arquivo de dados.
      */
    private void runSimulatorDB()
    {
        MapReader mr = new MapReader();
        Vector<WorldMap> maps = mr.readMaps();
        
        double totScore = 0;
        for (int i = 0; i < maps.size(); i++)
        {
            World w = maps.get(i).generateWorld();
            totScore += (double)runSimulation(w);
        }
        totScore = totScore / (double)maps.size();
        System.out.println("Average score: " + totScore);
    }
    
   /**
      * Inicia o programa em modo simulador
      * com mapas aleatórios.
      */
    private void runSimulator()
    {
        double totScore = 0;
        for (int i = 0; i < 10; i++)
        {
            WorldMap w = MapGenerator.getRandomMap(i);
            totScore += (double)runSimulation(w.generateWorld());
        }
        totScore = totScore / (double)10;
        System.out.println("Average score: " + totScore);
    }
    
  /**
      * Executa o agente solucionador para o Wumpus especificado
      * Mundo.
      *
      * @param w Wumpus Mundo
      * @return Pontuação alcançada
      */
    private int runSimulation(World w)
    {
        int actions = 0;
        Agent a = new MyAgent(w);
        while (!w.gameOver())
        {
            a.doAction();
            actions++;
        }
        int score = w.getScore();
        System.out.println("Simulation ended after " + actions + " actions. Score " + score);
        return score;
    }
}
