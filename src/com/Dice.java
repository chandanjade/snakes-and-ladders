package com;

public class Dice {
    private int faceValue;

    private Dice(int value) {
        faceValue = value;
    }

    public static Dice roll() {
        int value = (int) (Math.random() * 100) % 6 + 1;
        return new Dice(value);
    }

    public int getFaceValue() {
        return faceValue;
    }

    @Override
    public String toString() {
        return "Dice " + faceValue;
    }
}
