/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author elainewang
 */
public class Player implements Comparable{
    int score;
    String name;
    public Player(int s, String n){
        score = s;
        name = n;
    }

    @Override
    public int compareTo(Object o) {
        Player temp = (Player) o;
        if(temp.score>score){
            return -1;
        }
        else if (temp.score<score){
            return 1;
        }
        else{
        return 0;
        }
    }
}
