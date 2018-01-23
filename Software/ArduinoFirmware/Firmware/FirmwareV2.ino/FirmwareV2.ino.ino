#include <SoftwareSerial.h>
 
SoftwareSerial mySerial(10, 11); // RX pin on arduino pin 10, TX pin on arduino pin 11
boolean commandReceived = false;
byte commandBuff[64];
int commandLength;

void setup() {
  mySerial.begin(9600);

}

void loop() {
  pollCommand();
  executeCommand();

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
  }
}

void executeCommand(){
  switch(commandBuffer[0]){
    case 10: commandYPR();
             break;
    case 11: commandStreamYPR();
             break;
    case 12: commandLedOn();
             break;
    case 13: commandLedOff();
             break;
  }
  commandReceived = false;
}

void commandYPR(){
  
}

void commandStreamYPR(){
  
}

void commandLedOn(){
  
}

void commandLedOff(){
  
}

