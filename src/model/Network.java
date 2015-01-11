package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.interfaces.Activator;

public class Network
{
    private Neuron neuronX = new Neuron();
    private Neuron neuronY = new Neuron();
    private Neuron last = new Neuron();
    private List<Neuron> layer = new ArrayList<Neuron>();
    
    public Network()
    {
        Activator activator = new NeuronActivator();
        Activator outputActivator= new OutputActivator();
        last.setActivator(activator);
        Neuron bias = new Neuron();
        bias.setActivator(new BiasActivator());
        last.addInput(bias, new Random().nextDouble());
        
        
        Random random = new Random();
        for(int i = 0; i < 20; i++)
        {
            Neuron neuron = new Neuron();
            neuron.addInput(neuronX, (-1 + random.nextDouble() * 2));
            neuron.addInput(neuronY, (-1 + random.nextDouble() * 2));
            neuron.addInput(bias, (-1 + random.nextDouble() * 2));
            neuron.setActivator(activator);
            last.addInput(neuron, random.nextDouble());
            layer.add(neuron);
        }        
    }
    
    public double calculate(double x, double y)
    {
        neuronX.setOutput(x);
        neuronY.setOutput(y);
        for (Neuron neuron: layer)
        {
            neuron.active();
        }
        last.active();
        double result = last.getOutput();
        return result;
    }
    
    public void teach(double x, double y, double result)
    {
        double beta = 0.1;
        for(int i = 0; i< 1000; i++)
        {
            double calculated = calculate(x, y);
            double lastError = last.activateDeriv() * (result - calculated);
            System.out.println(calculated);
            for(Input input: last.getInputs())
            {
                double lastWeight = input.getWeight();
                input.setWeight(input.getWeight() + beta * lastError * input.getInput());
                Neuron currentNeuron = input.getNeuron();
                double currentNeuronLastError = currentNeuron.activateDeriv() * lastWeight * lastError;
                for(Input currentNeuronInput: currentNeuron.getInputs())
                {
                    currentNeuronInput.setWeight(currentNeuronInput.getWeight() + beta * currentNeuronLastError * input.getInput());
                }
                
            }
        }
    }


}
