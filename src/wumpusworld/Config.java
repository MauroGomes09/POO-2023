package wumpusworld;

import java.io.*;

/**
 * lê o arquivo config txt
 */
public class Config 
{
 /**
     * Verifica as opções de execução da aplicação (`gui`, `sim` ou `simdb`).
     *
     * @return Opção de execução da aplicação. O padrão é `gui`.
     */
    public static String getOption()
    {
        String option = "gui";
        try
        {
            BufferedReader r = new BufferedReader(new FileReader("config.txt"));
            String line = r.readLine();
            while (line != null)
            {
                if (line.startsWith("Option="))
                {
                    String[] tokens = line.split("=");
                    if (tokens[1].equalsIgnoreCase("gui")) option = "gui";
                    if (tokens[1].equalsIgnoreCase("sim")) option = "sim";
                    if (tokens[1].equalsIgnoreCase("simulator")) option = "sim";
                    if (tokens[1].equalsIgnoreCase("simdb")) option = "simdb";
                    if (tokens[1].equalsIgnoreCase("simulatordb")) option = "simdb";
                }
                line = r.readLine();
            }
            r.close();
        }
        catch (Exception ex)
        {
            option = "gui";
        }
        return option;
    }
    
   /**
     * Retorna o caminho para o arquivo de mapa.
     * @return Caminho para o arquivo de mapa, ou uma string vazia se o arquivo não for encontrado.
     */
    public static String getMapfile()
    {
        String mapfile = "";
        try
        {
            BufferedReader r = new BufferedReader(new FileReader("config.txt"));
            String line = r.readLine();
            while (line != null)
            {
                if (line.startsWith("Mapfile="))
                {
                    String[] tokens = line.split("=");
                    mapfile = tokens[1];
                }
                line = r.readLine();
            }
            r.close();
        }
        catch (Exception ex)
        {
            mapfile = "";
        }
        return mapfile;
    }
}
