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
    private int minesLeft, mines;
    private int time;
    int mouseCX, mouseCY, mouseX, mouseY;
    int numOpened;
    boolean p = true;
    boolean gameOver = false;
    Restart restart;

    public Minesweeper() {
        //set up all variables related to the game
       
        space = false;
        click = false;
        time = 0;
        mines = 99;
        minesLeft = mines;
        rows = 16;
        cols = 30;
        //board with array of blocks and array of values
        board = new Board(rows,cols,mines);
        //restart button
        restart = new Restart();

        

        setBackground(Color.WHITE);
        setVisible(true);

        
        
        new Thread(this).start();
        addKeyListener(this);	//starts the key thread to log key strokes
        addMouseListener(this); 
        addMouseMotionListener(this);
    }

    public void update(Graphics window) {
        if(!gameOver){
            paint(window);
        }
        
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
        
        //check for restart
        if(restart.restart){
            board = new Board(rows,cols,mines);
        }
        
        //draw all the blocks
        for(Blocks[] r : board.getMines()){
            for(Blocks b : r){
                //b.pressed();
                b.draw(graphToBack);
            }
        }
        restart.draw(graphToBack);
        
        //set the flag values for the board
        board.setFV();
        //open blocks around the spaced and opened blocks that have mines flagged
        board.openAround();
        
        
        //flagging mines
        if (space == true) {
            for(Blocks[] rows : board.getMines()){
                for(Blocks bl : rows){
                    //if the block isn't opened, flag it
                    if(!bl.clicked){
                        if(mouseX>=bl.getX()&&mouseX<bl.getX()+bl.getWidth()&&mouseY>=bl.getY()&&mouseY<bl.getY()+bl.getHeight()){
                            if(p){
                                bl.flagged();
                                //bl.draw(graphToBack);
                                p = false;
                                                            }
                        }
                    }
                    //if the block is opened, let the openAround method check if the blocks around it should be opened
                    else{
                        if(mouseX>=bl.getX()&&mouseX<bl.getX()+bl.getWidth()&&mouseY>=bl.getY()&&mouseY<bl.getY()+bl.getHeight()){
                            if(p){  
                                bl.spaced=true;
                                p = false;
                            }
                        }
                    }
                }
            }  
            
        }
        
        //on space release, unmark all blocks for openAround
        if(space == false){
            for(Blocks[] r : board.getMines()){
                for(Blocks bk : r){
                    bk.spaced = false;
                    //bk.draw(graphToBack);
                }
            }
        }
        
        
        
        //opening blocks with clicks and clicking restart button
        if (click == true) {
            if(mouseCX>=restart.getX()&&mouseCX<restart.getX()+restart.getWidth()&&mouseCY>=restart.getY()&&mouseCY<restart.getY()+restart.getHeight()){
                //restart the game
                restart.pressed();
            }
            for(Blocks[] ros : board.getMines()){
                for(Blocks bl : ros){
                    //if the block isn't flagged
                    if(!bl.flag){
                        if(mouseCX>=bl.getX()&&mouseCX<bl.getX()+bl.getWidth()&&mouseCY>=bl.getY()&&mouseCY<bl.getY()+bl.getHeight()){
                            //open the block
                            bl.pressed();
                            //bl.draw(graphToBack);
                        }
                    }
                }
            }
        }
        
        
        
        //check for win/loss
        numOpened = 0;
        for(Blocks[] rss : board.getMines()){
            for(Blocks bks : rss){
                if(bks.clicked&&bks.value!=9){
                    numOpened++;
                }
                //check for loss
                if(bks.value == 9 && bks.clicked){
                    for(Blocks[] ro : board.getMines()){
                        for(Blocks all : ro){
                            //add flag check to see what was wrong (not complete)
                            all.pressed();
                            //all.draw(graphToBack);
                        }
                    }
                    gameOver = true;
                    break;
                }
            }
        }
        //check for win
        //System.out.println(numOpened);
        if(numOpened == (rows*cols)-mines){
            System.out.println(numOpened);
            System.out.println("YOU WIN!!"); //wrong
        }
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
