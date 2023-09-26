package wumpusworld;

import java.io.*;

/**
 * lê o arquivo config txt
 */
public class Config {
    /**
     * Executa o GUI
     */
    public static String getOption() {
        String option = "gui";
        try {
            BufferedReader r = new BufferedReader(new FileReader("config.txt"));
            String line = r.readLine();
            while (line != null) {
                if (line.startsWith("Option=")) {
                    String[] tokens = line.split("=");
                    if (tokens[1].equalsIgnoreCase("gui"))
                        option = "gui";
                }
                line = r.readLine();
            }
            r.close();
        } catch (Exception ex) {
            option = "gui";
        }
        return option;
    }

    /**
     * Retorna o caminho para o arquivo de mapa.
     */
    public static String getMapfile() {
        String mapfile = "";
        try {
            BufferedReader r = new BufferedReader(new FileReader("config.txt"));
            String line = r.readLine();
            while (line != null) {
                if (line.startsWith("Mapfile=")) {
                    String[] tokens = line.split("=");
                    mapfile = tokens[1];
                }
                line = r.readLine();
            }
            r.close();
        } catch (Exception ex) {
            mapfile = "";
        }
        return mapfile;
    }
}
