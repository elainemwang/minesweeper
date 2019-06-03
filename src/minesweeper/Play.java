package minesweeper;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class Play extends JFrame implements overListener {

    private static final int WIDTH = 750;
    private static final int HEIGHT = 473;
    Minesweeper theGame;
    scoreboard highScores;
    enterName enter;
    JButton openScores;
    boolean back = false;

    public Play() throws FileNotFoundException {
        super("Minesweeper");

        setLayout(new BorderLayout());

        setSize(WIDTH, HEIGHT);

        theGame = new Minesweeper();
        ((Component) theGame).setFocusable(true);

        highScores = new scoreboard();
        ((Component) highScores).setFocusable(true);
        highScores.setVisible(false);

        enter = new enterName();
        ((Component) enter).setFocusable(true);
        enter.setVisible(false);

        theGame.addListener(enter);
        theGame.addListener(this);

        add(enter, BorderLayout.EAST);

        add(highScores, BorderLayout.WEST);

        add(theGame, BorderLayout.CENTER);

        setVisible(true);
        //theGame.setVisible(true);

        //highScores.setVisible(false);
        openScores = new JButton("Scores");

        openScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("pressed");
                if (!back) {
                    theGame.setVisible(false);
                    try {
                        highScores.update();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    highScores.setVisible(true);
                    openScores.setText("Back");
                    back = true;
                } else {
                    theGame.setVisible(true);
                    highScores.setVisible(false);
                    openScores.setText("Scores");
                    back = false;
                }
            }
        });

        add(openScores, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) throws FileNotFoundException {
        Play run = new Play();

    }

    @Override
    public void gameIsOver(int score) {
        enter.setVisible(true);
    }
}
