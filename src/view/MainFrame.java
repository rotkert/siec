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
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class MainFrame extends JFrame
{
    public TrainPanel trainPanel = new TrainPanel(this);
    public BoardPanel boardPanel = new BoardPanel(this);
    public Network network;
    private final JPanel panel = new JPanel();
    private final JButton btnNewButton = new JButton("Rysuj");
    private final JButton btnNewButton_2 = new JButton("Nowa siec");
    private final JPanel panel_1 = new JPanel();
    int loops = 0;
    private final JSpinner spinner = new JSpinner();
    private final JSpinner spinner_1 = new JSpinner();
    private final JSpinner spinner_2 = new JSpinner();
    private final JLabel lblNewLabel = new JLabel("Warstwy");
    private final JLabel lblNewLabel_1 = new JLabel("Neurony");
    private final JLabel lblNewLabel_2 = new JLabel("Beta");
    private Timer timer;
    
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
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        
        panel_1.add(lblNewLabel);
        spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        
        panel_1.add(spinner);
        lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel_1.add(lblNewLabel_1);
        spinner_1.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
        
        panel_1.add(spinner_1);
        lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel_1.add(lblNewLabel_2);
        spinner_2.setModel(new SpinnerNumberModel(new Double(0.006), 0.001, null, new Double(0.001)));
        
        panel_1.add(spinner_2);
        
        network = new Network((Integer)spinner.getValue(), (Integer)spinner_1.getValue(), (Double)spinner_2.getNextValue());
        
        btnNewButton.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // TODO Auto-generated method stub
                System.out.println("uczenie");
                network.teach();
                run();
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
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
                if(timer != null)
                {
                    timer.cancel();
                    timer.purge();
                }
                network = new Network((Integer)spinner.getValue(), (Integer)spinner_1.getValue(), (Double)spinner_2.getNextValue());
                trainPanel.newNetwork();
                boardPanel.newNetwork();
            }
        });
        setVisible(true);
        setBounds(300, 200, 992, 551);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    void run()
    {
        boardPanel.run();
    }
    

}
