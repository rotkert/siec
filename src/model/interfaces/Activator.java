package model.interfaces;

import java.util.List;

import model.Input;

public interface Activator
{
    double activate(List<Input> inputs);
    double activateDeriv(List<Input> inputs);
}
