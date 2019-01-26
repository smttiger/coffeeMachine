package com.itstep.fabiyanski;

import com.itstep.fabiyanski.Coffee.Americano;
import com.itstep.fabiyanski.Coffee.Late;
import com.itstep.fabiyanski.exceptions.CoffeeMachineException;

//задание 3
public interface TheNewestCoffeeMachine {
    void enable();

    void disable();

    Americano makeAmericano() throws CoffeeMachineException;

    Late makeLate() throws CoffeeMachineException;

    void grindCoffee();
}
