package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.interfaces.Activator;

public class Network
{
    private Neuron neuronX = new Neuron();
    private Neuron neuronY = new Neuron();
    private Neuron lastW = new Neuron();
    private Neuron lastB = new Neuron();
    private List<Neuron> layer = new ArrayList<Neuron>();
    private List<TeachPoint> teachPoints = new ArrayList<>();
    
    public Network()
    {
        Activator activator = new NeuronActivator();
        Activator outputActivator= new OutputActivator();
        lastW.setActivator(outputActivator);
        lastB.setActivator(outputActivator);
        Neuron bias = new Neuron();
        bias.setActivator(new BiasActivator());
        bias.active();
        lastW.addInput(bias, new Random().nextDouble());
        lastB.addInput(bias, new Random().nextDouble());

        Random random = new Random();
        for(int i = 0; i < 20; i++)
        {
            Neuron neuron = new Neuron();
            neuron.addInput(neuronX, (-1 + random.nextDouble() * 2));
            neuron.addInput(neuronY, (-1 + random.nextDouble() * 2));
            neuron.addInput(bias, (-1 + random.nextDouble() * 2));
            neuron.setActivator(activator);
            lastW.addInput(neuron, random.nextDouble());
            lastB.addInput(neuron, random.nextDouble());
            layer.add(neuron);
        }        
    }
    
    public void calculate(double x, double y)
    {
        neuronX.setOutput(x);
        neuronY.setOutput(y);
        for (Neuron neuron: layer)
        {
            neuron.active();
        }
        lastW.active();
        lastB.active();
    }

    public double getResultW()
    {
        return lastW.getOutput();
    }
    public double getResultB()
    {
        return lastB.getOutput();
    }
    
    public void teach()
    {
        double beta = 0.006;
        for(int i =0; i< 10000; i++) {
            Collections.shuffle(teachPoints);
            TeachPoint teachPoint = teachPoints.get(0);
            calculate(teachPoint.getX(), teachPoint.getY());
            double calculatedW = getResultW();
            double calculatedB = getResultB();
            lastW.setErrorRate(lastW.activateDeriv() * (teachPoint.getResultW() - calculatedW));
            lastB.setErrorRate(lastB.activateDeriv() * (teachPoint.getResultB() - calculatedB));
            System.out.println(String.format("calculatedW: %s, calculatedB: %s, resultW: %s resultB: %s", calculatedW, calculatedB, teachPoint.getResultW(), teachPoint.getResultB()));

            for (Input inputW : lastW.getInputs()) {
                for(Input inputB: lastB.getInputs()){
                    if(inputW.getNeuron() == inputB.getNeuron()){
                        Neuron currentNeuron = inputW.getNeuron();
                        currentNeuron.setErrorRate(currentNeuron.activateDeriv() * (inputW.getInfluenceFactor() + inputB.getInfluenceFactor()));
                    }
                }
            }

            for(Neuron neuron:layer)
            {
                for(Input input: neuron.getInputs()){
                    input.setWeight(input.getWeight() + (beta * neuron.getErrorRate() * input.getInputValue()));
                }
            }
            for (Input input : lastW.getInputs()) {
                input.setWeight(input.getWeight() + beta * lastW.getErrorRate() * input.getInputValue());
            }
            for (Input input : lastB.getInputs()) {
                input.setWeight(input.getWeight() + beta * lastB.getErrorRate() * input.getInputValue());
            }
        }
    }

    public void addTeachPoint(double x ,double y, double resultW, double resultB)
    {
        teachPoints.add(new TeachPoint(x, y, resultW, resultB));
    }


}
