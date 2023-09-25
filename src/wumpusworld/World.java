package wumpusworld;

import java.util.Vector;

/**
  * Esta classe lida com uma instância do Wumpus World. Ele contém o mundo
  * estado, quais ações estão disponíveis e atualiza o mundo quando uma ação
  * foi executado.
  */
public class World 
{
    private int size;
    private String[][] w;
    private int pX = 1;
    private int pY = 1;
    private boolean wumpusAlive = true;
    private boolean hasArrow = true;
    private boolean isInPit = false;
    private boolean hasGold = false;
    private boolean gameOver = false;   
    private int score = 0;
    //Constantes de direções do jogador.
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;
    
    //inicia direção
    private int dir = DIR_RIGHT;
    
    //constantes de percepções
    public static final String BREEZE = "B";
    public static final String STENCH = "S";
    public static final String PIT = "P";
    public static final String WUMPUS = "W";
    public static final String GLITTER = "G";
    public static final String GOLD = "T";
    public static final String UNKNOWN = "U";
    
    //constantes de ação
    public static final String A_MOVE = "m";
    public static final String A_GRAB = "g";
    public static final String A_CLIMB = "c";
    public static final String A_SHOOT = "s";
    public static final String A_TURN_LEFT = "l";
    public static final String A_TURN_RIGHT = "r";

    public boolean hasPlayerVisited(int x, int y) {
        return grid[x][y].isVisited();
    }
    
  /**
      * Cria um novo Mundo Wumpus. O Mundo Wumpus trabalha com
      * qualquer tamanho 4 ou maior, mas apenas o tamanho 4 é suportado por
      * a GUI.
      *
      * @param size Tamanho do mundo.
      */
    public World(int size)
    {
        this.size = size;
        w = new String[size+1][size+1];
        
        for (int x = 0; x <= size; x++)
        {
            for (int y = 0; y <= size; y++)
            {
                w[x][y] = UNKNOWN;
            }
        }
        
        setVisited(1, 1);
    }

    public World(String[][] w1,int size, int px, int py, int dirr)
    {
        this.size = size;
        pX=px;
        pY=py;
        dir = dirr;
        this.w=w1;
    }
  /**
      * Retorna a pontuação atual.
      *
      * @return A pontuação.
      */
    public int getScore()
    {
        return score;
    }
   /**
      * Retorna o tamanho deste Mundo Wumpus.
      *
      * @return O tamanho
      */
    public int getSize()
    {
        return size;
    }
    
  /**
      * Verifica se o jogo terminou ou não.
      *
      * @return True se o jogo terminar, false se não.
      */
    public boolean gameOver()
    {
        return gameOver;
    }
    
    /**
      * Retorna a posição do jogador X.
      *
      * @return posição X.
      */
    public int getPlayerX()
    {
        return pX;
    }
    
    /**
     * Returns player Y position.
     * 
     * @return Y position.
     */
    public int getPlayerY()
    {
        return pY;
    } 
           
  /**
      * Verifica se o jogador está em um buraco e precisa
      * escalar.
      *
      * @return True se estiver em um poço, false caso contrário.
      */
    public boolean isInPit()
    {
        return isInPit;
    }
    
    /**
     * Checks if the player has the arrow left.
     * 
     * @return True if player has the arrow, false otherwise.
     */
    public boolean hasArrow()
    {
        return hasArrow;
    }
    
    /**
   * Verifica se o Wumpus está vivo.
      *
      * @return True se Wumpus estiver vivo, false caso contrário.
      */
    public boolean wumpusAlive()
    {
        return wumpusAlive;
    }
    
 /**
      * Verifica se o jogador carrega o tesouro de ouro.
      *
      * @return True se o jogador tiver o ouro, false caso contrário.
      */
    public boolean hasGold()
    {
        return hasGold;
    }
    
   /**
      * Retorna a direção atual do jogador.
      *
      * @return Direção (ver constantes de direção)
      */
    public int getDirection()
    {
        return dir;
    }
    
