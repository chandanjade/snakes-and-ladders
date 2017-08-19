package com;

import java.util.Random;

public class Board {
    private final int rows;
    private final int cols;
    private Cell[] cells;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[size()];
        for (int i = 0; i < size(); i++) {
            cells[i] = new Cell(i+1);
        }
        addSomeLadders();
        addSomeSnakes();
    }

    public int size() {
        return rows * cols;
    }

    private void addSomeLadders() {
        Random random = new Random();
        for(int i = 1; i < size() - 1; i++) {
            int randomInt = random.nextInt(Integer.MAX_VALUE) % (size() - i - 1) + 1;
            if(randomInt % 2 == 0) {
                cells[i].pointsTo(cells[i + randomInt]);
            }
        }
    }

    private void addSomeSnakes() {
        Random random = new Random();
        for(int i = size() - 2; i > 1; i--) {
            int randomInt = random.nextInt(Integer.MAX_VALUE) % (i-1) + 1;
            if(randomInt % 2 == 0) {
                if(doesNotCreateCycle(cells[i], cells[i-randomInt])) {
                    cells[i].pointsTo(cells[i- randomInt]);
                }
            }
        }
    }

    private boolean doesNotCreateCycle(Cell src, Cell next) {
        Cell t = next;
        while (t != null) {
            if (t.equals(src)) {
                return false;
            }
            t = t.next;
        }
        return true;
    }

    public Cell movePlayer(Player p, Cell current, int score) {
        int nextValue = current.value + score;
        if(nextValue >= size()) throw new IllegalStateException("No cell available for " + nextValue + ", Board size is " + size());
        Cell next = cells[nextValue-1];
        System.out.println(p + " moves from " + current + " to " + next);
        while (next.next != null) {
            String snakeOrLadder = next.next.value > next.value ? "Yo! there is a ladder." : "Oops, you hit the snake!";
            System.out.println(snakeOrLadder + " " + p + " moves from " + next + " to " + next.next);
            next = next.next;
        }
        return next;
    }

    private String printCell(Cell cell) {
        String link = "     ";
        if (cell.next != null) {
            String type = cell.next.value > cell.value ? " #" : "\uD83D\uDC0D ";
            link = String.format(type + "%3d", cell.next.value);
        }
        return String.format("|" + link + "%3d ", cell.value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append("+---------");
            }
            sb.append("+");
            sb.append("\n");
            for (int j = 0; j < cols; j++) {
                sb.append(printCell(cells[i * cols + j]));
            }
            sb.append("|");
            sb.append("\n");
        }
        for (int j = 0; j < cols; j++) {
            sb.append("+---------");
        }
        sb.append("+");
        return sb.toString();
    }
}
