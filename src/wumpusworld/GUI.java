package wumpusworld;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel gamepanel;
    private JLabel score;
    private JLabel status;
    private World w;
    private Agent agent;
    private JPanel[][] blocks = new JPanel[15][15];
    private JComboBox mapList;
    private Vector<WorldMap> maps;

    private ImageIcon l_breeze;
    private ImageIcon l_stench;
    private ImageIcon l_pit;
    private ImageIcon l_glitter;
    private ImageIcon l_wumpus;
    private ImageIcon l_monstro2;
    private ImageIcon l_player_up;
    private ImageIcon l_player_down;
    private ImageIcon l_player_left;
    private ImageIcon l_player_right;

   
    public GUI() {
        if (!checkResources()) {
            JOptionPane.showMessageDialog(null, "Não é possível iniciar a GUI. Ícones ausentes.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        MapReader mr = new MapReader();
        maps = mr.readMaps();
        if (maps.size() > 0) {
            w = maps.get(0).generateWorld();
        } else {
            w = MapGenerator.getRandomMap((int) System.currentTimeMillis()).generateWorld();
        }

        l_breeze = new ImageIcon("gfx/B.png");
        l_stench = new ImageIcon("gfx/S.png");
        l_pit = new ImageIcon("gfx/P.png");
        l_glitter = new ImageIcon("gfx/G.png");
        l_wumpus = new ImageIcon("gfx/W.png");
        l_monstro2 = new ImageIcon("gfx/M.png");
        l_player_up = new ImageIcon("gfx/PU.png");
        l_player_down = new ImageIcon("gfx/PD.png");
        l_player_left = new ImageIcon("gfx/PL.png");
        l_player_right = new ImageIcon("gfx/PR.png");

        createWindow();
    }

  
    private boolean checkResources() {
        try {
            File f;
            f = new File("gfx/B.png");
            if (!f.exists())
                return false;
            f = new File("gfx/S.png");
            if (!f.exists())
                return false;
            f = new File("gfx/P.png");
            if (!f.exists())
                return false;
            f = new File("gfx/G.png");
            if (!f.exists())
                return false;
            f = new File("gfx/W.png");
            if (!f.exists())
                return false;
            f = new File("gfx/PU.png");
            if (!f.exists())
                return false;
            f = new File("gfx/PD.png");
            if (!f.exists())
                return false;
            f = new File("gfx/PL.png");
            if (!f.exists())
                return false;
            f = new File("gfx/PR.png");
            if (!f.exists())
                return false;
            f = new File("gfx/M.png");
            if (!f.exists())
                return false;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

   
    private void createWindow() {
        frame = new JFrame("Wumpus World");
        frame.setSize(1920, 1080);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gamepanel = new JPanel();
        gamepanel.setPreferredSize(new Dimension(1890, 790));
        gamepanel.setBackground(Color.GRAY);
        gamepanel.setLayout(new GridLayout(15, 15));

        // Add blocks
        blocks = new JPanel[15][15];
        for (int j = 14; j >= 0; j--) {
            for (int i = 0; i < 15; i++) {
                blocks[i][j] = new JPanel();
                blocks[i][j].setBackground(Color.red);

                int blockSize = 10;

                blocks[i][j].setPreferredSize(new Dimension(blockSize, blockSize));
                blocks[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                blocks[i][j].setLayout(new GridLayout(1, 1));
                gamepanel.add(blocks[i][j]);
            }
        }
        frame.getContentPane().add(gamepanel);

        // Add buttons
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(820, 740));
        buttons.setLayout(new FlowLayout());
        // Status 
        status = new JLabel("", SwingConstants.CENTER);
        status.setPreferredSize(new Dimension(820, 25));
        buttons.add(status);
        // Score 
        score = new JLabel("Score: 0", SwingConstants.CENTER);
        score.setPreferredSize(new Dimension(820, 25));
        buttons.add(score);
        // Buttons
        JButton bl = new JButton(new ImageIcon("gfx/TL.png"));
        bl.setActionCommand("TL");
        bl.addActionListener(this);
        buttons.add(bl);
        // Mf
        JButton bf = new JButton(new ImageIcon("gfx/MF.png"));
        bf.setActionCommand("MF");
        bf.addActionListener(this);
        buttons.add(bf);
        // TR
        JButton br = new JButton(new ImageIcon("gfx/TR.png"));
        br.setActionCommand("TR");
        br.addActionListener(this);
        buttons.add(br);
        // pegar
        JButton bg = new JButton("Pegar");
        bg.setPreferredSize(new Dimension(95, 22));
        bg.setActionCommand("Pegar");
        bg.addActionListener(this);
        buttons.add(bg);
        // Sair
        JButton bc = new JButton("Sair");
        bc.setPreferredSize(new Dimension(95, 22));
        bc.setActionCommand("Sair");
        bc.addActionListener(this);
        buttons.add(bc);
        // Atirar
        JButton bs = new JButton("Atirar");
        bs.setPreferredSize(new Dimension(95, 22));
        bs.setActionCommand("Atirar");
        bs.addActionListener(this);
        buttons.add(bs);
        // lanterna
        JButton lanternButton = new JButton("Lanterna");
        lanternButton.setPreferredSize(new Dimension(95, 22));
        lanternButton.setActionCommand("Lanterna");
        lanternButton.addActionListener(this);
        buttons.add(lanternButton);
        // Add a resolução
        JButton ba = new JButton("Executar Resolução");
        ba.setActionCommand("AGENT");
        ba.addActionListener(this);
        buttons.add(ba);
        // Add a delimiter
        JLabel l = new JLabel("");
        l.setPreferredSize(new Dimension(220, 25));
        buttons.add(l);
        // Fill dropdown list
        Vector<String> items = new Vector<String>();
        items.add("Random");
        for (int i = 0; i < maps.size(); i++) {
            items.add((i + 1) + "");
        }
        mapList = new JComboBox(items);
        mapList.setPreferredSize(new Dimension(220, 25));
        buttons.add(mapList);
        JButton bn = new JButton("New Game");
        bn.setActionCommand("NEW");
        bn.addActionListener(this);
        buttons.add(bn);

        frame.getContentPane().add(buttons);

        updateGame();

        // Mostra a janela
        frame.setVisible(true);
    }

    /**
     * Comandos dos botões

     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("TL")) {
            w.doAction(World.A_TURN_LEFT);
            updateGame();
        }
        if (e.getActionCommand().equals("TR")) {
            w.doAction(World.A_TURN_RIGHT);
            updateGame();
        }
        if (e.getActionCommand().equals("MF")) {
            w.doAction(World.A_MOVE);
            updateGame();
        }
        if (e.getActionCommand().equals("GRAB")) {
            w.doAction(World.A_GRAB);
            updateGame();
        }
        if (e.getActionCommand().equals("CLIMB")) {
            w.doAction(World.A_CLIMB);
            updateGame();
        }
        if (e.getActionCommand().equals("SHOOT")) {
            w.doAction(World.A_SHOOT);
            updateGame();
        }
        if (e.getActionCommand().equals("SHOOT")) {
            w.doAction(World.A_SHOOT1);
            updateGame();
        }
        if (e.getActionCommand().equals("Lanterna")) {
            w.doAction(World.A_LANTERN);
            updateGame();
        }
        if (e.getActionCommand().equals("NEW")) {
            String s = (String) mapList.getSelectedItem();
            if (s.equalsIgnoreCase("Random")) {
                w = MapGenerator.getRandomMap((int) System.currentTimeMillis()).generateWorld();
            } else {
                int i = Integer.parseInt(s);
                i--;
                w = maps.get(i).generateWorld();
            }
            agent = new MyAgent(w);
            updateGame();
        }
        if (e.getActionCommand().equals("AGENT")) {
            if (agent == null) {
                agent = new MyAgent(w);
            }
            agent.doAction();
            updateGame();
        }
    }

    
    private void updateGame() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                blocks[i][j].removeAll();
                blocks[i][j].setBackground(Color.WHITE);
                if (w.hasPit(i + 1, j + 1)) {
                    blocks[i][j].add(new JLabel(l_pit));
                }
                if (w.hasBreeze(i + 1, j + 1)) {
                    blocks[i][j].add(new JLabel(l_breeze));
                }
                if (w.hasStench(i + 1, j + 1)) {
                    blocks[i][j].add(new JLabel(l_stench));
                }
                if (w.hasWumpus(i + 1, j + 1)) {
                    blocks[i][j].add(new JLabel(l_wumpus));
                }
                if (w.hasMonster2(i + 1, j + 1)) {
                    blocks[i][j].add(new JLabel(l_monstro2));
                }
                if (w.hasGlitter(i + 1, j + 1)) {
                    blocks[i][j].add(new JLabel(l_glitter));
                }
                if (w.hasPlayer(i + 1, j + 1)) {
                    if (w.getDirection() == World.DIR_DOWN)
                        blocks[i][j].add(new JLabel(l_player_down));
                    if (w.getDirection() == World.DIR_UP)
                        blocks[i][j].add(new JLabel(l_player_up));
                    if (w.getDirection() == World.DIR_LEFT)
                        blocks[i][j].add(new JLabel(l_player_left));
                    if (w.getDirection() == World.DIR_RIGHT)
                        blocks[i][j].add(new JLabel(l_player_right));
                }
                if (w.isUnknown(i + 1, j + 1)) {
                    blocks[i][j].setBackground(Color.decode("#664323"));

                }

                blocks[i][j].updateUI();
                blocks[i][j].repaint();
            }
        }

        score.setText("Score: " + w.getScore());
        status.setText("");
        if (w.isInPit()) {
            status.setText("O jogador deve subir!");
        }
        if (w.gameOver()) {
            status.setText("GAME OVER");
        }

        gamepanel.updateUI();
        gamepanel.repaint();
    }
}