    /**
      * Verifica se um quadrado tem brisa. Retorna falso
      * se a posição for inválida ou se o quadrado for
      * desconhecido.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se a praça tiver brisa
      */
    public boolean hasBreeze(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(BREEZE))
            return true;
        else
            return false;
    }
    
 /**
      * Verifica se um quadrado tem mau cheiro. Retorna falso
      * se a posição for inválida ou se o quadrado for
      * desconhecido.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se o quadrado tiver mau cheiro
      */
    public boolean hasStench(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(STENCH))
            return true;
        else
            return false;
    }
    
  /**
      * Verifica se um quadrado tem brilho. Retorna falso
      * se a posição for inválida ou se o quadrado for
      * desconhecido.
      *
      * @param x posição X
      * @param e posição Y
      * @return True se o quadrado tiver glitter
      */
    public boolean hasGlitter(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(GLITTER))
            return true;
        else
            return false;
    }
    
/**
      * Verifica se um quadrado possui um buraco. Retorna falso
      * se a posição for inválida ou se o quadrado for
      * desconhecido.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se o quadrado tiver um buraco
      */
    public boolean hasPit(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(PIT))
            return true;
        else
            return false;
    }
    
   /**
      * Verifica se o Wumpus está em um quadrado. Retorna falso
      * se a posição for inválida ou se o quadrado for
      * desconhecido.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se o Wumpus estiver na praça
      */
    public boolean hasWumpus(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        if (isUnknown(x,y)) return false;
        
        if (w[x][y].contains(WUMPUS))
            return true;
        else
            return false;
    }
 /**
      * Verifica se o jogador está em um quadrado.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se o jogador estiver na praça
      */
    public boolean hasPlayer(int x, int y)
    {
        if (pX == x && pY == y)
        {
            return true;
        }
        return false;
    }
    
