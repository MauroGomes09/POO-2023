package wumpusworld;

import java.util.Vector;

/**
 * Esta classe lida com uma instância do Wumpus World. Ele contém o mundo
 * estado, quais ações estão disponíveis e atualiza o mundo quando uma ação
 * foi executado.
 */
public class World {
    private boolean lantern;
    private int size;
    private String[][] w;
    private int pX = 1;
    private int pY = 1;
    private int newX;
    private int newY;
    private boolean wumpusAlive = true;
    private boolean monster2Alive = true;
    private boolean hasArrow = true;
    private boolean isInPit = false;
    private boolean hasGold = false;
    private boolean gameOver = false;
    private int score = 0;
    // Constantes de direções do jogador.
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;

    // inicia direção
    private int dir = DIR_RIGHT;

    // constantes de percepções
    public static final String BREEZE = "B";
    public static final String STENCH = "S";
    public static final String PIT = "P";
    public static final String WUMPUS = "W";
    public static final String MONSTRO2 = "M";
    public static final String GLITTER = "G";
    public static final String GOLD = "T";
    public static final String UNKNOWN = "U";

    // constantes de ação
    public static final String A_MOVE = "m";
    public static final String A_GRAB = "g";
    public static final String A_CLIMB = "c";
    public static final String A_SHOOT = "s";
    public static final String A_SHOOT1 = "s1";
    public static final String A_TURN_LEFT = "l";
    public static final String A_TURN_RIGHT = "r";
    public static final String A_LANTERN = "lt";

    /* Lanterna */

    private boolean lanternOn = false;

    public void toggleLantern() {
        lanternOn = !lanternOn;
    }

