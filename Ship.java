package com.company;

import java.util.ArrayList;

public abstract class Ship {
    protected int numOfTries = 0;
    protected int startTileX;
    protected int startTileY;
    protected enum Orientation{
        horizontal,vertical
    }
    protected int shipSize;
    public boolean placeShip(Tile tile,Orientation or,Tile[][] board, String mode, Board hBoard){
        //τοποθετεί τα πλοία στο ταμπό με την βοήθεια του χρήστη
        try {
            if (mode.equals("ver")) {
                hBoard.drawBoard("vis");
            }
            if (numOfTries == 400){
                System.out.println("Λογο του μεγέθους του πίνακα μπορεί αν μην μπορεί το πρόγραμμα να βάλει πάντα τα πλοία, δοκιμάστε ξανα δοκίμασε : " + numOfTries + " φορές");
                System.exit(0);
            }
            numOfTries++;
            boolean res;
            res = checkBounds(tile, or);
            if (res == false) {return res;}
            res = checkExist(tile, or, board);
            if (res == false) {return res;}
            res = checkAdjacent(tile, or, board, hBoard);
            if (res == false) {return res;}
            res = checkedSetIt(tile, or, board, mode, hBoard);
            return res;
        } catch (OversizeException e){
            if (mode.equals("ver"))
                e.printStackTrace();
            return false;
        } catch (OverlapTilesException e){
            if (mode.equals("ver")){
                e.printStackTrace();
            }
            return false;
        } catch (AdjacentTilesException e) {
            if (mode.equals("ver")){
                e.printStackTrace();
            }
            return false;
        }
    }

    public boolean checkedSetIt(Tile tile,Orientation or,Tile[][] board, String mode, Board hBoard){
        switch (or) {
            case horizontal:
                this.startTileX = tile.getX();
                this.startTileY = tile.getY();
                for (int i=tile.getX(); i<tile.getX()+shipSize; i++){
                    board[i][tile.getY()].setSymbol("s");
                }
                return true;
            case vertical:
                this.startTileX = tile.getX();
                this.startTileY = tile.getY();
                for (int i=tile.getY(); i<tile.getY()+shipSize; i++){
                    board[tile.getX()][i].setSymbol("s");
                }
                return true;
        }
        return false;
    }

    public boolean checkAdjacent(Tile tile, Orientation or, Tile[][] board, Board hBoard) throws AdjacentTilesException{
        switch (or) {
            case horizontal:
                for (int i=tile.getX(); i<tile.getX()+shipSize; i++){
                    ArrayList<Tile> nei = hBoard.getAdjaentTiles(board[i][tile.getY()]);
                    for (Tile tl : nei ){
                        if (tl.getSymbol().equals("s")){
                            throw new AdjacentTilesException("Υπάρχει γειτονικό");
                        }
                    }
                }
                return true;
            case vertical:
                for (int i=tile.getY(); i<tile.getY()+shipSize; i++){
                    ArrayList<Tile> nei = hBoard.getAdjaentTiles(board[tile.getX()][i]);
                    for (Tile tl : nei ){
                        if (tl.getSymbol().equals("s")){
                            throw new AdjacentTilesException("Υπάρχει γειτονικό");
                        }
                    }
                }
                return true;
        }
        return false;
    }

    public boolean checkBounds(Tile tile,Orientation or) throws OversizeException {
        switch (or) {
            case vertical:
                if (tile.getY() + shipSize < 7) {
                    return true;
                } else {
                    throw new OversizeException("Εκτός ορίων");
                }
            case horizontal:
                if (tile.getX() + shipSize < 7) {
                    return true;
                } else {
                    throw new OversizeException("Εκτός ορίων");
                }
        }
        return false;
    }

    public boolean checkExist(Tile tile, Orientation or,Tile[][] board) throws  OverlapTilesException{
        switch (or) {
            case horizontal:
                for (int i=tile.getX(); i<tile.getX()+shipSize; i++){
                    if (board[i][tile.getY()].getSymbol().equals("s")){
                        throw new OverlapTilesException("Υπάρχει άλλο εδω : " + i);
                    }
                }
                return true;
            case vertical:
                for (int i=tile.getY(); i<tile.getY()+shipSize; i++){
                    if (board[tile.getX()][i].getSymbol().equals("s")){
                        throw new OverlapTilesException("Υπάρχει άλλο εδω : " + i);
                    }
                }
                return true;
        }
        return false;
    }

}
