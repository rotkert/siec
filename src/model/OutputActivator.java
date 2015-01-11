package model;

import java.util.List;

import model.interfaces.Activator;

public class OutputActivator implements Activator
{

    @Override
    public double activate(List<Input> inputs)
    {
        double sum = 0;
        for (Input input : inputs)
        {
            sum += input.getValue();
        }
        
        
        return sum;
    }

    @Override
    public double activateDeriv(List<Input> inputs)
    {
        return 1;
    }

}
