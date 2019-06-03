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
public class Timer implements Displayable {

    int xPos;
    int yPos;

    int width;
    int height;

    public Timer() {
        setPos(600, 20);
        setWidth(50);
        setHeight(20);
    }

    @Override
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWidth(int w) {
        width = w;
    }

    @Override
    public void setHeight(int h) {
        height = h;
    }

    public void draw(Graphics window, long time) {
        window.setColor(Color.WHITE);
        window.fillRect(xPos, yPos, width, height);
        window.setColor(Color.BLACK);
        window.drawRect(xPos, yPos, width, height);
        window.drawString(time + "", xPos + 20, yPos + 15);
    }

}
