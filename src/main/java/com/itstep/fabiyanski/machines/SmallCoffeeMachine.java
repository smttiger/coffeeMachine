package com.itstep.fabiyanski.machines;

public class SmallCoffeeMachine extends ACoffeeMachine {
    public static final int SMALL_WATER_LIMIT = 1500;
    public static final int SMALL_COFFEE_LIMIT = 1100;
    public static final int SMALL_TANK_CAPACITY = 750;


    public SmallCoffeeMachine() {super();}


    public SmallCoffeeMachine(int coffee, int water, int tank) {
        super(coffee, water, tank);
    }

    public int getWaterLimit() {
        return SMALL_WATER_LIMIT;
    }

    public int getTankLimit() {return SMALL_TANK_CAPACITY;}

    public int getCoffeeLimit() {
        return SMALL_COFFEE_LIMIT;
    }
}
