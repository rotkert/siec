package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 2015-01-21.
 */
public class Layer {
    private List<Neuron> neurons = new ArrayList<>();

    public Neuron get(int index) {
        return neurons.get(index);
    }

    public boolean add(Neuron neuron) {
        return neurons.add(neuron);
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }
}
