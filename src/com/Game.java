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
            playersPosition[i] = new Cell(1);
        }
    }

    public int play(int playerId, Dice dice) {
        System.out.println(dice);
        if(playersPosition[playerId].value + dice.getFaceValue() >= board.size()) return playerId;
        playersPosition[playerId] = board.movePlayer(players[playerId], playersPosition[playerId], dice.getFaceValue());
        System.out.println(this);
        if(playersPosition[playerId].value >= board.size()) return playerId;
        return -1;
    }

    private String printPlayersPositions() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            sb.append(players[i].name + " at " + playersPosition[i].value + ", ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        String playersPositions = printPlayersPositions();
        return "Board:\n" + board + "\n" + playersPositions;
    }
}
