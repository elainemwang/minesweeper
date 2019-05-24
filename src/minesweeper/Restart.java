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
public class Restart extends Button implements Displayable{

    boolean restart = false;
    
    public Restart(){
        setPos(300,10);
        setWidth(50);
        setHeight(30);
    }

    public void pressed() {
        restart = true;
    }


    public void draw(Graphics window) {
        window.setColor(Color.GRAY);
        window.fillRect(xPos, yPos, width, height);
        window.setColor(Color.YELLOW);
        window.drawString("RESTART", xPos, yPos+10);
    }


    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int w) {
        width = w;
    }

    @Override
    public void setHeight(int h) {
        height = h;
    }
    
}
