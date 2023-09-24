package wumpusworld;

/**
  *Esta classe é utilizada pelo agente ao buscar novos
  * quadrados para onde se mover. Ele contém a posição para um
  * quadrado e uma prioridade. Priovalores mais baixos = mais altos
  * prioridade.

  */
public class Location implements Comparable
{
    /**
     * X position
     */
    public int x;
    /**
     * Y position
     */
    public int y;
    /**
     * Priority value
     */
    public int prio;
    
   /**
      * Cria uma nova instância de Local.
      *
      * @param x posição X
      * @param e posição Y
      */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.prio = 0;
    }
    
  /**
      * Cria uma nova instância de Local.
      *
      * @param x posição X
      * @param e posição Y
      * @param prio Valor de prioridade
      */
    public Location(int x, int y, int prio)
    {
        this.x = x;
        this.y = y;
        this.prio = prio;
    }
    
  /**
      * Usado para classificar listas de possíveis objetivos. Prioridade inferior
      * valor = maior prioridade.
      *
      * @param o objeto Localização.
      * @return 1 if prio(o) > prio(este). -1 se prio(o) > prio(este). 0 caso contrário.
      */
    public int compareTo(Object o)
    {
        Location l = (Location)o;
        if (l.prio < prio) return 1;
        if (l.prio > prio) return -1;
        return 0;
    }
}
