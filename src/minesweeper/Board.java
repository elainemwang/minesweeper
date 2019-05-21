/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author elainewang
 */
public class Board{
    int r, c, value;
    private int[][] board;
    public Board(int rows, int cols, int bombs){
        value = 0;
        board = new int[rows+2][cols+2];
        for(int i = 0; i < bombs; i++){
            r = ((int) (Math.random()*(rows))+1);   //add to later to
            c = ((int) (Math.random()*(cols))+1);   //make sure the bombs cannot 
            board[r][c] = 9;                    //be on the same spot              
        }
        for(int rw = 1; rw < rows+1; rw++){
            for(int cl = 1; cl < cols+1; cl++){
                value = 0;
                for(int j = rw - 1; j <= rw+1; j++){
                    for(int i = cl - 1; i <= cl+1; i++){
                        if(board[j][i] == 9){
                            value++;
                        }
                    }
                }
                if(board[rw][cl] != 9){
                    board[rw][cl] = value;
                }
                
            }
        }
    }
    public int[][] getBoard(){
        return board;
    }
    
    
    
}
