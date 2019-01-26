package com.itstep.fabiyanski.machines;

import com.itstep.fabiyanski.Coffee.Capuchino;
import com.itstep.fabiyanski.Coffee.Late;
import com.itstep.fabiyanski.exceptions.*;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class NewCoffeeMachineTest {
    @Test
    public void makeCapuchinoPositiveTest() throws CoffeeMachineException {
        NewCoffeeMachine newCoffeeMachine = new NewCoffeeMachine();
        newCoffeeMachine.fillWithCoffee(50);
        newCoffeeMachine.fillWithWater(200);
        newCoffeeMachine.fillWithMilk(100);
        newCoffeeMachine.enable();
        newCoffeeMachine.setMediumMilkButtonOn();
        Capuchino capuchino = newCoffeeMachine.makeCapuchino();
        Assert.assertNotNull(capuchino);
    }

    @Test
    public void makeLateNoCoffeeTest() throws CoffeeMachineException {

        NewCoffeeMachine newCoffeeMachine = new NewCoffeeMachine();
        newCoffeeMachine.fillWithCoffee(10);
        newCoffeeMachine.fillWithWater(200);
        newCoffeeMachine.fillWithMilk(300);
        newCoffeeMachine.enable();
        Late late;
        try {
            newCoffeeMachine.setLowMilkButtonOn();
            late = newCoffeeMachine.makeLate();

        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoCoffeeException) {
                newCoffeeMachine.fillWithCoffee(100);
            }
            newCoffeeMachine.setLowMilkButtonOn();
            late = newCoffeeMachine.makeLate();

        }
        Assert.assertNotNull(late);
    }

    @Test
    public void makeLateNoMilkTest() throws CoffeeMachineException {

        NewCoffeeMachine newCoffeeMachine = new NewCoffeeMachine();
        newCoffeeMachine.fillWithCoffee(100);
        newCoffeeMachine.fillWithWater(200);
        newCoffeeMachine.fillWithMilk(20);
        newCoffeeMachine.enable();
        Late late;
        try {
            newCoffeeMachine.setLargeMilkButtonOn();
            late = newCoffeeMachine.makeLate();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoMilkException) {
                newCoffeeMachine.fillWithMilk(100);
            }
            newCoffeeMachine.setLargeMilkButtonOn();
            late = newCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    @Test
    public void makeLateNoWaterTest() throws CoffeeMachineException {

        NewCoffeeMachine newCoffeeMachine = new NewCoffeeMachine();
        newCoffeeMachine.fillWithCoffee(30);
        newCoffeeMachine.fillWithWater(20);
        newCoffeeMachine.fillWithMilk(20);
        newCoffeeMachine.enable();
        Late late;
        try {
            newCoffeeMachine.setMediumMilkButtonOn();
            late = newCoffeeMachine.makeLate();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoWaterException) {
                newCoffeeMachine.fillWithWater(100);
            }
            newCoffeeMachine.setMediumMilkButtonOn();
            late = newCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    @Test
    public void makeLateNoTankCapacityTest() throws CoffeeMachineException {

        NewCoffeeMachine newCoffeeMachine= new NewCoffeeMachine(200,200,750,100);// создаём объект кофемашины с заполненным баком для отработанного кофе
        newCoffeeMachine.enable();
        Late late;
        try {
            newCoffeeMachine.setMediumMilkButtonOn();
            late = newCoffeeMachine.makeLate();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoTankCapacityException) {
                newCoffeeMachine.cleanUp();
            }
            newCoffeeMachine.setMediumMilkButtonOn();
            late = newCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    // тесты для метода makeCapuchino аналогичны тестам, приведенным выше, поэтому опущу их


    @Test
    public void enableTest(){
        NewCoffeeMachine newCoffeeMachine=new NewCoffeeMachine();
        newCoffeeMachine.enable();
        assertTrue (newCoffeeMachine.isRunning()==true);
    }

    @Test
    public void disableTest(){
        NewCoffeeMachine newCoffeeMachine=new NewCoffeeMachine();
        newCoffeeMachine.enable();
        newCoffeeMachine.disable();
        assertTrue (newCoffeeMachine.isRunning()==false);
    }

    @Test
    public void cleanUpTest() throws CoffeeMachineException{
        NewCoffeeMachine newCoffeeMachine=new NewCoffeeMachine();
        newCoffeeMachine.fillWithCoffee(50);
        newCoffeeMachine.fillWithWater(200);
        newCoffeeMachine.fillWithMilk(100);
        newCoffeeMachine.enable();
        newCoffeeMachine.setMediumMilkButtonOn();
        newCoffeeMachine.makeEspresso();
        newCoffeeMachine.cleanUp();
        assertTrue (newCoffeeMachine.getMudTank()==0);
    }
}
