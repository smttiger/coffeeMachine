package com.itstep.fabiyanski.machines;

public class BigCoffeeMachine extends ACoffeeMachine {
    private static final int BIG_WATER_LIMIT = 3000;
    private static final int BIG_COFFEE_LIMIT = 2200;
    private static final int BIG_TANK_CAPACITY = 1500;

    BigCoffeeMachine() {
        super();
    }

    public BigCoffeeMachine(int coffee, int water, int tank) {
        super(coffee, water, tank);
    }

    int getWaterLimit() {
        return BIG_WATER_LIMIT;
    }

    int getTankLimit() {
        return BIG_TANK_CAPACITY;
    }

    int getCoffeeLimit() {
        return BIG_COFFEE_LIMIT;
    }
}
