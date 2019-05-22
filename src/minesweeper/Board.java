/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.ArrayList;

/**
 *
 * @author elainewang
 */
public class Board{
    int r, c, value;
    private int[][] board;
    private Blocks[][] mines;
    private int bXPos = 0;
    private int bYPos = 0;
    private int rows;
    private int cols;
    
    public Board(int rows, int cols, int bombs){
        value = 0;
        this.rows = rows;
        this.cols = cols;
        board = new int[rows+2][cols+2];
        mines = new Blocks[rows][cols];
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
        for(int r = 1; r < rows+1; r++){
            for(int c = 1; c < cols+1; c++){
                mines[r-1][c-1] = (new Blocks(board[r][c],bXPos,bYPos));
                bXPos+=25;
                if(bXPos>=750){
                    bXPos=0;
                    bYPos+=25;
                }
            }
        }
    }
    public void setFV(){
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                mines[r][c].flagsAround=0;
                for(int j = r - 1; j <= r+1; j++){
                    for(int i = c - 1; i <= c+1; i++){
                        if(!(r<1)&&!(c<1)&&!(j>rows-1)&&!(i>cols-1)){
                            mines[r][c].countFlags(mines[j][i]);
                        }
                    }
                }
            }
        }
    }
    public void openAround(){
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if(mines[r][c].flagsAround==mines[r][c].value&&mines[r][c].clicked){
                    for(int j = r - 1; j <= r+1; j++){
                        for(int i = c - 1; i <= c+1; i++){
                            if(!(r<1)&&!(c<1)&&!(j>rows-1)&&!(i>cols-1)){
                                mines[j][i].pressed();
                            }
                        }
                    }
                }
            }
        }
    }
    public int[][] getBoard(){
        return board;
    }
    public Blocks[][] getMines(){
        return mines;
    }
    
    
    
    
}
