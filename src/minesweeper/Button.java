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
public abstract class Button {

    int xPos;
    int yPos;
    int width;
    int height;
    boolean clicked = false;

    public abstract void pressed();

    public abstract void draw(Graphics window);

}
