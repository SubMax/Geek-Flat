/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flopppelsh2.view;

import flopppelsh2.FloppPelsh2;
import flopppelsh2.model.Player;
import flopppelsh2.model.Song;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 * @author touch_p
 */
public class GuessOrNotDialogController {
    FloppPelsh2 app;
    Stage       dialogStage;
    
    public Player   player;
    public Song     song;
    
    @FXML
    private Label playerName;    
    
    public void setMainApp( FloppPelsh2 app ) {
        this.app = app;
    }
    
    public void setDialogStage( Stage dialogStage ) {
        this.dialogStage = dialogStage;
    }
    
    public void setPlayer( Player player, Song song ) {
        this.player = player;
        this.song = song;
        
        playerName.setText( player.getName() );
    }
    
    @FXML
    public void rightAnswerButton() {
        app.incrementSong();
        
        int newScore = player.getScore() + song.getCost();
        player.setScore( newScore );
        
        app.refreshScore();
        
        System.err.println( player.getScore() );
        dialogStage.close();
    }
    
    @FXML
    public void wrongAnswerButton() {
        player.setCanResponds( false );
        app.incrementSong();
        
        player.setName( "Banned" );
        
        app.refreshScore();
        
        Dialogs.create()
                .title("")
                .message( song.getName() )
                .showWarning();
        
        dialogStage.close();
    }
}
