package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Activator;

public class Neuron
{
    private List<Input> inputs;
    private List<Input> forwardInputs;
    private Activator activator;
    private double output;
    private double errorRate;
    
    public Neuron()
    {
        inputs = new ArrayList<Input>();
        forwardInputs = new ArrayList<Input>();
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
        Input input = new Input(neuron, weight, this);
        inputs.add(input);
        neuron.addForwardInput(input);
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
    public double getErrorRate() {
        return errorRate;
    }
    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }
    public void addForwardInput(Input input)
    {
        forwardInputs.add(input);
    }

    public List<Input> getForwardInputs() {
        return forwardInputs;
    }
}
