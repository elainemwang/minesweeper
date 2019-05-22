package minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.MouseInputListener;
import java.util.concurrent.TimeUnit;


public class Minesweeper extends Canvas implements KeyListener, Runnable, MouseInputListener {

    private int rows, cols;
    private Board board;
    private boolean space;
    private boolean click;
    private BufferedImage back;
    private int minesLeft;
    private int time;
    int mouseCX, mouseCY, mouseX, mouseY;
    boolean p = true;

    public Minesweeper() {
        //set up all variables related to the game
       
        space = false;
        click = false;
        time = 0;
        minesLeft = 99;
        rows = 16;
        cols = 30;
        board = new Board(rows,cols,minesLeft);

        

        setBackground(Color.WHITE);
        setVisible(true);

        
        
        new Thread(this).start();
        addKeyListener(this);	//starts the key thread to log key strokes
        addMouseListener(this); 
        addMouseMotionListener(this);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current Frame and same it as an image
        //that is the exact same width and height as the current Frame
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        
        Graphics graphToBack = back.createGraphics();

        graphToBack.setColor(Color.WHITE);
        
        for(Blocks[] r : board.getMines()){
            for(Blocks b : r){
                //b.pressed();
                b.draw(graphToBack);
            }
        }

        board.setFV();
        board.openAround();

        
        //flagging mines
        if (space == true) {
            for(Blocks[] rows : board.getMines()){
                for(Blocks bl : rows){
                    if(!bl.clicked){
                        if(mouseX>=bl.getX()&&mouseX<bl.getX()+bl.getWidth()&&mouseY>=bl.getY()&&mouseY<bl.getY()+bl.getHeight()){
                            if(p == true){
                                bl.flagged();
                                p = false;
                            }
                        }
                    }
                }
            }  
        }
        //opening blocks
        int fCount = 0;
        if (click == true) {
            fCount = 0;
            for(int r = 0; r < rows; r++){
                for(int c = 0; c < cols; c++){
                    if(!board.getMines()[r][c].flag){
                        if(mouseCX>=board.getMines()[r][c].getX()&&mouseCX<board.getMines()[r][c].getX()+board.getMines()[r][c].getWidth()&&mouseCY>=board.getMines()[r][c].getY()&&mouseCY<board.getMines()[r][c].getY()+board.getMines()[r][c].getHeight()){
                            board.getMines()[r][c].pressed();
                        }
                    }
                }
            }
        }
        /*
        if (click == true) {
            for(Blocks[] ros : mines){
                for(Blocks bl : ros){
                    if(!bl.flag){
                        if(mouseCX>=bl.getX()&&mouseCX<bl.getX()+bl.getWidth()&&mouseCY>=bl.getY()&&mouseCY<bl.getY()+bl.getHeight()){
                            bl.pressed();
                            
                        }
                    }
                }
            }
        }*/

        twoDGraph.drawImage(back, null, 0, 0);
    }
    
    public void actionPerformed(ActionEvent a){
        
    }

    
    
    
    public void keyPressed(KeyEvent e) {
        switch (toUpperCase(e.getKeyChar())) {
            case ' ':
                space = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (toUpperCase(e.getKeyChar())) {
            case ' ':
                space = false;
                p = true;
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(8);
                repaint();
            }
        } catch (Exception e) {
        }
    }

    public void mouseClicked(MouseEvent e) {
        click = true;
        mouseCX = e.getX();
        mouseCY = e.getY();
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        click = false;
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    public void mouseDragged(MouseEvent e) {
        
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
