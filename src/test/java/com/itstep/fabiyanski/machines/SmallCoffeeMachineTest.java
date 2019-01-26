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


public class SmallCoffeeMachineTest {

    @Test
    public void makeEspressoPositiveTest() throws CoffeeMachineException {
        SmallCoffeeMachine small = new SmallCoffeeMachine();
        small.fillWithCoffee(50);
        small.fillWithWater(200);
        small.enable();
        Espresso espresso = small.makeEspresso();
        Assert.assertNotNull(espresso);
    }

    @Test
    public void makeEspressoNoCoffeeTest() throws CoffeeMachineException {

        SmallCoffeeMachine small= new SmallCoffeeMachine();
        small.fillWithCoffee(10);
        small.fillWithWater(200);
        small.enable();
        Espresso espresso;
        try {
            espresso = small.makeEspresso();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoCoffeeException) {
                small.fillWithCoffee(100);
            }
            espresso = small.makeEspresso();
        }
        Assert.assertNotNull(espresso);
    }

    @Test
    public void makeEspressoNoWaterTest() throws CoffeeMachineException {

        SmallCoffeeMachine small= new SmallCoffeeMachine();
        small.fillWithCoffee(30);
        small.fillWithWater(20);
        small.enable();
        Espresso espresso;
        try {
            espresso = small.makeEspresso();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoWaterException) {
                small.fillWithWater(100);
            }
            espresso = small.makeEspresso();
        }
        Assert.assertNotNull(espresso);
    }

    @Test
    public void makeEspressoNoTankCapacityTest() throws CoffeeMachineException {

        SmallCoffeeMachine small= new SmallCoffeeMachine(200,200,750);// создаём объект кофемашины с заполненным баком для отработанного кофе
        small.enable();
        Espresso espresso;
        try {
            espresso = small.makeEspresso();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoTankCapacityException) {
                small.cleanUp();
            }
            espresso = small.makeEspresso();
        }
        Assert.assertNotNull(espresso);
    }

    // тесты для метода makeAmericano аналогичны тестам, приведенным выше

    @Test
    public void makeAmericanoPositiveTest() throws CoffeeMachineException {
        SmallCoffeeMachine small = new SmallCoffeeMachine();
        small.fillWithCoffee(50);
        small.fillWithWater(200);
        small.enable();
        Americano americano = small.makeAmericano();
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeAmericanoNoCoffeeTest() throws CoffeeMachineException {

        SmallCoffeeMachine small= new SmallCoffeeMachine();
        small.fillWithCoffee(10);
        small.fillWithWater(200);
        small.enable();
        Americano americano;
        try {
            americano = small.makeAmericano();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoCoffeeException) {
                small.fillWithCoffee(100);
            }
            americano = small.makeAmericano();
        }
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeAmericanoNoWaterTest() throws CoffeeMachineException {

        SmallCoffeeMachine small= new SmallCoffeeMachine();
        small.fillWithCoffee(30);
        small.fillWithWater(20);
        small.enable();
        Americano americano;
        try {
            americano = small.makeAmericano();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoWaterException) {
                small.fillWithWater(100);
            }
            americano = small.makeAmericano();
        }
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeAmericanoNoTankCapacityTest() throws CoffeeMachineException {

        SmallCoffeeMachine small= new SmallCoffeeMachine(200,200,750);// создаём объект кофемашины с заполненным баком для отработанного кофе
        small.enable();
        Americano americano;
        try {
            americano = small.makeAmericano();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoTankCapacityException) {
                small.cleanUp();
            }
            americano = small.makeAmericano();
        }
        Assert.assertNotNull(americano);
    }

    @Test
    public void enableTest(){
        SmallCoffeeMachine small=new SmallCoffeeMachine();
        small.enable();
        assertTrue (small.isRunning()==true);
    }

    @Test
    public void disableTest(){
        SmallCoffeeMachine small=new SmallCoffeeMachine();
        small.enable();
        small.disable();
        assertTrue (small.isRunning()==false);
    }

    @Test
    public void cleanUpTest() throws CoffeeMachineException{
        SmallCoffeeMachine small=new SmallCoffeeMachine();
        small.fillWithCoffee(50);
        small.fillWithWater(200);
        small.enable();
        small.makeEspresso();
        small.cleanUp();
        assertTrue (small.getMudTank()==0);
    }

}