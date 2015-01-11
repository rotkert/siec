package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.sun.javafx.scene.paint.GradientUtils.Point;

public class TrainPanel extends JPanel implements MouseListener
{
    MainFrame mainFrame;
    ArrayList<MyPoint> points = new ArrayList<>();
    
    TrainPanel(MainFrame mainFrame)
    {
        addMouseListener(this);
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        int x = arg0.getX();
        int y = arg0.getY();
        double result;
        result = arg0.getButton() == MouseEvent.BUTTON1 ? 1 : 0 ;
        
        x /= 5;
        y /= 5;
        System.out.println(x + " " + y);
        
        double newx = (double) x / 100; 
        double newy = (double) y / 100; 
        mainFrame.network.teach(newx , newy, result);
        points.add(new MyPoint(x*5, y*5, result));
        repaint();
        mainFrame.run();
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        
       
        for (MyPoint point : points)
        {
            if(point.result == 1)
                g2.setColor(Color.white);
            else
                g2.setColor(Color.black);
            g2.drawRect(point.x, point.y, 5, 5);
        }
    }

}
