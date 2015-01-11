package model;

public class Input
{
    private Neuron neuron;
    private double weight;
    
    public Input(Neuron neuron, double weight)
    {
        super();
        this.neuron = neuron;
        this.weight = weight;
    }

    public double getValue(){
        return weight * neuron.getOutput();
    }
    
    public double getWeight()
    {
        return weight;
    }
    public void setWeight(double weight)
    {
        this.weight = weight;
    }
    public void setNeuron(Neuron neuron)
    {
        this.neuron = neuron;
    }

    public Neuron getNeuron()
    {
        return neuron;
    }
    public double getInput()
    {
        return neuron.getOutput();
    }
    
}
