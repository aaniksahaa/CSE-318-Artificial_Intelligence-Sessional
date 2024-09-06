package heuristics;

import board.Board;

public class Hamming extends Heuristic{
    public Hamming(){
        this.name = "Hamming";
    }
    @Override
    public int calculate(Board currentBoard, Board targetBoard) {
        int d = 0;

        for(int i=0; i<currentBoard.arr.length; i++){
            if((currentBoard.arr[i] != 0) && (currentBoard.arr[i] != targetBoard.arr[i])){
                d++;
            }
        }

        return d;
    }
}
