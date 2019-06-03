package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.sort;
import java.util.Scanner;
import javax.swing.event.MouseInputListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;

public class Minesweeper extends Canvas implements KeyListener, Runnable, MouseInputListener {

    private int rows, cols;
    private Board board;
    private boolean space;
    private boolean click, rClick;
    private BufferedImage back;
    private int minesLeft, mines;
    private long stime;
    private long etime;
    int mouseCX, mouseCY, mouseX, mouseY;
    int numOpened;
    boolean p = true;
    boolean gameOver = true;
    Restart restart;
    boolean first = true;
    Timer timer;
    boolean start = true;
    Start startB;
    boolean enterScores = false;
    long oTime = 0;
    String[] names = new String[5];
    int[] scores = new int[5];
    Player[] players = new Player[5];
    boolean set = false;
    int num = 0;
    //listeners to open up name enter panel when game is won
    ArrayList<overListener> listeners;

    public Minesweeper() throws FileNotFoundException {
        //set up all variables related to the game

        space = false;
        click = false;
        rClick = false;
        mines = 99;
        rows = 16;
        cols = 30;
        //board with array of blocks and array of values
        board = new Board(rows, cols, mines);
        mines = board.bb;
        minesLeft = mines;
        //restart button
        restart = new Restart();
        timer = new Timer();
        startB = new Start();

        listeners = new ArrayList<overListener>();

        File scs = new File("/Users/elainewang/Desktop/NetbeansProjects/minesweeper/src/minesweeper/scores.txt");
        File nms = new File("/Users/elainewang/Desktop/NetbeansProjects/minesweeper/src/minesweeper/names.txt");
        Scanner sc = new Scanner(scs);
        Scanner nm = new Scanner(nms);
        for (int i = 0; i < names.length; i++) {
            names[i] = nm.nextLine();
            scores[i] = sc.nextInt();
            players[i] = new Player(scores[i],names[i]);
        }

        setBackground(Color.WHITE);
        setVisible(true);

        new Thread(this).start();
        addKeyListener(this);	//starts the key thread to log key strokes
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void update(Graphics window) {
        //start page
        if (start) {
            window.setColor(Color.WHITE);
            window.fillRect(0, 0, 750, 473);
            window.setColor(Color.BLACK);
            startB.draw(window);
            window.drawString("Elaine Wang Period 1", 100, 100);
            window.drawString("Minesweeper", 100, 220);
            window.drawString("clear the board of everything but mines", 100, 250);
            window.drawString("Google \"how to play minesweeper\" for instructions on how to play", 100, 280);
            window.drawString("Space to flag, click to open, space when opened opens around if number of flags are correct", 100, 310);
            if (click == true) {
                if (mouseCX >= startB.getX() && mouseCX < startB.getX() + startB.getWidth() && mouseCY >= startB.getY() && mouseCY < startB.getY() + startB.getHeight()) {
                    start = false;
                    gameOver = false;
                    click = false;
                }
            }
        }

        if (!gameOver) {
            paint(window);
        }
        if (rClick) {
            if (mouseCX >= restart.getX() && mouseCX < restart.getX() + restart.getWidth() && mouseCY >= restart.getY() && mouseCY < restart.getY() + restart.getHeight()) {
                //restart the game
                restart.pressed();

            }
        }
        //check for restart
        if (restart.clicked) {
            board = new Board(rows, cols, 99);
            mines = board.bb;
            first = true;
            restart.clicked = false;
            //fix start -> immediately flag
            p = true;
            numOpened = 0;
            gameOver = false;
            set = false;
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
        //draw all the blocks

        restart.draw(graphToBack);

        //set the flag values for the board
        board.setFV();
        //open blocks around the spaced and opened blocks that have mines flagged
        board.openAround();

        //flagging mines
        if (space == true) {
            //System.out.println(p);
            for (Blocks[] rows : board.getMines()) {
                for (Blocks bl : rows) {
                    //if the block isn't opened, flag it
                    if (!bl.clicked) {
                        if (mouseX >= bl.getX() && mouseX < bl.getX() + bl.getWidth() && mouseY >= bl.getY() && mouseY < bl.getY() + bl.getHeight()) {
                            if (p) {
                                //System.out.println("flagging");
                                if (first) {
                                    stime = System.nanoTime();
                                }
                                first = false;
                                bl.flagged();
                                bl.draw(graphToBack);
                                p = false;
                            }
                        }
                    } //if the block is opened, let the openAround method check if the blocks around it should be opened
                    else {
                        if (mouseX >= bl.getX() && mouseX < bl.getX() + bl.getWidth() && mouseY >= bl.getY() && mouseY < bl.getY() + bl.getHeight()) {
                            if (p) {
                                bl.spaced = true;
                                p = false;
                            }
                        }
                    }
                }
            }
        }

        //on space release, unmark all blocks for openAround
        if (space == false) {
            for (Blocks[] r : board.getMines()) {
                for (Blocks bk : r) {
                    bk.spaced = false;
                    //bk.draw(graphToBack);
                }
            }
        }

        //opening blocks with clicks
        if (click == true) {
            for (Blocks[] ros : board.getMines()) {
                for (Blocks bl : ros) {
                    //if the block isn't flagged
                    if (mouseCX >= bl.getX() && mouseCX < bl.getX() + bl.getWidth() && mouseCY >= bl.getY() && mouseCY < bl.getY() + bl.getHeight()) {
                        //open the block
                        if (!bl.flag) {
                            if (first) {
                                stime = System.nanoTime();
                            }
                            first = false;
                            bl.pressed();
                        }
                    }
                }
            }
            click = false;
        }

        //check for win/loss
        numOpened = 0;
        for (Blocks[] rss : board.getMines()) {
            for (Blocks bks : rss) {
                if (bks.clicked && bks.value != 9) {
                    numOpened++;
                }
                //check for loss
                if (bks.value == 9 && bks.clicked) {
                    for (Blocks[] ro : board.getMines()) {
                        for (Blocks all : ro) {
                            //add flag check to see what was wrong (not complete)
                            all.pressed();
                            all.draw(graphToBack);
                        }
                    }
                    gameOver = true;
                    break;
                }
            }
        }

        for (Blocks[] r : board.getMines()) {
            for (Blocks b : r) {
                //b.pressed();
                b.draw(graphToBack);
            }
        }

        //check for win
        //System.out.println(numOpened);
        if (numOpened == (rows * cols) - mines) {
            if (!set) {
                gameWon();
            }
        }

        //timer
        if (!gameOver) {
            etime = System.nanoTime();
        }
        if (!first) {
            timer.draw(graphToBack, (etime - stime) / 1000000000);
        } else {
            timer.draw(graphToBack, 0);
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void addListener(overListener toAdd) {
        listeners.add(toAdd);
    }

    public void gameWon() {
        System.out.println(numOpened);
        System.out.println("YOU WIN!!");
        enterScores = true;
        gameOver = true;
        oTime = (etime - stime) / 1000000000;
        
        

        for (overListener ol : listeners) {
            ol.gameIsOver((int) oTime);
        }

        set = true;
    }

    public void actionPerformed(ActionEvent a) {

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
        rClick = true;
        mouseCX = e.getX();
        mouseCY = e.getY();
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        click = false;
        rClick = false;

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
