package model;

public class Input
{
    private Neuron neuron;
    private Neuron parentNeuron;
    private double weight;
    
    public Input(Neuron neuron, double weight, Neuron parentNeuron)
    {
        super();
        this.neuron = neuron;
        this.weight = weight;
        this.parentNeuron = parentNeuron;
    }

    public double getValueDependsOnWeight(){
        return weight * neuron.getOutput();
    }

    public double getInfluenceFactor()
    {
        return parentNeuron.getErrorRate() * weight;
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
    public double getInputValue()
    {
        return neuron.getOutput();
    }
    public Neuron getParentNeuron() {
        return parentNeuron;
    }
}
