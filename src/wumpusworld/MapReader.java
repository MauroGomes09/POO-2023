package wumpusworld;

import java.io.*;
import java.util.Vector;

/**
 * Esta classe lê mapas de um arquivo.
 * 
 */
public class MapReader {
    private Vector<WorldMap> maps;
    private String mapFilename;

    /**
     * Cria uma nova instância da classe.
     */
    public MapReader() {
        mapFilename = Config.getMapfile();
        maps = new Vector<WorldMap>();
    }

    /**
     * Leia os mapas do arquivo de mapa e retorne um vetor
     * com os mapas. O programa é encerrado se ocorrer um erro
     * encontrado.
     *
     * @return Uma lista de objetos do mapa ou uma lista vazia se nenhum for
     *         encontrado.
     */
    public Vector<WorldMap> readMaps() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapFilename));

            WorldMap wm = null;
            String line = reader.readLine();
            while (line != null) {
                line = line.toUpperCase();

                // New map
                if (line.startsWith("NEW")) {
                    String[] t = split(line);
                    int size = Integer.parseInt(t[1]);
                    wm = new WorldMap(size);
                }

                // Pit
                if (line.startsWith("P")) {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addPit(x, y);
                }

                // Wumpus
                if (line.startsWith("W")) {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addWumpus(x, y);
                }

                // Monstro 2
                if (line.startsWith("M")) {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addMonster2(x, y);
                }

                // Gold
                if (line.startsWith("G")) {
                    String[] t = split(line);
                    int x = Integer.parseInt(t[1]);
                    int y = Integer.parseInt(t[2]);
                    wm.addGold(x, y);
                }

                // End of map
                if (line.startsWith("END")) {
                    maps.add(wm);
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (Exception ex) {

        }

        // Adiciona alguns mapas aleatórios
        maps.add(MapGenerator.getRandomMap(42));
        maps.add(MapGenerator.getRandomMap(1977));
        maps.add(MapGenerator.getRandomMap(1990));

        return maps;
    }

    /**
     * Divide uma string com espaços em branco como delimitador.
     *
     * @param line A string a ser dividida
     * @return Tokens
     */
    private String[] split(String line) {
        return line.split(" ");
    }
}
