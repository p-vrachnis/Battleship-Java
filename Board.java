package com.company;
import com.company.Ship.Orientation;

import java.util.Random;
import java.util.ArrayList;

public class Board {
    public Tile[][] board;

    public Board(){
        board = new Tile[7][7];
        int i;
        int j;
        for (i=0; i<7; i++){
            for (j=0; j<7; j++){
                board[i][j] = new Tile();
                board[i][j].setSymbol("~");
                board[i][j].setY(j);
                board[i][j].setX(i);
            }
        }

    }

    public void drawBoard(String show){
        System.out.println("  0 1 2 3 4 5 6");
        for (int j=0; j<7; j++){
            System.out.print(j+" ");
            for (int i=0; i<7; i++){
                if (show.equals("hid") && board[i][j].getSymbol().equals("s")) {
                    System.out.print("~ ");
                } else {
                    board[i][j].draw();
                }
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Tile> getAdjaentTiles(Tile tile){
        //Επιστρέφει γειτονικά κελιά
        int x = tile.getX();
        int y = tile.getY();
        ArrayList<Tile> nearby = new ArrayList<Tile>();
        if (x+1 < 7){
            nearby.add(board[x+1][y]);
        }
        if (y+1 < 7){
            nearby.add(board[x][y+1]);
        }
        if (y-1 > -1){
            nearby.add(board[x][y-1]);
        }
        if (x-1 > -1){
            nearby.add(board[x-1][y]);
        }
        return nearby;
    }

    public void placeAllShips(Board hBoard){
        //τυχαία βάζει τα πλοία στο Board
        boolean res = false;
        Random rand = new Random();
        Orientation or;
        Carrier cr = new Carrier();
        while (res == false) {
            or = getOr();
            res = cr.placeShip(this.board[rand.nextInt(7)][rand.nextInt(7)], or, this.board, "hid", hBoard);
        }
        res = false;
        Battleship bs = new Battleship();
        while (res == false) {
            or = getOr();
            res = bs.placeShip(this.board[rand.nextInt(7)][rand.nextInt(7)], or, this.board, "hid", hBoard);
        }
        Cruiser cs = new Cruiser();
        res = false;
        while (res == false) {
            or = getOr();
            res = cs.placeShip(this.board[rand.nextInt(7)][rand.nextInt(7)], or, this.board, "hid", hBoard);
        }
        Submarine sb = new Submarine();
        res = false;
        while (res == false) {
            or = getOr();
            res = sb.placeShip(this.board[rand.nextInt(7)][rand.nextInt(7)], or, this.board, "hid", hBoard);
        }
        res = false;
        Destroyer dr = new Destroyer();
        while (res == false) {
            or = getOr();
            res = dr.placeShip(this.board[rand.nextInt(7)][rand.nextInt(7)], or, this.board, "hid", hBoard);
        }
    }

    public boolean allShipsSunk(){
        //true αν ολα βυθησμένα false αλλιώς
        int i;
        int j;
        for (i=0; i<7; i++){
            for (j=0; j<7; j++){
                if (board[i][j].getSymbol().equals("s")){
                    return false;
                }
            }
        }
        return true;
    }

    public Orientation getOr(){
        Random rand = new Random();
        Orientation or;
        if (rand.nextInt(2) == 0){
            or = Orientation.horizontal;
        } else {
            or = Orientation.vertical;
        }
        return or;
    }
}
