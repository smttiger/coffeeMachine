package com.itstep.fabiyanski.machines;

import com.itstep.fabiyanski.Coffee.Americano;

import com.itstep.fabiyanski.Coffee.Late;
import com.itstep.fabiyanski.TheNewestCoffeeMachine;
import com.itstep.fabiyanski.exceptions.*;
import static com.itstep.fabiyanski.machines.ACoffeeMachine.AMOUNT_OF_COFFEE_Americano;
import static com.itstep.fabiyanski.machines.ACoffeeMachine.AMOUNT_OF_WATER_Americano;
import static com.itstep.fabiyanski.machines.ACoffeeMachine.COFFEE_TO_TANK_Americano;

// задание 3
public class NewestCoffeeMachine implements TheNewestCoffeeMachine {
    public static final int NEWEST_WATER_LIMIT = 1500;
    public static final int NEWEST_COFFEE_LIMIT = 1100;
    public static final int NEWEST_TANK_CAPACITY = 750;
    public static final int NEWEST_COFFEE_BEANS_LIMIT = 1100;
    public static final int NEWEST_MILK_LIMIT = 750;
    public static final int AMOUNT_OF_COFFEE_LATE = 22;
    public static final int AMOUNT_OF_WATER_LATE = 30;
    public static final int COFFEE_TO_TANK_LATE = 15;//кол-во кофе,сбрасываемое в бак (из 22 г часть всё-таки остаётся в чашке)
    public static final int LOW_MILK = 10;
    public static final int MEDIUM_MILK = 20;
    public static final int LARGE_MILK = 30;
    private int currentMilk;
    private int currentCoffeeBeans;
    private int currentCoffee;
    private int currentWater;
    private int mudTank;
    private boolean isRunning = false;
    private boolean isLowMilkButtonOn = false;
    private boolean isMediumMilkButtonOn = false;
    private boolean isLargeMilkButtonOn = false;

    public NewestCoffeeMachine() {
        this.currentMilk = 0;
        this.currentCoffeeBeans = 0;
        this.currentWater = 0;
        this.currentCoffee = 0;
        this.mudTank = 0;
    }


    public NewestCoffeeMachine(int coffee, int water, int tank, int milk, int coffeeBeans) {
        if (milk > NEWEST_MILK_LIMIT) this.currentMilk = NEWEST_MILK_LIMIT;
        else this.currentMilk = milk;
        if (coffee > NEWEST_COFFEE_LIMIT) this.currentCoffee = NEWEST_COFFEE_LIMIT;
        else this.currentCoffee = coffee;
        if (water > NEWEST_WATER_LIMIT) this.currentWater = NEWEST_WATER_LIMIT;
        else this.currentWater = water;
        if (tank > NEWEST_TANK_CAPACITY) this.mudTank = NEWEST_TANK_CAPACITY;
        else this.mudTank = tank;
        if (coffeeBeans > NEWEST_COFFEE_BEANS_LIMIT) this.currentCoffeeBeans = NEWEST_COFFEE_BEANS_LIMIT;
        else this.currentCoffeeBeans = coffeeBeans;
    }


    public void setCurrentMilk(int currentMilk) {
        this.currentMilk = currentMilk;
    }

