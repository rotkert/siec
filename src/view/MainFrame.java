package view;

import javax.swing.JFrame;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Network;

public class MainFrame extends JFrame
{
    public TrainPanel trainPanel = new TrainPanel(this);
    public BoardPanel boardPanel = new BoardPanel(this);
    public MenuPanel menuPanel = new MenuPanel();
    public Network network = new Network();
    public MainFrame()
    {
        trainPanel.setBackground(Color.blue);
        boardPanel.setBackground(Color.red);
        getContentPane().setLayout(new GridLayout(1, 4, 0, 0));
        getContentPane().add(trainPanel);
        getContentPane().add(boardPanel);
        getContentPane().add(menuPanel);
        setVisible(true);
        setBounds(300, 200, 1200, 600);
    }
    
    void run()
    {
        boardPanel.run();
    }

}