    /**
     * 
     * Cria um novo Mundo Wumpus. O Mundo Wumpus trabalha com
     * tamanho de mapa(matriz) 4 ou maior, dependendo da opção escolhida pelo
     * usúario.

     */
    public World(int size) {
        this.size = size;
        w = new String[size + 1][size + 1];

        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                w[x][y] = UNKNOWN;
            }
        }

        setVisited(1, 1);
    }

    public World(String[][] w1, int size, int px, int py, int dirr) {
        this.size = size;
        pX = px;
        pY = py;
        dir = dirr;
        this.w = w1;
    }

    /**
     * Retorna a pontuação atual.

     */
    public int getScore() {
        return score;
    }

    /**
     * Retorna o tamanho deste Mundo Wumpus.
  
     */
    public int getSize() {
        return size;
    }

    /**
     * Verifica se o jogo terminou ou não.
  
     */
    public boolean gameOver() {
        return gameOver;
    }

    /**
     * Retorna a posição do jogador X.

     */
    public int getPlayerX() {
        return pX;
    }

    /**
     * Retorna a posição do jogador Y.
     
     */
    public int getPlayerY() {
        return pY;
    }

    /**
     * Verifica se o jogador está em um buraco e precisa
     * escalar.

     */
    public boolean isInPit() {
        return isInPit;
    }

    /**
     * Checks if the player has the arrow left.
     */
    public boolean hasArrow() {
        return hasArrow;
    }

    /**
     * Verifica se o Wumpus está vivo.
     */
    public boolean wumpusAlive() {
        return wumpusAlive;
    }

    /**
     * Verifica se o Monstro 2 está vivo.

     */
    public boolean monster2Alive() {
        return monster2Alive;
    }

    /**
     * Verifica se o jogador carrega o tesouro de ouro.
 
     */
    public boolean hasGold() {
        return hasGold;
    }

    /**
     * Retorna a direção atual do jogador.

     */
    public int getDirection() {
        return dir;
    }

    /**
     * Verifica se um quadrado tem brisa. Retorna falso
     * se a posição for inválida ou se o quadrado for
     * desconhecido.

     */
    public boolean hasBreeze(int x, int y) {
        if (!isValidPosition(x, y))
            return false;
        if (isUnknown(x, y))
            return false;

        if (w[x][y].contains(BREEZE))
            return true;
        else
            return false;
    }

    /**
     * Verifica se um quadrado tem mau cheiro. Retorna falso
     * se a posição for inválida ou se o quadrado for
     * desconhecido.

     */
    public boolean hasStench(int x, int y) {
        if (!isValidPosition(x, y))
            return false;
        if (isUnknown(x, y))
            return false;

        if (w[x][y].contains(STENCH))
            return true;
        else
            return false;
    }

    /**
     * Verifica se um quadrado tem brilho. Retorna falso
     * se a posição for inválida ou se o quadrado for
     * desconhecido.

     */
    public boolean hasGlitter(int x, int y) {
        if (!isValidPosition(x, y))
            return false;
        if (isUnknown(x, y))
            return false;

        if (w[x][y].contains(GLITTER))
            return true;
        else
            return false;
    }

    /**
     * Verifica se um quadrado possui um buraco. Retorna falso
     * se a posição for inválida ou se o quadrado for
     * desconhecido.

     */
    public boolean hasPit(int x, int y) {
        if (!isValidPosition(x, y))
            return false;
        if (isUnknown(x, y))
            return false;

        if (w[x][y].contains(PIT))
            return true;
        else
            return false;
    }

    /**
     * Verifica se o Wumpus está em um quadrado. Retorna falso
     * se a posição for inválida ou se o quadrado for
     * desconhecido.

     */
    public boolean hasWumpus(int x, int y) {
        if (!isValidPosition(x, y))
            return false;
        if (isUnknown(x, y))
            return false;

        if (w[x][y].contains(WUMPUS))
            return true;
        else
            return false;
    }

    /**
     * Verifica se o Monstro 2 está em um quadrado. Retorna falso
     * se a posição for inválida ou se o quadrado for
     * desconhecido.
 
     */
    public boolean hasMonster2(int x, int y) {
        if (!isValidPosition(x, y))
            return false;
        if (isUnknown(x, y))
            return false;

        if (w[x][y].contains(MONSTRO2))
            return true;
        else
            return false;
    }

    /**
     * Verifica se o jogador está em um quadrado.
    
     */
    public boolean hasPlayer(int x, int y) {
        if (pX == x && pY == y) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se uma praça é visitada. Retorna falso

     */
    public boolean isVisited(int x, int y) {
        if (!isValidPosition(x, y))
            return false;

        return !isUnknown(x, y);
    }

    /**
     * Verifica se um quadrado é desconhecido. Retorna falso

     */
    public boolean isUnknown(int x, int y) {
        if (!isValidPosition(x, y))
            return false;

        if (w[x][y].contains(UNKNOWN))
            return true;
        else
            return false;
    }

    /**
     * Verifica se um quadrado é válido, ou seja, dentro

     */
    public boolean isValidPosition(int x, int y) {
        if (x < 1)
            return false;
        if (y < 1)
            return false;
        if (x > size)
            return false;
        if (y > size)
            return false;
        return true;
    }

    /**
     * Adiciona uma percepção a um quadrado.

     */
    private void append(int x, int y, String s) {
        if (!isValidPosition(x, y))
            return;

        if (!w[x][y].contains(s)) {
            w[x][y] += s;
        }
    }

    /**
     * Adiciona o Wumpus a um quadrado.
 
     */
    public void addWumpus(int x, int y) {
        if (!w[x][y].contains(WUMPUS)) {
            append(x, y, WUMPUS);
            append(x - 1, y, STENCH);
            append(x + 1, y, STENCH);
            append(x, y - 1, STENCH);
            append(x, y + 1, STENCH);
        }
    }

    /**
     * Adiciona o Monstro 2 a um quadrado.
    
     */

    public void addMonstro2(int x, int y) {
        if (!w[x][y].contains(MONSTRO2)) {
            append(x, y, MONSTRO2);
            append(x - 1, y, STENCH);
            append(x + 1, y, STENCH);
            append(x, y - 1, STENCH);
            append(x, y + 1, STENCH);
        }
    }

    public World cloneWorld() {
        // this.size = size;
        String[][] w1 = new String[size + 1][size + 1];
        int px, py, dirr;
        px = pX;
        py = pY;
        dirr = dir;
        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                w1[x][y] = w[x][y];
            }
        }

        return new World(w1, size, px, py, dirr);
    }

    /**
     * Adiciona um buraco a um quadrado.

     */
    public void addPit(int x, int y) {
        if (!w[x][y].contains(PIT)) {
            append(x, y, PIT);
            append(x - 1, y, BREEZE);
            append(x + 1, y, BREEZE);
            append(x, y - 1, BREEZE);
            append(x, y + 1, BREEZE);
        }
    }

    /**
     * Adiciona o tesouro de ouro a um quadrado.
  
     */
    public void addGold(int x, int y) {
        if (!w[x][y].contains(GLITTER)) {
            append(x, y, GLITTER);
        }
    }

    /**
     * Define que uma praça foi visitada.
     *
     * @param x posição X
     * @param e posição Y
     */
    private void setVisited(int x, int y) {
        if (w[x][y].contains(UNKNOWN)) {
            w[x][y] = w[x][y].replaceAll(UNKNOWN, "");
        }
    }

    /**
     * Executa uma ação no Mundo Wumpus.
     *
     * @param uma string de ação (consulte Constantes de ação)
     * @return Verdadeiro se a ação foi bem-sucedida, falso se a ação falhou.
     */
    public boolean doAction(String a) {
        if (gameOver)
            return false;

        // Each action costs 1 score
        score -= 1;

        if (a.equals(A_MOVE)) {
            if (!isInPit) {
                if (dir == DIR_LEFT)
                    return move(pX - 1, pY);
                if (dir == DIR_RIGHT)
                    return move(pX + 1, pY);
                if (dir == DIR_UP)
                    return move(pX, pY + 1);
                if (dir == DIR_DOWN)
                    return move(pX, pY - 1);
            }
        }
        if (a.equals(A_TURN_LEFT)) {
            dir--;
            if (dir < 0)
                dir = 3;
            return true;
        }
        if (a.equals(A_TURN_RIGHT)) {
            dir++;
            if (dir > 3)
                dir = 0;
            return true;
        }
        if (a.equals(A_GRAB)) {
            if (hasGlitter(pX, pY)) {
                w[pX][pY] = w[pX][pY].replaceAll(GLITTER, "");
                score += 1000;
                hasGold = true;
                gameOver = true;
                return true;
            }
        }
        if (a.equals(A_SHOOT)) {
            if (hasArrow) {
                score -= 10;
                hasArrow = false;
                shoot();
                return true;
            }
        }
        if (a.equals(A_SHOOT1)) {
            if (hasArrow) {
                score -= 10;
                hasArrow = false;
                shoot1();
                return true;
            }
        }
        if (a.equals(A_CLIMB)) {
            isInPit = false;
        }

        if (a.equals(A_LANTERN)) {
            toggleLantern();
            return true;
        }

        // Action failed
        return false;
    }

    /**
     * Verifica se o Wumpus foi atingido pela flecha.
     */
    private void shoot() {
        if (dir == DIR_RIGHT) {
            for (int x = pX; x <= size; x++) {
                if (w[x][pY].contains(WUMPUS))
                    removeWumpus();
            }
        }
        if (dir == DIR_LEFT) {
            for (int x = pX; x >= 0; x--) {
                if (w[x][pY].contains(WUMPUS))
                    removeWumpus();
            }
        }
        if (dir == DIR_UP) {
            for (int y = pY; y <= size; y++) {
                if (w[pX][y].contains(WUMPUS))
                    removeWumpus();
            }
        }
        if (dir == DIR_DOWN) {
            for (int y = pY; y >= 0; y--) {
                if (w[pX][y].contains(WUMPUS))
                    removeWumpus();
            }
        }
    }

    /**
     * Remove o Wumpus (e o Fedor) do Mundo Wumpus.
     * Usado quando o Wumpus foi atingido pela flecha.
     */
    private void removeWumpus() {
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
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
     * @return True se as ações de movimentação foram bem-sucedidas, false caso
     *         contrário
     */
    private boolean move(int nX, int nY) {
        // Check if valid
        if (!isValidPosition(nX, nY)) {
            return false;
        }
        if (lanternOn) {
            // Ilumina as praças ao longo da linha ou coluna
            if (dir == DIR_RIGHT || dir == DIR_LEFT) {
                for (int x = Math.min(pX, nX); x <= Math.max(pX, nX); x++) {
                    for (int y = 1; y <= size; y++) {
                        setVisited(x, y);
                    }
                }
            } else if (dir == DIR_UP || dir == DIR_DOWN) {
                for (int y = Math.min(pY, nY); y <= Math.max(pY, nY); y++) {
                    for (int x = 1; x <= size; x++) {
                        setVisited(x, y);
                    }
                }
            }
        } else {
            // Atualiza a praça atual como visitada
            setVisited(nX, nY);
        }

        pX = nX;
        pY = nY;

        setVisited(pX, pY);

        if (hasWumpus(pX, pY)) {
            score -= 1000;
            gameOver = true;
        }
        if (hasMonster2(pX, pY)) {
            score -= 1000;
            gameOver = true;
        }
        if (hasPit(pX, pY)) {
            score -= 1000;
            isInPit = true;
        }

        return true;
    }

    /**
     * Verifica se o Monstro 2 foi atingido pela flecha.
     */
    private void shoot1() {
        if (dir == DIR_RIGHT) {
            for (int x = pX; x <= size; x++) {
                if (w[x][pY].contains(MONSTRO2))
                    removeMonstro2();
            }
        }
        if (dir == DIR_LEFT) {
            for (int x = pX; x >= 0; x--) {
                if (w[x][pY].contains(MONSTRO2))
                    removeMonstro2();
            }
        }
        if (dir == DIR_UP) {
            for (int y = pY; y <= size; y++) {
                if (w[pX][y].contains(MONSTRO2))
                    removeMonstro2();
            }
        }
        if (dir == DIR_DOWN) {
            for (int y = pY; y >= 0; y--) {
                if (w[pX][y].contains(MONSTRO2))
                    removeMonstro2();
            }
        }
    }

    /**
     * Remove o Monstro 2 (e o Fedor) do Mundo Wumpus.
     * Usado quando o Monstro 2 foi atingido pela flecha.
     */
    private void removeMonstro2() {
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                w[x][y] = w[x][y].replaceAll(MONSTRO2, "");
                w[x][y] = w[x][y].replaceAll(STENCH, "");
            }
        }

        monster2Alive = false;
    }

    /**
     * Move o Wumpus de acordo com as regras.
     */
    public void moveWumpus() {
        if (wumpusAlive) {
            do {
                newX = pX + (int) (Math.random() * 3) - 1;
                newY = pY + (int) (Math.random() * 3) - 1;
            } while (!isValidPosition(newX, newY) || hasPit(newX, newY)); // Verifica se há um poço na nova posição

            // Remove o Wumpus da posição atual
            w[pX][pY] = w[pX][pY].replace(WUMPUS, "");
            // Move o Wumpus para a nova posição
            addWumpus(newX, newY);
        }
    }

    /*
     * /**
     * Move o Monstro2.
     */
    public void moveMonstro2() {
        if (monster2Alive) {
            do {
                int moveX = (int) (Math.random() * 3) - 1;
                int moveY = (int) (Math.random() * 3) - 1;

                // Verifica se o movimento é válido para formar um "L"
                if ((moveX != 0 && moveY != 0) || (moveX == 0 && moveY == 0))
                    continue;

                newX = pX + moveX;
                newY = pY + moveY;
            } while (!isValidPosition(newX, newY) || hasPit(newX, newY) || hasWumpus(newX, newY)
                    || hasPlayer(newX, newY)); // Verifica se há um poço, Wumpus ou jogador na nova posição

            // Remove o Monstro2 da posição atual
            w[pX][pY] = w[pX][pY].replace(MONSTRO2, "");
            // Move o Monstro2 para a nova posição
            addMonstro2(newX, newY);

            // Atualiza as variáveis pX e pY para a nova posição do Monstro2
            pX = newX;
            pY = newY;

        }
    }

}
