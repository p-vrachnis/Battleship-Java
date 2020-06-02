package com.company;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Random rand = new Random();
        Player human = new Player("human");
        Player pc = new Player("pc");
        Board hBoard = new Board();
        Board pcBoard = new Board();
        System.out.print("Θέλετε να τοποθετηθούν τα πλοία μόνα τους ή όχι; (Y/N): ");
        if (randomPlace()) {
            hBoard.placeAllShips(hBoard); //βάζει τα πλοία του παίκτη
        } else {
            boolean res = false;
            while(res == false) {
                hBoard.drawBoard("vis");
                System.out.println("Δωσε συντεταγμένες για το BattleShip 4 θέσεων");
                int[] coor = getInput();
                System.out.println("Δώσε την κατεύθυνση του BattleShip (H/V)");
                Ship.Orientation or = getOrientation();
                Battleship bs = new Battleship();
                res = bs.placeShip(hBoard.board[coor[0]][coor[1]], or, hBoard.board, "vis", hBoard);
                if (!res){
                    System.out.println("Δεν μπορεί να μπεί εκεί πλοίο");
                }
            }
            res = false;
            while (res == false) {
                hBoard.drawBoard("vis");
                System.out.println("Δωσε συντεταγμένες για το Carrier 5 θέσεων");
                int[] coor = getInput();
                System.out.println("Δώσε την κατεύθυνση του Carrier (H/V)");
                Ship.Orientation or = getOrientation();
                Carrier cr = new Carrier();
                res = cr.placeShip(hBoard.board[coor[0]][coor[1]], or, hBoard.board, "vis", hBoard);
                if (!res){
                    System.out.println("Δεν μπορεί να μπεί εκεί πλοίο");
                }
            }
            res = false;
            while (res == false) {
                hBoard.drawBoard("vis");
                System.out.println("Δωσε συντεταγμένες για το Destroyer 2 θέσεων");
                int[] coor = getInput();
                System.out.println("Δώσε την κατεύθυνση του Destroyer (H/V)");
                Ship.Orientation or = getOrientation();
                Destroyer dr = new Destroyer();
                res = dr.placeShip(hBoard.board[coor[0]][coor[1]], or, hBoard.board, "vis", hBoard);
                if (!res){
                    System.out.println("Δεν μπορεί να μπεί εκεί πλοίο");
                }
            }
            res = false;
            while (res == false) {
                hBoard.drawBoard("vis");
                System.out.println("Δωσε συντεταγμένες για το Cruiser 3 θέσεων");
                int[] coor = getInput();
                System.out.println("Δώσε την κατεύθυνση του Cruiser (H/V)");
                Ship.Orientation or = getOrientation();
                Cruiser cs = new Cruiser();
                res = cs.placeShip(hBoard.board[coor[0]][coor[1]], or, hBoard.board, "vis", hBoard);
                if (!res){
                    System.out.println("Δεν μπορεί να μπεί εκεί πλοίο");
                }
            }
            res = false;
            while (res == false) {
                hBoard.drawBoard("vis");
                System.out.println("Δωσε συντεταγμένες για το Submarine 3 θέσεων");
                int[] coor = getInput();
                System.out.println("Δώσε την κατεύθυνση του Cruiser (H/V)");
                Ship.Orientation or = getOrientation();
                Submarine sb = new Submarine();
                res = sb.placeShip(hBoard.board[coor[0]][coor[1]], or, hBoard.board, "vis", hBoard);
                if (!res){
                    System.out.println("Δεν μπορεί να μπεί εκεί πλοίο");
                }
            }


        }
        pcBoard.placeAllShips(pcBoard);//βάζει τα πλοία του υπολογιστή
        System.out.println("Εσεις");
        hBoard.drawBoard("vis");
        System.out.println("Ο υπολογιστής");
        pcBoard.drawBoard("hid");
        while(!hBoard.allShipsSunk() && !pcBoard.allShipsSunk()){
            System.out.println("Δωσε συντεταγμένες για επίθεση");
            int[] coor = getInput();
            human.fire(pcBoard, coor[0], coor[1]);
            pc.fire(hBoard, rand.nextInt(7), rand.nextInt(7));
            hBoard.drawBoard("vis");
            pcBoard.drawBoard("hid");
        }
        int flagpc = 0;
        int flagh = 0;
        int i;
        int j;
        for (i=0; i<7; i++){
            for (j=0; j<7; j++){
                if (hBoard.board[i][j].getSymbol().equals("s")){
                    flagh++;
                }
                if (pcBoard.board[i][j].getSymbol().equals("s")){
                    flagpc++;
                }
            }
        }
        if (flagpc == 0){
            System.out.println("Ο παίκτης νίκησε");
            System.out.println("Στατιστικά παίκτη:");
            int[] st = human.getStats();
            System.out.println("Προσπάθιες: "+st[0]+"| Χτυπήματα: "+st[1]+"| Αστοχίες: "+st[2]+"| Χτυπήματα στο ίδιο κελί: "+st[3]);
            System.out.println("Στατιστικά υπολογιστη:");
            st = pc.getStats();
            System.out.println("Προσπάθιες: "+st[0]+"| Χτυπήματα: "+st[1]+"| Αστοχίες: "+st[2]+"| Χτυπήματα στο ίδιο κελί: "+st[3]);
        } else {
            System.out.println("Ο υπολογιστής νίκησε");
            System.out.println("Στατιστικά παίκτη:");
            int[] st = human.getStats();
            System.out.println("Προσπάθιες: "+st[0]+"| Χτυπήματα: "+st[1]+"| Αστοχίες: "+st[2]+"| Χτυπήματα στο ίδιο κελί: "+st[3]);
            System.out.println("Στατιστικά υπολογιστη:");
            st = pc.getStats();
            System.out.println("Προσπάθιες: "+st[0]+"| Χτυπήματα: "+st[1]+"| Αστοχίες: "+st[2]+"| Χτυπήματα στο ίδιο κελί: "+st[3]);
        }
    }

    public static boolean randomPlace(){
        Scanner keyboard = new Scanner(System.in);
        String des = keyboard.nextLine();
        if (des.equals("y") || des.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static Ship.Orientation getOrientation(){
        Scanner keyboard = new Scanner(System.in);
        while(true) {
            String des = keyboard.nextLine();
            if (des.equals("v") || des.equals("V")) {
                return Ship.Orientation.vertical;
            } else if (des.equals("h") || des.equals("H")) {
                return Ship.Orientation.horizontal;
            }
            System.out.println("Πρέπει να δώσετε ως είσοδο είτε V είτε H");
        }
    }

    public static int[] getInput(){
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            int des = keyboard.nextInt();
            int des2 = keyboard.nextInt();
            if (des > -1 && des2 > -1 && des < 7 && des2 < 7) {
                return new int[]{des, des2};
            } else {
                System.out.println("Οι τιμές πρέπει να είναι ανάμεσα στο 0-6");
            }
        }
    }

}
