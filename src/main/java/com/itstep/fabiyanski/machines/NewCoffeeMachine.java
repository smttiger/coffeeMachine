package com.itstep.fabiyanski.machines;

import com.itstep.fabiyanski.ANewCoffeeMachine;
import com.itstep.fabiyanski.Coffee.Capuchino;
import com.itstep.fabiyanski.Coffee.Late;
import com.itstep.fabiyanski.exceptions.CoffeeMachineException;
import com.itstep.fabiyanski.exceptions.NoCoffeeException;
import com.itstep.fabiyanski.exceptions.NoMilkException;
import com.itstep.fabiyanski.exceptions.NoWaterException;

//задание 2
public class NewCoffeeMachine  extends ACoffeeMachine implements ANewCoffeeMachine {
    public static final int NEW_WATER_LIMIT = 1500;
    public static final int NEW_COFFEE_LIMIT = 1100;
    public static final int NEW_TANK_CAPACITY = 750;
    public static final int NEW_MILK_LIMIT = 750;
    public static final int AMOUNT_OF_COFFEE_LATE = 22;
    public static final int AMOUNT_OF_WATER_LATE = 30;
    public static final int COFFEE_TO_TANK_LATE = 15;//кол-во кофе,сбрасываемое в бак (из 22 г часть всё-таки остаётся в чашке)
    public static final int AMOUNT_OF_COFFEE_CAPUCHINO = 22;
    public static final int AMOUNT_OF_WATER_CAPUCHINO = 30;
    public static final int COFFEE_TO_TANK_CAPUCHINO = 15;//кол-во кофе,сбрасываемое в бак (из 22 г часть всё-таки остаётся в чашке)
    public static final int LOW_MILK = 10;
    public static final int MEDIUM_MILK = 20;
    public static final int LARGE_MILK = 30;
    private int currentMilk;
    private boolean isLowMilkButtonOn=false;
    private boolean isMediumMilkButtonOn=false;
    private boolean isLargeMilkButtonOn=false;
    public NewCoffeeMachine() {
        super();
        this.currentMilk = 0;
    }


    public NewCoffeeMachine(int coffee, int water, int tank, int milk) {
        super(coffee, water, tank);
        if (milk > this.getMilkLimit()) this.currentMilk = this.getMilkLimit();
        else this.currentMilk = milk;
    }

    public int getWaterLimit() {
        return NEW_WATER_LIMIT;
    }

    public int getTankLimit() {
        return NEW_TANK_CAPACITY;
    }

    public int getCoffeeLimit() {
        return NEW_COFFEE_LIMIT;
    }

    public int getMilkLimit() {
        return NEW_MILK_LIMIT;
    }
    public int getCurrentMilk() {
        return currentMilk;
    }

    public void setCurrentMilk(int currentMilk) {
        this.currentMilk = currentMilk;
    }

    public void fillWithMilk(int quantityInMl) {
        if (quantityInMl + currentMilk > getMilkLimit()) {
            currentMilk = getMilkLimit();
        } else {
            currentMilk += quantityInMl;
        }
    }
    public void setLowMilkButtonOn(){isLowMilkButtonOn=true;} // кнопка низкого уровня молока
    public void setMediumMilkButtonOn(){isMediumMilkButtonOn=true;}// кнопка среднего уровня молока
    public void setLargeMilkButtonOn(){isLargeMilkButtonOn=true;}// кнопка большого уровня молока
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
        if (isLowMilkButtonOn==true){
            if (getCurrentMilk()<LOW_MILK){
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk()-LOW_MILK);
            isLowMilkButtonOn=false;
        }
        else if (isMediumMilkButtonOn==true){
            if (getCurrentMilk()<MEDIUM_MILK){
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk()-MEDIUM_MILK);
            isMediumMilkButtonOn=false;
        }
        else if (isLargeMilkButtonOn==true){
            if (getCurrentMilk()<LARGE_MILK){
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk()-LARGE_MILK);
            isLargeMilkButtonOn=false;
        }
        collectGarbage(COFFEE_TO_TANK_LATE);
        setCurrentCoffee(getCurrentCoffee() - AMOUNT_OF_COFFEE_LATE);
        setCurrentWater(getCurrentWater() - AMOUNT_OF_WATER_LATE);
        return new Late();
    }
    public Capuchino makeCapuchino() throws CoffeeMachineException {
        if (!isRunning()) {
            return null;
        }
        if (getCurrentCoffee() < AMOUNT_OF_COFFEE_CAPUCHINO) {
            System.out.println("Отсутствует молотый кофе");
            throw new NoCoffeeException();
        }
        if (getCurrentWater() < AMOUNT_OF_WATER_CAPUCHINO) {
            System.out.println("Отсутствует вода");
            throw new NoWaterException();
        }
        if (isLowMilkButtonOn==true){
            if (getCurrentMilk()<LOW_MILK){
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk()-LOW_MILK);
            isLowMilkButtonOn=false;
        }
        else if (isMediumMilkButtonOn==true){
            if (getCurrentMilk()<MEDIUM_MILK){
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk()-MEDIUM_MILK);
            isMediumMilkButtonOn=false;
        }
        else if (isLargeMilkButtonOn==true){
            if (getCurrentMilk()<LARGE_MILK){
                System.out.println("Отсутствует молоко");
                throw new NoMilkException();
            }
            setCurrentMilk(getCurrentMilk()-LARGE_MILK);
            isLargeMilkButtonOn=false;
        }
        collectGarbage(COFFEE_TO_TANK_CAPUCHINO);
        setCurrentCoffee(getCurrentCoffee() - AMOUNT_OF_COFFEE_LATE);
        setCurrentWater(getCurrentWater() - AMOUNT_OF_WATER_LATE);
        return new Capuchino();
    }







}
