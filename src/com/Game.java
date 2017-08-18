package com;


public class Game {
    private Board board;
    private Player[] players;
    private Cell[] playersPosition;

    public Game(Player[] players) {
        this.players = players;
        initPlayersPositions();
        this.board = new Board(10, 10);
    }

    public Game(Player[] players, int rows, int cols) {
        this.players = players;
        initPlayersPositions();
        this.board = new Board(rows, cols);

    }

    private void initPlayersPositions() {
        this.playersPosition = new Cell[players.length];
        for (int i = 0; i < players.length; i++) {
            playersPosition[i] = new Cell(0, 0);
        }
    }

    private String printPlayersPositions() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            sb.append(players[i].name + " at " + board.cellValue(playersPosition[i]) + "\n");
        }
        return sb.toString();
    }

    public int play(int playerId, Dice dice) {
        System.out.println(dice);
        playersPosition[playerId] = board.nextCell(playersPosition[playerId], dice.getFaceValue());
        System.out.println(printPlayersPositions());
        return -1;
    }

    @Override
    public String toString() {
        String playersPositions = printPlayersPositions();
        return "Board:\n" + board + "\nPlayers:\n" + playersPositions;
    }
}
