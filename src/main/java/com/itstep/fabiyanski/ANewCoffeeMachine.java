package com.itstep.fabiyanski;

import com.itstep.fabiyanski.Coffee.Capuchino;
import com.itstep.fabiyanski.Coffee.Late;
import com.itstep.fabiyanski.exceptions.CoffeeMachineException;

// задание 2
public interface ANewCoffeeMachine extends CoffeeMachine {
    Late makeLate() throws CoffeeMachineException;

    Capuchino makeCapuchino() throws CoffeeMachineException;
}
