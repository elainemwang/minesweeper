/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author elainewang
 */
public class Blocks extends Button implements Displayable{ 
    int value;
    int flagsAround = 0;
    boolean flag = false;
    //check if block is open and spaced for opening around
    boolean spaced = false;
    
    public Blocks(int v, int x ,int y){
        value = v;
        setPos(x,y);
        setWidth(25);
        setHeight(25);
    }
    public void pressed(){
        if(!flag){
            clicked = true;
        }
    }
    public void flagged(){
        if(flag){
            flag = false;
            clicked = false;
        }
        else{
            flag = true;
            clicked = false;
        }
        spaced = false;
    }
    public void countFlags(Blocks b){
        if(b.flag){
            flagsAround++;
        }
    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }
    
    public void draw(Graphics window) {
        //add code to draw the block
        //flag drawing
        if(flag){
            window.setColor(Color.RED);
            window.fillRect(getX(), getY(), getWidth(), getHeight());
            window.setColor(Color.BLACK);
            window.drawRect(xPos, yPos, width, height);
        }
        //opened drawing
        else if(clicked){
            window.setColor(Color.WHITE);
            window.fillRect(xPos, yPos, width, height);
            if(value == 1){
                window.setColor(Color.BLUE);
            }
            else if(value == 2){
                window.setColor(Color.green);
            }
            else if(value == 3){
                window.setColor(Color.RED);
            }
            else if(value == 4){
                window.setColor(Color.PINK);
            }
            else if(value == 5){
                window.setColor(Color.MAGENTA);
            }
            else if(value == 6){
                window.setColor(Color.CYAN);
            }
            else if(value == 7){
                window.setColor(Color.BLACK);
            }
            else if(value == 8){
                window.setColor(Color.GRAY);
            }
            if(value != 9 && value != 0){
                window.drawString(value+"", xPos+10, yPos+20);
            }
            if(value == 9){
                window.setColor(Color.BLACK);
                window.fillRect(xPos, yPos, width, height);
            }
        }
        //unopened, unflagged drawing
        else{
            window.setColor(Color.LIGHT_GRAY);
            window.fillRect(getX(), getY(), getWidth(), getHeight());
            window.setColor(Color.BLACK);
            window.drawRect(xPos, yPos, width, height);
        }
        
    }
    public String toString(){
        return "*";
    }
}