    public int getCurrentCoffee() {
        return currentCoffee;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public int getCurrentMilk() {
        return currentMilk;
    }

    public int getMudTank() {
        return mudTank;
    }

    public void setCurrentCoffee(int currentCoffee) {
        this.currentCoffee = currentCoffee;
    }

    public void setCurrentWater(int currentWater) {
        this.currentWater = currentWater;
    }


    public void fillWithCoffeeBeans(int quantityInGr) {
        if (quantityInGr + currentCoffeeBeans > NEWEST_COFFEE_BEANS_LIMIT) {
            currentCoffeeBeans = NEWEST_COFFEE_BEANS_LIMIT;
        } else
            currentCoffeeBeans += quantityInGr;
    }

    public void grindCoffee() {
        if (currentCoffeeBeans >= (NEWEST_COFFEE_LIMIT - currentCoffee)) {
            currentCoffeeBeans = currentCoffeeBeans - (NEWEST_COFFEE_LIMIT - currentCoffee);
            currentCoffee = NEWEST_COFFEE_LIMIT;
        } else {
            currentCoffee = currentCoffee + currentCoffeeBeans;
            currentCoffeeBeans = 0;
        }
    }

    public void enable() {

        if (isRunning == false)
            isRunning = true;
    }

    public void disable() {
        isRunning = false;
    }

    public void cleanUp() {
        mudTank = 0;
    }


    public boolean isRunning() {
        return isRunning;
    }

    boolean availableTankCapacity(int valueToAdd) {
        return (mudTank + valueToAdd) <= NEWEST_TANK_CAPACITY;
    }

    void collectGarbage(int valueToAdd) throws CoffeeMachineException {
        if (availableTankCapacity(valueToAdd)) {
            mudTank = mudTank + valueToAdd;
        } else {
            System.out.println("Переполнен бак с отработанным кофе");
            throw new NoTankCapacityException();
        }

    }


    public void fillWithWater(int quantityInMl) {
        if (quantityInMl + currentWater > NEWEST_WATER_LIMIT) {
            currentWater = NEWEST_WATER_LIMIT;
        } else {
            currentWater += quantityInMl;
        }
    }

    public void fillWithCoffee(int quantityInGrams) {
        if (quantityInGrams + currentWater > NEWEST_COFFEE_LIMIT) {
            currentCoffee = NEWEST_COFFEE_LIMIT;
        } else {
            currentCoffee += quantityInGrams;
        }
    }

    public void fillWithMilk(int quantityInMl) {
        if (quantityInMl + currentMilk > NEWEST_MILK_LIMIT) {
            currentMilk = NEWEST_MILK_LIMIT;
        } else {
            currentMilk += quantityInMl;
        }
    }

    public void setLowMilkButtonOn() {
        isLowMilkButtonOn = true;
    } // кнопка низкого уровня молока

    public void setMediumMilkButtonOn() {
        isMediumMilkButtonOn = true;
    }// кнопка среднего уровня молока

    public void setLargeMilkButtonOn() {
        isLargeMilkButtonOn = true;
    }// кнопка большого уровня молока

    public Americano makeAmericano() throws CoffeeMachineException {
        if (!isRunning()) {
            return null;
        }
        if (getCurrentCoffee() < AMOUNT_OF_COFFEE_Americano) {
            System.out.println("Отсутствует молотый кофе");
            throw new NoCoffeeException();
        }
        if (getCurrentWater() < AMOUNT_OF_WATER_Americano) {
            System.out.println("Отсутствует вода");
            throw new NoWaterException();
        }
        collectGarbage(COFFEE_TO_TANK_Americano);
        setCurrentCoffee(getCurrentCoffee() - AMOUNT_OF_COFFEE_Americano);
        setCurrentWater(getCurrentWater() - AMOUNT_OF_WATER_Americano);
        return new Americano();
    }

    public Late makeLate() throws CoffeeMachineException {
        if (!isRunning()) {
            return null;
        }
        if (getCurrentCoffee() < AMOUNT_OF_COFFEE_LATE) {
            System.out.println("Отсутствует молотый кофе");
            throw new NoCoffeeException();
        }
        if (getCurrentWater() < AMOUNT_OF_WATER_LATE) {
            System.out.println("Отсутствует вода");
            throw new NoWaterException();
        }
        if (isLowMilkButtonOn == true) {
            if (getCurrentMilk() < LOW_MILK) {
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk() - LOW_MILK);
            isLowMilkButtonOn = false;
        } else if (isMediumMilkButtonOn == true) {
            if (getCurrentMilk() < MEDIUM_MILK) {
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk() - MEDIUM_MILK);
            isMediumMilkButtonOn = false;
        } else if (isLargeMilkButtonOn == true) {
            if (getCurrentMilk() < LARGE_MILK) {
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk() - LARGE_MILK);
            isLargeMilkButtonOn = false;
        }
        collectGarbage(COFFEE_TO_TANK_LATE);
        setCurrentCoffee(getCurrentCoffee() - AMOUNT_OF_COFFEE_LATE);
        setCurrentWater(getCurrentWater() - AMOUNT_OF_WATER_LATE);
        return new Late();
    }
}