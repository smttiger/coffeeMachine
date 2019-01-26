package com.itstep.fabiyanski.machines;

import com.itstep.fabiyanski.Coffee.Americano;
import com.itstep.fabiyanski.Coffee.Late;
import com.itstep.fabiyanski.exceptions.*;
import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class NewestCoffeeMachineTest {

    @Test
    public void grindCoffeeTest() {
        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.fillWithCoffeeBeans(200);
        newestCoffeeMachine.enable();
        newestCoffeeMachine.grindCoffee();
        assertTrue(newestCoffeeMachine.getCurrentCoffee() == 200);
    }

    @Test
    public void makeAmericanoPositiveTest() throws CoffeeMachineException {
        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.fillWithCoffee(50);
        newestCoffeeMachine.fillWithWater(200);
        newestCoffeeMachine.enable();
        Americano americano = newestCoffeeMachine.makeAmericano();
        Assert.assertNotNull(americano);
    }

    @Test
    public void makeLateNoCoffeeTest() throws CoffeeMachineException {

        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.fillWithCoffee(10);
        newestCoffeeMachine.fillWithWater(200);
        newestCoffeeMachine.fillWithMilk(300);
        newestCoffeeMachine.fillWithCoffeeBeans(300);
        newestCoffeeMachine.enable();
        Late late;
        try {
            newestCoffeeMachine.setLowMilkButtonOn();
            late = newestCoffeeMachine.makeLate();

        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoCoffeeException) {
                newestCoffeeMachine.fillWithCoffee(100);
            }
            newestCoffeeMachine.setLowMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    @Test
    public void makeLateNoMilkTest() throws CoffeeMachineException {

        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.fillWithCoffee(100);
        newestCoffeeMachine.fillWithWater(200);
        newestCoffeeMachine.fillWithMilk(20);
        newestCoffeeMachine.fillWithCoffeeBeans(100);
        newestCoffeeMachine.enable();
        Late late;
        try {
            newestCoffeeMachine.setLargeMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoMilkException) {
                newestCoffeeMachine.fillWithMilk(100);
            }
            newestCoffeeMachine.setLargeMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    @Test
    public void makeLateNoWaterTest() throws CoffeeMachineException {

        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.fillWithCoffee(30);
        newestCoffeeMachine.fillWithWater(20);
        newestCoffeeMachine.fillWithMilk(20);
        newestCoffeeMachine.fillWithCoffeeBeans(100);
        newestCoffeeMachine.enable();
        Late late;
        try {
            newestCoffeeMachine.setMediumMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoWaterException) {
                newestCoffeeMachine.fillWithWater(100);
            }
            newestCoffeeMachine.setMediumMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    @Test
    public void makeLateNoTankCapacityTest() throws CoffeeMachineException {

        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine(200, 200, 750, 100, 100);// создаём объект кофемашины с заполненным баком для отработанного кофе
        newestCoffeeMachine.enable();
        Late late;
        try {
            newestCoffeeMachine.setMediumMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        } catch (CoffeeMachineException exceptionVariableName) {
            if (exceptionVariableName instanceof NoTankCapacityException) {
                newestCoffeeMachine.cleanUp();
            }
            newestCoffeeMachine.setMediumMilkButtonOn();
            late = newestCoffeeMachine.makeLate();
        }
        Assert.assertNotNull(late);
    }

    // тесты для метода makeAmericano аналогичны тестам, приведенным выше, поэтому опущу их


    @Test
    public void enableTest() {
        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.enable();
        assertTrue(newestCoffeeMachine.isRunning() == true);
    }

    @Test
    public void disableTest() {
        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.enable();
        newestCoffeeMachine.disable();
        assertTrue(newestCoffeeMachine.isRunning() == false);
    }

    @Test
    public void cleanUpTest() throws CoffeeMachineException {
        NewestCoffeeMachine newestCoffeeMachine = new NewestCoffeeMachine();
        newestCoffeeMachine.fillWithCoffee(50);
        newestCoffeeMachine.fillWithWater(200);
        newestCoffeeMachine.fillWithMilk(100);
        newestCoffeeMachine.fillWithCoffeeBeans(100);
        newestCoffeeMachine.enable();
        newestCoffeeMachine.setMediumMilkButtonOn();
        newestCoffeeMachine.makeAmericano();
        newestCoffeeMachine.cleanUp();
        assertTrue(newestCoffeeMachine.getMudTank() == 0);
    }
}

