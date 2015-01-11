package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Activator;

public class Neuron
{
    private List<Input> inputs;
    private Activator activator;
    private double output;
    
    public Neuron()
    {
        inputs = new ArrayList<Input>();
    }
    
    public void active()
    {
        this.output = activator.activate(inputs);
    }
    
    
    
    public double activateDeriv()
    {
        return activator.activateDeriv(inputs);
    }

    public List<Input> getInputs()
    {
        return inputs;
    }
    
    public void addInput(Neuron neuron, double weight)
    {
        inputs.add(new Input(neuron, weight));
    }
    
    public void setOutput(double output)
    {
        this.output = output;
    }

    public double getOutput()
    {
        return output;
    }
    public void setActivator(Activator activator)
    {
        this.activator = activator;
    }
    
    
    
    
    
}
