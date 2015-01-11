package controllers;

import javax.swing.SwingUtilities;

import view.MainFrame;
import model.Network;

public class Main
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MainFrame mainFrame = new MainFrame();
            }
        });
    }

}
