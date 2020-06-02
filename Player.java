package com.company;

public class Player {
    private String name;
    private int attemps;
    private int misses;
    private int hits;
    private int sameTile;
    public Player(String name){
        this.name = name;
        this.hits = 0;
        this.attemps = 0;
        this.misses = 0;
        this.sameTile = 0;
    }
    public void fire(Board board, int attackX, int attackY){
        this.attemps++;
        if (board.board[attackX][attackY].getSymbol().equals("s")){
            board.board[attackX][attackY].setSymbol("X");
            this.hits++;
        } else if (board.board[attackX][attackY].getSymbol().equals("X")){
            this.sameTile++;
        } else if(board.board[attackX][attackY].getSymbol().equals("~")){
            board.board[attackX][attackY].setSymbol("o");
            this.misses++;
        }
    }
    public int[] getStats(){
        return new int[]{this.attemps, this.hits, this.misses, this.sameTile};
    }
}
