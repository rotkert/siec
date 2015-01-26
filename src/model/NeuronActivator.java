package model;

import java.util.List;

import model.interfaces.Activator;

public class NeuronActivator implements Activator
{

    @Override
    public double activate(List<Input> inputs)
    {
        double sum = 0;
        for (Input input : inputs)
        {
            sum += input.getValueDependsOnWeight();
        }
        
        sum = Math.atan(sum);
        return sum;
        
    }

    @Override
    public double activateDeriv(List<Input> inputs)
    {
        double sum = 0;
        for (Input input : inputs)
        {
            sum += input.getValueDependsOnWeight();
        }
        
        sum = 1/(1 + sum * sum);
        return sum;
    }

}
