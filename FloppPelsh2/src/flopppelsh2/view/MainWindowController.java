/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flopppelsh2.view;

import flopppelsh2.FloppPelsh2;
import flopppelsh2.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 *
 * @author touch_p
 */
public class MainWindowController {
    FloppPelsh2 app;
    
    @FXML
    private Label player1Name;
    @FXML
    private Label player1Score;
    
    @FXML
    private Label player2Name;
    @FXML
    private Label player2Score;
    
    @FXML
    private Label player3Name;
    @FXML
    private Label player3Score;
    
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    @FXML
    private Button button31;
    @FXML
    private Button button32;
    @FXML
    private Button button33;
    @FXML
    private Button button34;
    @FXML
    private Button button41;
    @FXML
    private Button button42;
    @FXML
    private Button button43;
    @FXML
    private Button button44;
    
    
    public MainWindowController() {
        
    }
    

    public void setMainApp( FloppPelsh2 app ) {
        this.app = app;
    }
    
    public void setPlayersNameAndScore() {
        if ( app.players != null ) {
            player1Name.setText( app.players.get(0).getName() );
            player2Name.setText( app.players.get(1).getName() );
            player3Name.setText( app.players.get(2).getName() );

            player1Score.setText( Integer.toString( app.players.get(0).getScore() ) );
            player2Score.setText( Integer.toString( app.players.get(1).getScore() ) );
            player3Score.setText( Integer.toString( app.players.get(2).getScore() ) );
        } else {
            System.err.println( "Список пуст же, ну" );
        }
    }
    
    public void refreshScore() {
        player1Score.setText( Integer.toString( app.players.get(0).getScore() ) );
        player2Score.setText( Integer.toString( app.players.get(1).getScore() ) );
        player3Score.setText( Integer.toString( app.players.get(2).getScore() ) );
    }
    
    @FXML
    public void fileMenuNewGame() {
        app.showNewGameDialog();
        app.newGame();
    }
    
    @FXML
    public void fileMenuClose() throws Exception {
        app.stop();
    }
    
    @FXML
    public void playSong11Button() {
        button11.setDisable( true );
        app.playSong( app.songs.get( 0 ) );
    }
    
    @FXML
    public void playSong12Button() {
        button12.setDisable( true );
        app.playSong( app.songs.get( 1 ) );
    }
    
    @FXML
    public void playSong13Button() {
        button13.setDisable( true );
        app.playSong( app.songs.get( 2 ) );
    }
    
    @FXML
    public void playSong14Button() {
        button14.setDisable( true );
        app.playSong( app.songs.get( 3 ) );
    }
    
    @FXML
    public void playSong21Button() {
        button21.setDisable( true );
        app.playSong( app.songs.get( 4 ) );
    }
    
    @FXML
    public void playSong22Button() {
        button22.setDisable( true );
        app.playSong( app.songs.get( 5 ) );
    }
    
    @FXML
    public void playSong23Button() {
        button23.setDisable( true );
        app.playSong( app.songs.get( 6 ) );
    }
    
    @FXML
    public void playSong24Button() {
        button24.setDisable( true );
        app.playSong( app.songs.get( 7 ) );
    }
    
    @FXML
    public void playSong31Button() {
        button31.setDisable( true );
        app.playSong( app.songs.get( 8 ) );
    }
    
    @FXML
    public void playSong32Button() {
        button32.setDisable( true );
        app.playSong( app.songs.get( 9 ) );
    }
    
    @FXML
    public void playSong33Button() {
        button33.setDisable( true );
        app.playSong( app.songs.get( 10 ) );
    }
    
    @FXML
    public void playSong34Button() {
        button34.setDisable( true );
        app.playSong( app.songs.get( 11 ) );
    }
    
    @FXML
    public void playSong41Button() {
        button41.setDisable( true );
        app.playSong( app.songs.get( 12 ) );
    }
    
    @FXML
    public void playSong42Button() {
        button42.setDisable( true );
        app.playSong( app.songs.get( 13 ) );
    }
    
    @FXML
    public void playSong43Button() {
        button43.setDisable( true );
        app.playSong( app.songs.get( 14 ) );
    }
    
    @FXML
    public void playSong44Button() {
        button44.setDisable( true );
        app.playSong( app.songs.get( 15 ) );
    }
}
