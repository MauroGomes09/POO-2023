package wumpusworld;

/**
 * Interface para criar um agente para combater o wumpus
 * 
 */
public interface Agent 
{
    /**
     * Diz ao agente a ação que deve realizar no mundo
     */
    public void doAction();

    /*adiciona um atriuto para armazenar o estado da lanterna no agente */
    public boolean lantenaLigada = false;
}