/**
      * Verifica se uma praça é visitada. Retorna falso
      * se a posição for inválida.
      *
      * @param x posição X
      * @param e posição Y
      * @return True se a praça for visitada
      */
    public boolean isVisited(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        
        return !isUnknown(x, y);
    }
    
   /**
      * Verifica se um quadrado é desconhecido. Retorna falso
      * se a posição for inválida.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se o quadrado for desconhecido
      */
    public boolean isUnknown(int x, int y)
    {
        if (!isValidPosition(x,y)) return false;
        
        if (w[x][y].contains(UNKNOWN))
            return true;
        else
            return false;  
    }
    
  /**
      * Verifica se um quadrado é válido, ou seja, dentro
      * os limites do mundo do jogo.
      *
      * @param x posição X
      * @param e posição Y
      * @return Verdadeiro se o quadrado for válido
      */
    public boolean isValidPosition(int x, int y)
    {
        if (x < 1) return false;
        if (y < 1) return false;
        if (x > size) return false;
        if (y > size) return false;
        return true;
    }
    
    /**
      * Adiciona uma percepção a um quadrado.
      *
      * @param x posição X
      * @param e posição Y
      * @param s Percept a ser adicionado (veja constantes de Percept)
      */
    private void append(int x, int y, String s)
    {
        if (!isValidPosition(x,y))
            return;
        
        if (!w[x][y].contains(s))
        {
            w[x][y] += s;
        }
    }
    
   /**
      * Adiciona o Wumpus a um quadrado.
      *
      * @param x posição X
      * @param e posição Y
      */
    public void addWumpus(int x, int y)
    {
        if (!w[x][y].contains(WUMPUS))
        {
            append(x,y,WUMPUS);
            append(x-1,y,STENCH);
            append(x+1,y,STENCH);
            append(x,y-1,STENCH);
            append(x,y+1,STENCH);
        }
    }
    
    public World cloneWorld()
    {
        //this.size = size;
        String[][] w1 = new String[size+1][size+1];
        int px,py,dirr;
        px=pX;
        py=pY;
        dirr=dir;
        for (int x = 0; x <= size; x++)
        {
            for (int y = 0; y <= size; y++)
            {
                w1[x][y]= w[x][y];
            }
        }
        
        return new World(w1,size,px,py,dirr);
    }
    
   /**
      * Adiciona um buraco a um quadrado.
      *
      * @param x posição X
      * @param e posição Y
      */
    public void addPit(int x, int y)
    {
        if (!w[x][y].contains(PIT))
        {
            append(x,y,PIT);
            append(x-1,y,BREEZE);
            append(x+1,y,BREEZE);
            append(x,y-1,BREEZE);
            append(x,y+1,BREEZE);
        }
    }
    
   /**
      * Adiciona o tesouro de ouro a um quadrado.
      *
      * @param x posição X
      * @param e posição Y
      */
    public void addGold(int x, int y)
    {
        if (!w[x][y].contains(GLITTER))
        {
            append(x,y,GLITTER);
        }
    }
    
   /**
      * Define que uma praça foi visitada.
      *
      * @param x posição X
      * @param e posição Y
      */
    private void setVisited(int x, int y)
    {
        if (w[x][y].contains(UNKNOWN))
        {
            w[x][y] = w[x][y].replaceAll(UNKNOWN, "");
        }
    }
   /**
      * Executa uma ação no Mundo Wumpus.
      *
      * @param uma string de ação (consulte Constantes de ação)
      * @return Verdadeiro se a ação foi bem-sucedida, falso se a ação falhou.
      */
    public boolean doAction(String a)
    {
        if (gameOver) return false;
        
        //Each action costs 1 score
        score -= 1;
        
        if (a.equals(A_MOVE))
        {
            if (!isInPit)
            {
                if (dir == DIR_LEFT) return move(pX-1,pY);
                if (dir == DIR_RIGHT) return move(pX+1,pY);
                if (dir == DIR_UP) return move(pX,pY+1);
                if (dir == DIR_DOWN) return move(pX,pY-1);
            }
        }
        if (a.equals(A_TURN_LEFT))
        {
            dir--;
            if (dir < 0) dir = 3;
            return true;
        }
        if (a.equals(A_TURN_RIGHT))
        {
            dir++;
            if (dir > 3) dir = 0;
            return true;
        }
        if (a.equals(A_GRAB))
        {
            if (hasGlitter(pX,pY))
            {
                w[pX][pY] = w[pX][pY].replaceAll(GLITTER, "");
                score += 1000;
                hasGold = true;
                gameOver = true;
                return true;
            }
        }
        if (a.equals(A_SHOOT))
        {
            if (hasArrow)
            {
                score -= 10;
                hasArrow = false;
                shoot();
                return true;
            }
        }
        if (a.equals(A_CLIMB))
        {
            isInPit = false;
        }
        
        //Action failed
        return false;
    }
   /**
      * Verifica se o Wumpus foi atingido pela flecha.
      */
    private void shoot()
    {
        if (dir == DIR_RIGHT)
        {
            for (int x = pX; x <= size; x++)
            {
                if (w[x][pY].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_LEFT)
        {
            for (int x = pX; x >= 0; x--)
            {
                if (w[x][pY].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_UP)
        {
            for (int y = pY; y <= size; y++)
            {
                if (w[pX][y].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_DOWN)
        {
            for (int y = pY; y >= 0; y--)
            {
                if (w[pX][y].contains(WUMPUS)) removeWumpus();
            }
        }
    }
    
/**
      * Remove o Wumpus (e o Fedor) do Mundo Wumpus.
      * Usado quando o Wumpus foi atingido pela flecha.
      */
    private void removeWumpus()
    {
        for (int x = 1; x <= 4; x++)
        {
            for (int y = 1; y <= 4; y++)
            {
                w[x][y] = w[x][y].replaceAll(WUMPUS, "");
                w[x][y] = w[x][y].replaceAll(STENCH, "");
            }
        }
        
        wumpusAlive = false;
    }
    
   /**
      * Executa um avanço para um novo quadrado.
      *
      * @param nX Nova posição X
      * @param nY Nova posição Y
      * @return True se as ações de movimentação foram bem-sucedidas, false caso contrário
      */
    private boolean move(int nX, int nY)
    {
        //Check if valid
        if (!isValidPosition(nX, nY))
        {
            return false;
        }
        
        pX = nX;
        pY = nY;
        
        setVisited(pX, pY);
        
        if(hasWumpus(pX,pY))
        {
            score -= 1000;
            gameOver = true;
        }
        if (hasPit(pX,pY))
        {
            score -= 1000;
            isInPit = true;
        }
        
        return true;    
    }
}
