package com;

public class Cell {
    public final int value;
    public Cell next;

    public Cell(int value) {
        this.value = value;
    }

    public void pointsTo(Cell cell) {
        next = cell;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
