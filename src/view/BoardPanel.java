package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
    ArrayList<MyPoint> points = new ArrayList<>();
    MainFrame mainFrame;
    
    public BoardPanel(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        for (int i = 0; i < 3600; i++)
        {
            points.add(new MyPoint(i % 60, i / 60, 1));
        }
    }
    
    public void run()
    {
        for (MyPoint point : points)
        {
           mainFrame.network.calculate(point.x, point.y);
           point.result = mainFrame.network.getResultW() > mainFrame.network.getResultB() ? 1 : 0;
        }
        repaint();
    }
    
    void newNetwork()
    {
        for (MyPoint point : points)
        {
            point.result = 1;
        }
        repaint();
    }
    
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for (MyPoint point : points)
        {
                if(point.result > 0.5)
                    g2.setColor(Color.white);
                else
                    g2.setColor(Color.black);
                g2.fillRect(point.x*8, point.y*8, 8, 8);
        }
    }
    
}
