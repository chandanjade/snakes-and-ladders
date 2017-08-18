package com;

public class Cell {
    public final int row;
    public final int col;
    public Cell next;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void pointsTo(Cell cell) {
        next = cell;
    }
}
