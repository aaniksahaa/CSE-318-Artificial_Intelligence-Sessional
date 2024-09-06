import board.Board;
import game.Game;
import heuristics.Hamming;
import heuristics.Heuristic;
import heuristics.Manhattan;
import util.InversionCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static final int INVALID_INDICATOR = -1;
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str); // or Double.parseDouble(str) for floating-point numbers
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int parseNumber(String str) {
        try {
            return Integer.parseInt(str); // or Double.parseDouble(str) for floating-point number
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k;
        System.out.print("Enter the value of k: ");
        k = scanner.nextInt();
        if(k <= 0){
            System.out.println("Sorry! The value of k must be positive.");
            return;
        }
        int n = (int) (Math.pow(k,2)) - 1;

        int[][] input = new int[k][k];

        ArrayList<String>errors = new ArrayList<>();

        System.out.println("Input the initial board: ");
        for(int i=0; i<k; i++) {
            for (int j = 0; j < k; j++) {
                String s = scanner.next();
                if (s.equals("*")) {
                    input[i][j] = 0;
                } else if (isNumber(s)) {
                    int num = parseNumber(s);
                    if(num > n){
                        errors.add("Out of range number inputted at row "+i+", column "+j);
                    }
                    input[i][j] = num;
                } else {
                    input[i][j] = INVALID_INDICATOR;
                    errors.add("Invalid string inputted at row "+i+", column "+j);
                }
            }
        }

        ArrayList<Integer>arr = new ArrayList<>();
        for(int i=0; i<k; i++) {
            for (int j = 0; j < k; j++) {
                arr.add(input[i][j]);
            }
        }
        Collections.sort(arr);

        Boolean board_error = false;
        for(int i=0; i<k*k; i++){
            if(arr.get(i) != i){
                board_error = true;
                break;
            }
        }

        if(errors.size() > 0){
            System.out.println("Sorry! Invalid Input.");
            for(String e: errors){
                System.out.println(e);
            }
            return;
        }
        if (board_error){
            System.out.println("Sorry! The numbers do not form a valid board.");
            return;
        }

        // valid input
        Board inputBoard = new Board(k,input);
//        for(int x: inputBoard.arr){
//            System.out.print(x);
//        }

//        int [][] a = new int[k][k];
//        int c = 1;
//        for(int i=0; i<k; i++){
//            for(int j=0; j<k; j++){
//                if(c == k*k){
//                    a[i][j] = 0;
//                }
//                else {
//                    a[i][j] = c;
//                }
//                c++;
//            }
//        }
//
//        Board targetBoard = new Board(k,a);

//        Heuristic h = new Hamming();
//        System.out.println(h.name + ": " + h.calculate(inputBoard, targetBoard));
//
//        h = new Manhattan();
//        System.out.println(h.name + ": " + h.calculate(inputBoard, targetBoard));
//
        System.out.println(inputBoard.getInvCount());

        Game game = new Game(inputBoard);

        System.out.println(game.checkSolvable());

    }
}