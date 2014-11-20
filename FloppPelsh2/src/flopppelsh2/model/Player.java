/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flopppelsh2.model;

/**
 *
 * @author touch_p
 */
public class Player {
    String name;
    String bufferedName;
    int score;
    boolean canResponds;
    int songAtWhichBanned;
    
    public Player( String name) {
        this.name = name;
        bufferedName = name;
        score = 0;
        canResponds = true;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName( String name ) {
        this.name = name; 
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore( int score ) {
        this.score = score; 
    }
    
    public boolean canResponds() {
        return canResponds;
    }
    
    public void setCanResponds( boolean b) {
        canResponds = b;
    }
    
    public void setSongAtWhichBanned( int n ) {
        songAtWhichBanned = n;
    }
    
    public int getSongAtWhichBanned() {
        return songAtWhichBanned;
    }
    
    public void needDisbanned( int currentSong ) {
        if ( currentSong - songAtWhichBanned >= 3 ) {
            setCanResponds( true );
            
            name = bufferedName;
        }
    }
}
