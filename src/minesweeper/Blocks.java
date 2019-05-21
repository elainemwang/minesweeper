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
    boolean flag = false;
    public Blocks(int v, int x ,int y){
        value = v;
        setPos(x,y);
        setWidth(25);
        setHeight(25);
    }
    public void pressed(){
        clicked = true;
        
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
        if(flag == true){
            window.setColor(Color.RED);
            window.fillRect(getX(), getY(), getWidth(), getHeight());
            window.setColor(Color.BLACK);
            window.drawRect(xPos, yPos, width, height);
        }
        else if(clicked==true){
            window.setColor(Color.WHITE);
            window.fillRect(xPos, yPos, width, height);
            window.setColor(Color.BLACK);
            window.drawString(value+"", xPos+10, yPos+20);
        }
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
