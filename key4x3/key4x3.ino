// 1-8...7-2

#include <Keypad.h>

const byte DOOR_PIN = 9;

const byte ROWS = 4; //four rows
const byte COLS = 3; //three columns
char keys[ROWS][COLS] = {
      {'1','2','3'},
      {'4','5','6'},
      {'7','8','9'},
      {'*','0','#'}
};

byte rowPins[ROWS] = {8, 7, 6, 5}; //connect to the row pinouts of the keypad
byte colPins[COLS] = {4, 3, 2}; //connect to the column pinouts of the keypad

Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );
int numbersCount;
char keyLock[3] = {'1','2','9'};
char myKey[3];

void setup(){
  Serial.begin(9600);
  pinMode (LED_PIN, OUTPUT);
  digitalWrite ( LED_PIN, LOW );
  numbersCount = 0;
}

void loop(){
  char key = keypad.getKey();
  
  if ( key != NO_KEY ){
    Serial.write("current key = ");
    Serial.println(key);
    if ( numbersCount < 3 ) {
      myKey[numbersCount]=key;
      numbersCount++;
    }
   
    if (key == '*'){
//      Serial.println ( '*' );
      numbersCount = 0; 
      myKey[0] = ' ';
      myKey[1] = ' ';
      myKey[2] = ' ';
    }
  
    Serial.write(" myKey = ");
    Serial.print( myKey[0] );
    Serial.print( myKey[1] );
    Serial.print( myKey[2] );
    Serial.println ();
    
    if ( key == '#' ) {
      if ( myKey[0] == keyLock[0] && 
      myKey[1] == keyLock[1] && 
      myKey[2] == keyLock[2] ) 
      {
        digitalWrite ( LED_PIN, HIGH ); 
        delay ( 5000 );
        digitalWrite ( LED_PIN, LOW );
      }
    }
  }
}
