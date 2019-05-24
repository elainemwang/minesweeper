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
    private int bYPos = 50;
    private int rows;
    private int cols;
    //something in the board is making the program extremely slow to start
    public Board(int rows, int cols, int bombs){
        value = 0;
        this.rows = rows;
        this.cols = cols;
        board = new int[rows+2][cols+2];
        mines = new Blocks[rows][cols];
        //set bomb positions
        for(int i = 0; i < bombs; i++){
            r = ((int) (Math.random()*(rows))+1);   //add to later to
            c = ((int) (Math.random()*(cols))+1);   //make sure the bombs cannot 
            board[r][c] = 9;                    //be on the same spot              
        }
        //set values
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
        //set positions
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
    //set flag value of the block (number of flags around)
    public void setFV(){
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                mines[r][c].flagsAround=0;
                for(int j = r - 1; j <= r+1; j++){
                    for(int i = c - 1; i <= c+1; i++){
                        if(!(j<0)&&!(i<0)&&!(j>rows-1)&&!(i>cols-1)){
                            mines[r][c].countFlags(mines[j][i]);
                        }
                    }
                }
            }
        }
    }
    public void openAround(){
        for(int rs = 0; rs < rows; rs++){
            for (int cs = 0; cs < cols; cs++){
                //check if the number of flags around equals to the value of the block and block is opened and spaced
                if(mines[rs][cs].flagsAround==mines[rs][cs].value&&mines[rs][cs].spaced||mines[rs][cs].clicked&&mines[rs][cs].value == 0){
                    //if true, open everything around it
                    for(int j = rs - 1; j <= rs+1; j++){
                        for(int i = cs - 1; i <= cs+1; i++){
                            if(!(j<0)&&!(i<0)&&!(j>rows-1)&&!(i>cols-1)&&!mines[j][i].flag){
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
