package model;

import model.interfaces.Activator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Network
{
    public static final int INPUT_X = 0;
    public static final int INPUT_Y = 1;
    public static final int OUTPUT_W= 0;
    public static final int OUTPUT_B= 1;

    private List<Layer> layers = new ArrayList<>();
    private List<TeachPoint> teachPoints = Collections.synchronizedList(new ArrayList<TeachPoint>());
    private Random random = new Random();


    public Network(int layersCount, int neuronsPerLayer)
    {
        Activator activator = new NeuronActivator();
        Activator outputActivator= new OutputActivator();
        Neuron bias = new Neuron();
        bias.setActivator(new BiasActivator());
        bias.active();

        //input layer
        Layer firstLayer = new Layer();
        firstLayer.add(new Neuron());
        firstLayer.add(new Neuron());
        layers.add(firstLayer);

        for(int i = 0; i < layersCount ; i++){
            Layer currentLayer = new Layer();
            layers.add(currentLayer);
            for(int j = 0; j < neuronsPerLayer; j++)
            {
                Neuron neuron = new Neuron();
                for(Neuron previousLayerNeuron : layers.get(i).getNeurons())
                {
                    neuron.addInput(previousLayerNeuron, (-1 + random.nextDouble() * 2));
                }
                neuron.addInput(bias, (-1 + random.nextDouble() * 2));
                neuron.setActivator(activator);
                currentLayer.add(neuron);
            }
        }

        //add output layer
        Layer lastLayer = new Layer();
        for(int j = 0; j < 2; j++)
        {
            Neuron neuron = new Neuron();
            for(Neuron previousLayerNeuron : getLastLayer())
            {
                neuron.addInput(previousLayerNeuron, (-1 + random.nextDouble() * 2));
            }
            neuron.addInput(bias, (-1 + random.nextDouble() * 2));
            neuron.setActivator(outputActivator);
            lastLayer.add(neuron);
        }
        layers.add(lastLayer);

    }
    
    public void calculate(double x, double y)
    {
        setNetworkInput(x, y);

        for(int i = 1; i < layers.size(); i++)
        {
            for(Neuron neuron: layers.get(i).getNeurons())
            {
                neuron.active();
            }
        }
    }

    public double getResultW()
    {
        return getLastLayer().get(OUTPUT_W).getOutput();
    }
    public double getResultB()
    {
        return getLastLayer().get(OUTPUT_B).getOutput();
    }
    
    public void teach(double beta)
    {
        if(teachPoints.size() == 0 ) return;
        
        for(int i = 0; i < 1000; i++) {
            TeachPoint teachPoint = teachPoints.get(random.nextInt(teachPoints.size()));

            calculate(teachPoint.getX(), teachPoint.getY());
            double calculatedW = getResultW();
            double calculatedB = getResultB();
            //obliczenie bledu wyjsc
            getOutputW().setErrorRate(getOutputW().activateDeriv() * (teachPoint.getResultW() - calculatedW));
            getOutputB().setErrorRate(getOutputB().activateDeriv() * (teachPoint.getResultB() - calculatedB));

            //obliczenie bledu pozostalych neuronow
            for (int currentLayerNumber = (layers.size() - 2); currentLayerNumber > 0; currentLayerNumber--) 
            {
                for (Neuron neuron : layers.get(currentLayerNumber).getNeurons()) 
                {
                    double error = 0;
                    for (Input forwardInput : neuron.getForwardInputs()) 
                    {
                        error += forwardInput.getInfluenceFactor();
                    }
                    neuron.setErrorRate(neuron.activateDeriv() * error);
                }

            }
            
            //aktualizacja wag
            for (int currentLayerNumber = 1; currentLayerNumber < layers.size(); currentLayerNumber++) 
            {
                for (Neuron neuron : layers.get(currentLayerNumber).getNeurons()) 
                {
                    for (Input input : neuron.getInputs()) 
                    {
                        input.setWeight(input.getWeight() + beta * neuron.getErrorRate() * input.getInputValue());
                    }
                }

            }
        }
    }

    public void addTeachPoint(double x ,double y, double resultW, double resultB)
    {
        teachPoints.add(new TeachPoint(x, y, resultW, resultB));
    }

    private void setNetworkInput(double x, double y)
    {
        List<Neuron> firstLayer = layers.get(0).getNeurons();
        firstLayer.get(INPUT_X).setOutput(x);
        firstLayer.get(INPUT_Y).setOutput(y);

    }

    private List<Neuron> getLastLayer()
    {
        return layers.get(layers.size() - 1).getNeurons();
    }

    private Neuron getOutputW()
    {
        return getLastLayer().get(OUTPUT_W);
    }

    private Neuron getOutputB()
    {
        return getLastLayer().get(OUTPUT_B);
    }


}
