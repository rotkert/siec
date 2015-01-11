package model;

import java.util.List;

import model.interfaces.Activator;

public class BiasActivator implements Activator
{

    @Override
    public double activate(List<Input> inputs)
    {
        return 1;
    }

    @Override
    public double activateDeriv(List<Input> inputs)
    {
        // TODO Auto-generated method stub
        return 1;
    }

}
