package com;

public class Board {
    private final int rows;
    private final int cols;
    private Cell[][] cells;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        addSomeLadders();
        addSomeSnakes();
    }

    private void addSomeLadders() {
        for (int row = 0; row < rows - 1; row++) {
            int col = (int) (Math.random() * 1000) % (cols - 1);
            int vlen = (int) (Math.random() * 1000) % (rows - row);
            int hlen = (int) (Math.random() * 1000) % (cols - col);
            cells[row][col].pointsTo(cells[row + vlen][col + hlen]);
        }
    }

    private void addSomeSnakes() {
        for (int row = rows - 1; row > 0; row--) {
            int col = (int) (Math.random() * 1000) % (cols - 1);
            if (col == 0) col++;
            int vlen = (int) (Math.random() * 1000) % row;
            int hlen = (int) (Math.random() * 1000) % col;
            cells[row][col].pointsTo(cells[row - vlen][col - hlen]);
        }
    }

    public int cellValue(Cell cell) {
        return cell.row * cols + cell.col + 1;
    }

    public Cell nextCell(Cell current, int score) {
        int row = current.row + (current.col + score) / rows;
        int col = (current.col + score) % cols;
        Cell next = cells[row][col];
        while (next.next != null) {
            next = next.next;
        }
        return next;
    }

    private String printCell(Cell cell) {
        String link = "     ";
        if (cell.next != null) {
            String type = cellValue(cell.next) > cellValue(cell) ? " #" : "\uD83D\uDC0D ";
            link = String.format(type + "%3d", cellValue(cell.next));
        }
        return String.format("|" + link + "%3d|", cellValue(cell));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append("+--------+");
            }
            sb.append("\n");
            for (int j = 0; j < cols; j++) {
                sb.append(printCell(cells[i][j]));
            }
            sb.append("\n");
        }
        for (int j = 0; j < cols; j++) {
            sb.append("+--------+");
        }
        return sb.toString();
    }
}
