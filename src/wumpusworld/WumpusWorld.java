package wumpusworld;

import java.util.Vector;

/**
 * O programa
 * tem apenas uma opção: 1) Executar uma GUI onde o Wumpus World possa ser
 * resolvido passo a passo manualmente ou por um agente.
 * 
 */
public class WumpusWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WumpusWorld ww = new WumpusWorld();
    }

    /**
     * inicia o programa
     * 
     */
    public WumpusWorld() {
        String option = Config.getOption();

        option.equalsIgnoreCase("gui");
        showGUI();
    }

    /**
     * inicia o programa no modo gui
     */
    private void showGUI() {
        GUI g = new GUI();
    }

    /**
     * Executa o agente solucionador para o Wumpus especificado
     * Mundo.
     *
     * @param w Wumpus Mundo
     * @return Pontuação alcançada
     */
    private int runSimulation(World w) {
        int actions = 0;
        Agent a = new MyAgent(w);
        while (!w.gameOver()) {
            a.doAction();
            actions++;
        }
        int score = w.getScore();
        System.out.println("Simulation ended after " + actions + " actions. Score " + score);
        return score;
    }
}
