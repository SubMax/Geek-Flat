/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flopppelsh2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import moppydesk.MoppySequencer;

import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.directinput.DirectInputDevice;
import de.hardcode.jxinput.event.JXInputEventManager;
import flopppelsh2.model.Player;

import flopppelsh2.model.Song;
import flopppelsh2.view.GuessOrNotDialogController;
import flopppelsh2.view.NewGameDialogController;
import flopppelsh2.view.MainWindowController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.stage.Modality;

/**
 *
 * @author touch_p
 */
public class FloppPelsh2 extends Application {
    
    private Stage primaryStage;
    private AnchorPane mainLayout;
    
    MoppySequencer ms = null;
    DirectInputDevice gpad = null;
    
    public ArrayList<Song> songs  = null;
    BufferedReader songsInfoFile = null;

    boolean isSongPlaying = false;
    
    public ArrayList<Player> players = null;
    
    MainWindowController        mainWindowController;
    NewGameDialogController     newGameDialogController;
    GuessOrNotDialogController  guessOrNotDialogController;
    
    int currentSong = 0;
    
    public FloppPelsh2() {
        
    }
    
    @Override
    public void start( Stage primaryStage ) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle( "FloppPelsh" );
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( FloppPelsh2.class.getResource( "view/MainWindow.fxml" ) );
            
            mainLayout = (AnchorPane) loader.load();
            
            Scene scene = new Scene( mainLayout );
            primaryStage.setScene( scene );
            primaryStage.show();
            
            mainWindowController = loader.getController();
            mainWindowController.setMainApp( this );
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
        
        showNewGameDialog();
        newGame();
        
        initSequencer();
        
        initGamePad();
        
        loadSongs();
    }

    @Override
    public void stop() {
        primaryStage.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void initSequencer () {
        if ( ms != null ) {
            ms.closeSequencer();
        }
        try {
            ms = new MoppySequencer( "COM5" );
            if ( ms == null ) {
                System.out.println( "Ардуино подключена" );
            }
        } catch (Exception ex) {
            System.out.println( "Подключи ардуино" );
        }
    }
    
    private void initGamePad() {
        JXInputEventManager.setTriggerIntervall ( 1 );
  
        for(int i = 0; i < JXInputManager.getNumberOfDevices(); i++){
            if( JXInputManager.getJXInputDevice( i ).getName().equals( "USB Joystick          " ) ){
                gpad = new DirectInputDevice( i );   
            }
        }    
    }
    
    private void loadSongs() throws IOException {
        songs = new ArrayList();
        try {
           songsInfoFile = new BufferedReader( new FileReader( "res/midi/songs.txt" ) );
        } catch ( FileNotFoundException ex ) {
            System.err.println( "Список песен не найден" );
        }
        
        
        while ( true ) {            
            String name = songsInfoFile.readLine();
            String path = songsInfoFile.readLine();
            String cost = songsInfoFile.readLine();
            
            if ( name == null ) {
                break;
            }
            
            Song song = new Song( name, path, Integer.parseInt( cost ) );
            songs.add( song );
        }
    }
    
    public void playSong ( Song song ) {
        System.err.print("Играю: ");
        System.err.println( song.getName() );
        System.err.println( song.getPath() );
        System.err.println( song.getCost() );
        
        isSongPlaying = true;
        
        if ( ms != null ) {
            try {
                ms.loadFile( song.getPath() );
            } catch ( Exception ex) {
                ex.printStackTrace();
            }

            try {
                ms.startSequencer();
            } catch ( Exception ex ) {
                System.out.println("ms is null");
            }

            try {
                while ( isSongPlaying ) {
                    if ( gpad.getButton( 0 ).getState() && players.get( 0 ).canResponds() ) {
                        isSongPlaying = false;
                        System.err.println( "Player_1" );
                        ms.stopSequencer();
                        showGuessOrNotDialog( players.get( 0 ), song );
                    } else if ( gpad.getButton( 1 ).getState() && players.get( 1 ).canResponds() ) {
                        isSongPlaying = false;
                        System.err.println( "Player_2" );
                        ms.stopSequencer();
                        showGuessOrNotDialog( players.get( 1 ), song );
                    } else if ( gpad.getButton( 2 ).getState() && players.get( 2 ).canResponds() ) {
                        isSongPlaying = false;
                        System.err.println( "Player_3" );
                        ms.stopSequencer();
                        showGuessOrNotDialog( players.get( 2 ), song );
                    } else if ( gpad.getButton( 3 ).getState() ) {
                        isSongPlaying = false;
                        System.err.println( "Leader" );
                    }
                }    
            } catch ( Exception ex ) {
            }
        } else {
            System.err.println( "ms is null" );
        }
    }
    
    public void showNewGameDialog() {
        currentSong = 0;
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( FloppPelsh2.class.getResource( "view/NewGameDialog.fxml" ) );
            
            AnchorPane dialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle( "New game" );
            dialogStage.initModality( Modality.WINDOW_MODAL );
            dialogStage.initOwner( primaryStage );
            
            Scene scene = new Scene( dialog );
            dialogStage.setScene( scene );
            
            newGameDialogController = loader.getController();
            newGameDialogController.setMainApp( this );
            newGameDialogController.setDialogStage( dialogStage );
            
            dialogStage.showAndWait();           
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }
    
    public void showGuessOrNotDialog( Player player, Song song ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( FloppPelsh2.class.getResource( "view/GuessOrNotDialog.fxml" ) );
            
            AnchorPane dialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle( "Guess or not" );
            dialogStage.initModality( Modality.WINDOW_MODAL );
            dialogStage.initOwner( primaryStage );
            
            Scene scene = new Scene( dialog );
            dialogStage.setScene( scene );
            
            guessOrNotDialogController = loader.getController();
            guessOrNotDialogController.setMainApp( this );
            guessOrNotDialogController.setDialogStage( dialogStage );
            guessOrNotDialogController.setPlayer( player, song );
            
            dialogStage.showAndWait();           
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }
    
    public void newGame() {
        mainWindowController.setPlayersNameAndScore();
    }
    
    public void refreshScore() {
        mainWindowController.setPlayersNameAndScore();
    }
    
    public void incrementSong() {
        for ( Player p: players ) {
            p.needDisbanned( currentSong );
        }
        currentSong++;
    }
}
