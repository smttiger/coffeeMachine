package com.itstep.fabiyanski;

import com.itstep.fabiyanski.Coffee.Americano;
import com.itstep.fabiyanski.Coffee.Espresso;
import com.itstep.fabiyanski.exceptions.CoffeeMachineException;

public interface CoffeeMachine {
    void enable();

    void disable();

    Espresso makeEspresso() throws CoffeeMachineException;

    Americano makeAmericano() throws CoffeeMachineException ;

    void cleanUp();


}
