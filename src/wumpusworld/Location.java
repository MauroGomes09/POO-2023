package wumpusworld;

public class Location implements Comparable
{
  
    public int x;
  
    public int y;
   
    public int prio;
    
   /**
      * Cria uma nova instância de Local.
      */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.prio = 0;
    }
    
  /**
      * Cria uma nova instância de Local.
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
     
      */
    public int compareTo(Object o)
    {
        Location l = (Location)o;
        if (l.prio < prio) return 1;
        if (l.prio > prio) return -1;
        return 0;
    }
}
