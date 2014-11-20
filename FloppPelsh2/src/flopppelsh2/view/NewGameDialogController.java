/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flopppelsh2.view;

import javafx.scene.control.TextField;

import flopppelsh2.FloppPelsh2;
import flopppelsh2.model.Player;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author touch_p
 */
public class NewGameDialogController {  
    FloppPelsh2 app;
    Stage       dialogStage;
    
    @FXML
    private TextField player1;
    
    @FXML
    private TextField player2;
    
    @FXML
    private TextField player3;
    
    public void setMainApp( FloppPelsh2 app ) {
        this.app = app;
    }
    
    public void setDialogStage( Stage dialogStage ) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void startGameButton() {
        app.players = new ArrayList<Player>();
      
        if ( player1.getText() == null ) {
            System.out.println( "Чо-то пошло не так 2!!!" );
        } else {
            String playerName = player1.getText();
            System.err.println( playerName );
            
            Player player = new Player( playerName );
            app.players.add( player );
        }
        
        if ( player2.getText() == null ) {
            System.out.println( "Чо-то пошло не так 2!!!" );
        } else {
            String playerName = player2.getText();
            System.err.println( playerName );
            
            Player player = new Player( playerName );
            app.players.add( player );
        }
        
        if ( player3.getText() == null ) {
            System.out.println( "Чо-то пошло не так 2!!!" );
        } else {
            String playerName = player3.getText();
            System.err.println( playerName );
            
            Player player = new Player( playerName );
            app.players.add( player );
        }
        
        dialogStage.close();
    }
    
    @FXML
    public void cancelButton()  {
        dialogStage.close();
    }
}
