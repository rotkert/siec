package view;

import javax.swing.JFrame;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Network;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class MainFrame extends JFrame
{
    public TrainPanel trainPanel = new TrainPanel(this);
    public BoardPanel boardPanel = new BoardPanel(this);
    public Network network = new Network(3, 10);
    private final JPanel panel = new JPanel();
    private final JButton btnNewButton = new JButton("Rysuj");
    private final JButton btnNewButton_2 = new JButton("Nowa siec");
    private final JPanel panel_1 = new JPanel();
    int loops = 0;
    public MainFrame()
    {
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 2, 0, 0));
        trainPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5));
        panel.add(trainPanel);
        trainPanel.setBackground(Color.LIGHT_GRAY);
        boardPanel.setBorder(new LineBorder(Color.DARK_GRAY, 5));
        panel.add(boardPanel);
        boardPanel.setBackground(Color.LIGHT_GRAY);
        panel_1.setBackground(Color.DARK_GRAY);
        
        getContentPane().add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(0, 10, 0, 0));
        panel_1.add(btnNewButton);
        panel_1.add(btnNewButton_2);
        btnNewButton.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                System.out.println("uczenie");
                network.teach();
                run();
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        loops = (loops + 1) % 40;
                        network.teach();
                        if(loops == 0)
                            boardPanel.run();
                    }
                }, 0, 25);

            }
        });
        btnNewButton_2.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                network = new Network(2, 10);
                trainPanel.newNetwork();
                boardPanel.newNetwork();
            }
        });
        setVisible(true);
        setBounds(300, 200, 992, 551);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    void run()
    {
        boardPanel.run();
    }
    

}
