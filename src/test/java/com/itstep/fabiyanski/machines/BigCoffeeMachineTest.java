package com.itstep.fabiyanski.machines;

import com.itstep.fabiyanski.Coffee.Americano;
import com.itstep.fabiyanski.Coffee.Espresso;
import com.itstep.fabiyanski.exceptions.CoffeeMachineException;
import com.itstep.fabiyanski.exceptions.NoCoffeeException;
import com.itstep.fabiyanski.exceptions.NoTankCapacityException;
import com.itstep.fabiyanski.exceptions.NoWaterException;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BigCoffeeMachineTest {

// тесты для BigCoffeeMachine аналогичны тестам для SmallCoffeeMachine


    @Test
    public void makeEspressoPositiveTest() throws CoffeeMachineException {
        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(50);
        big.fillWithWater(200);
        big.enable();
        Espresso espresso = big.makeEspresso();
        Assert.assertNotNull(espresso);
    }

    @Test
    public void makeEspressoNoCoffeeTest() throws CoffeeMachineException {

        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(10);
        big.fillWithWater(200);
        big.enable();
        Espresso espresso;
        try {
            espresso = big.makeEspresso();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoCoffeeException) {
                big.fillWithCoffee(100);
            }
            espresso = big.makeEspresso();
        }
        Assert.assertNotNull(espresso);
    }

    @Test
    public void makeEspressoNoWaterTest() throws CoffeeMachineException {

        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(30);
        big.fillWithWater(20);
        big.enable();
        Espresso espresso;
        try {
            espresso = big.makeEspresso();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoWaterException) {
                big.fillWithWater(100);
            }
            espresso = big.makeEspresso();
        }
        Assert.assertNotNull(espresso);
    }

    @Test
    public void makeEspressoNoTankCapacityTest() throws CoffeeMachineException {

        BigCoffeeMachine big = new BigCoffeeMachine(200, 200, 1500);// создаём объект кофемашины с заполненным баком для отработанного кофе
        big.enable();
        Espresso espresso;
        try {
            espresso = big.makeEspresso();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoTankCapacityException) {
                big.cleanUp();
            }
            espresso = big.makeEspresso();
        }
        Assert.assertNotNull(espresso);
    }

    // тесты для метода makeAmericano аналогичны тестам, приведенным выше

    @Test
    public void makeAmericanoPositiveTest() throws CoffeeMachineException {
        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(50);
        big.fillWithWater(200);
        big.enable();
        Americano americano = big.makeAmericano();
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeAmericanoNoCoffeeTest() throws CoffeeMachineException {

        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(10);
        big.fillWithWater(200);
        big.enable();
        Americano americano;
        try {
            americano = big.makeAmericano();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoCoffeeException) {
                big.fillWithCoffee(100);
            }
            americano = big.makeAmericano();
        }
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeAmericanoNoWaterTest() throws CoffeeMachineException {

        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(30);
        big.fillWithWater(20);
        big.enable();
        Americano americano;
        try {
            americano = big.makeAmericano();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoWaterException) {
                big.fillWithWater(100);
            }
            americano = big.makeAmericano();
        }
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeAmericanoNoTankCapacityTest() throws CoffeeMachineException {

        BigCoffeeMachine big = new BigCoffeeMachine(200, 200, 1500);// создаём объект кофемашины с заполненным баком для отработанного кофе
        big.enable();
        Americano americano;
        try {
            americano = big.makeAmericano();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoTankCapacityException) {
                big.cleanUp();
            }
            americano = big.makeAmericano();
        }
        Assert.assertNotNull(americano);
    }

    @Test
    public void enableTest() {
        BigCoffeeMachine big = new BigCoffeeMachine();
        big.enable();
        assertTrue(big.isRunning() == true);
    }

    @Test
    public void disableTest() {
        BigCoffeeMachine big = new BigCoffeeMachine();
        big.enable();
        big.disable();
        assertTrue(big.isRunning() == false);
    }

    @Test
    public void cleanUpTest() throws CoffeeMachineException {
        BigCoffeeMachine big = new BigCoffeeMachine();
        big.fillWithCoffee(50);
        big.fillWithWater(200);
        big.enable();
        big.makeEspresso();
        big.cleanUp();
        assertTrue(big.getMudTank() == 0);
    }

}
