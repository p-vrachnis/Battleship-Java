package com.company;

public class Tile {
    private int x;
    private int y;
    private enum Type{
        Sea,Ship,Hit,Miss
    }
    private String symbol;

    public Tile(){
        Type type = Type.Sea;
        symbol = "~";
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {this.y = y; }

    public void setX(int x) {this.x = x; }

    public void draw(){
        System.out.print(symbol + " ");
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

}
