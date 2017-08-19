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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return value == cell.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
