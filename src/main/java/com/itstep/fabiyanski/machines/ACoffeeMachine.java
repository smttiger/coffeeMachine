package com.itstep.fabiyanski.machines;


import com.itstep.fabiyanski.Coffee.Americano;
import com.itstep.fabiyanski.Coffee.Espresso;
import com.itstep.fabiyanski.CoffeeMachine;
import com.itstep.fabiyanski.exceptions.CoffeeMachineException;
import com.itstep.fabiyanski.exceptions.NoCoffeeException;
import com.itstep.fabiyanski.exceptions.NoTankCapacityException;
import com.itstep.fabiyanski.exceptions.NoWaterException;

public abstract class ACoffeeMachine implements CoffeeMachine {
    private int currentCoffee;
    private int currentWater;
    private int mudTank;
    private boolean isRunning = false;
    public static final int AMOUNT_OF_COFFEE_Espresso = 22;
    public static final int AMOUNT_OF_WATER_Espresso = 30;
    public static final int COFFEE_TO_TANK_Espresso = 15;//кол-во кофе,сбрасываемое в бак (из 22 г часть всё-таки остаётся в чашке)
    public static final int AMOUNT_OF_COFFEE_Americano = 22;
    public static final int AMOUNT_OF_WATER_Americano = 100;
    public static final int COFFEE_TO_TANK_Americano = 15;//кол-во кофе,сбрасываемое в бак (из 22 г часть всё-таки остаётся в чашке)

    public ACoffeeMachine() {
        this.currentCoffee = 0;
        this.currentWater = 0;
        this.mudTank = 0;
    }

    public ACoffeeMachine(int coffee, int water, int tank) {
        if (coffee > this.getCoffeeLimit()) {
            this.currentCoffee = this.getCoffeeLimit();
        } else this.currentCoffee = coffee;
        if (water > this.getWaterLimit()) {
            this.currentWater = this.getWaterLimit();
        } else this.currentWater = water;
        if (tank > this.getTankLimit()) {
            this.mudTank = this.getTankLimit();
        } else this.mudTank = tank;
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
        return (mudTank + valueToAdd) <= getTankLimit();
    }

    void collectGarbage(int valueToAdd) throws CoffeeMachineException {
        if (availableTankCapacity(valueToAdd)) {
            mudTank = mudTank + valueToAdd;
        } else {
            System.out.println("Переполнен бак с отработанным кофе");
            throw new NoTankCapacityException();
        }

    }

    public int getCurrentCoffee() {
        return currentCoffee;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public int getMudTank() {return mudTank;}

    public void setCurrentCoffee(int currentCoffee) {
        this.currentCoffee = currentCoffee;
    }

    public void setCurrentWater(int currentWater) {
        this.currentWater = currentWater;
    }


    public void fillWithWater(int quantityInMl) {
        if (quantityInMl + currentWater > getWaterLimit()) {
            currentWater = getWaterLimit();
        } else {
            currentWater += quantityInMl;
        }
    }

    public void fillWithCoffee(int quantityInGrams) {
        if (quantityInGrams + currentWater > getCoffeeLimit()) {
            currentCoffee = getCoffeeLimit();
        } else {
            currentCoffee += quantityInGrams;
        }
    }


    abstract int getWaterLimit();

    abstract int getTankLimit();

    abstract int getCoffeeLimit();

    public Espresso makeEspresso() throws CoffeeMachineException {
        if (!isRunning()) {
            return null;
        }
        if (getCurrentCoffee() < AMOUNT_OF_COFFEE_Espresso) {
            System.out.println("Отсутствует молотый кофе");
            throw new NoCoffeeException();
        }
        if (getCurrentWater() < AMOUNT_OF_WATER_Espresso) {
            System.out.println("Отсутствует вода");
            throw new NoWaterException();
        }
        collectGarbage(COFFEE_TO_TANK_Espresso);
        setCurrentCoffee(getCurrentCoffee() - AMOUNT_OF_COFFEE_Espresso);
        setCurrentWater(getCurrentWater() - AMOUNT_OF_WATER_Espresso);
        return new Espresso();
    }

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
}
