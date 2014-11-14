#include <SPI.h>
#include <MFRC522.h>

#define RST_PIN 9
#define SS_PIN 10

#define LED_PIN 8

boolean need_compare = true;
boolean is_coincided = true;

byte exit_uid[4] = { 0x03, 0x4F, 0xCB, 0xF7 };

MFRC522 mfrc522 ( SS_PIN, RST_PIN );

void setup () {
  pinMode ( LED_PIN, OUTPUT );
  digitalWrite ( LED_PIN, LOW );
  Serial.begin ( 9600 );
  SPI.begin();
  mfrc522.PCD_Init();
}

void loop () {
  if ( !mfrc522.PICC_IsNewCardPresent() ) {
    return;
  }
  
  if ( !mfrc522.PICC_ReadCardSerial() ){
    return;
  }
  
  if ( need_compare ) {
    for ( byte i = 0; i < mfrc522.uid.size; i++ ) {
      //Serial.print ( mfrc522.uid.uidByte[i], HEX );
      if ( mfrc522.uid.uidByte[i] != exit_uid[i] ) {
        is_coincided = false;
      }
      if ( i == mfrc522.uid.size - 1 ) {
        need_compare = false;
      }
    }
  }
  
  if ( is_coincided ) {
    digitalWrite ( LED_PIN, HIGH );
    delay ( 3000 );
    digitalWrite ( LED_PIN, LOW );
  }
}
