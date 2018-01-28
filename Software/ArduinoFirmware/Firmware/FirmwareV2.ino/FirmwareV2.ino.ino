#include <SoftwareSerial.h>
 

SoftwareSerial mySerial(10, 11); // RX pin on arduino pin 10, TX pin on arduino pin 11

#define LED_RED 3
#define LED_GREEN 5
#define LED_BLUE 6

boolean commandReceived = false;
byte commandBuff[64];
int commandLength;

boolean streamYPR = false;

void setup() {
  pinMode(LED_RED, OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_BLUE, OUTPUT);

  mySerial.begin(9600);

}

void loop() {
  pollCommand();
  executeCommand();
  stream();
}

// Empty the Serial buffer and fill the commandBuffer
void pollCommand(){
  int commandLength = mySerial.available();
  if(commandLength){
    for(int i = 0; i < commandLength; i++) {
      byte received = mySerial.read();
      commandBuff[i] = received;
    }
    commandReceived = true;
  } else {
    commandReceived = false;
  }
}

void executeCommand(){
  if(!commandReceived){ return; }
  switch(commandBuff[0]){
    case 10: commandYPR();
             break;
    case 11: commandStreamYPR();
             break;
    case 12: commandLedOn();
             break;
    case 13: commandLedOff();
             break;
    default: break;
  }
  commandReceived = false;
}

void commandYPR(){
  byte response[9];
}

void commandStreamYPR(){
  byte activeStream = commandBuff[2];
  if (activeStream) {
    streamYPR = true;
  } else {
    streamYPR = false;
  }
}

void commandLedOn(){
  byte red = commandBuff[3];
  byte green = commandBuff[4];
  byte blue = commandBuff[5];
  analogWrite(LED_RED, red);
  analogWrite(LED_GREEN, green);
  analogWrite(LED_BLUE, blue);
}

void commandLedOff(){
  analogWrite(LED_RED, 0);
  analogWrite(LED_GREEN, 0);
  analogWrite(LED_BLUE, 0);
}

void stream(){
  
}

