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
public class Start extends Button implements Displayable {

    public Start() {
        setPos(100, 150);
        setWidth(50);
        setHeight(30);
    }

    @Override
    public void pressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics window) {
        window.setColor(Color.BLACK);
        window.drawRect(xPos, yPos, width, height);
        window.drawString("Start", xPos + 10, yPos + 20);
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
