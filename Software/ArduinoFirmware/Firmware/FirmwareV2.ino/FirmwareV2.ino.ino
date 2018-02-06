#include <SoftwareSerial.h>
 

SoftwareSerial mySerial(10, 11); // RX pin on arduino pin 10, TX pin on arduino pin 11

#define LED_RED 3
#define LED_GREEN 5
#define LED_BLUE 6
#define BATTERY_PIN 3

boolean commandReceived = false;
byte commandBuff[64];
int commandLength;

boolean streamYPR = false;
byte batteryState;

void setup() {
  pinMode(LED_RED, OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_BLUE, OUTPUT);

  analogWrite(LED_BLUE, 255);
  mySerial.begin(9600);
  Serial.begin(38400);
    while (!Serial);
  Serial.println("Serial OK");

}

void loop() {
  pollData();
  pollCommand();
  executeCommand();
  stream();
}


void pollData(){
  batteryState = batteryStateValue(analogRead(BATTERY_PIN));
}

// Empty the Serial buffer and fill the commandBuffer
void pollCommand(){
  if(mySerial.available()){
    int i = 0;
      Serial.print("length :");
      Serial.println(commandLength);
      while(mySerial.available()){
        byte received = mySerial.read();
        commandBuff[i] = received;
        Serial.print(commandBuff[i]);
        Serial.print(" ");
        i++;
      }
    Serial.println("|");
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
    case 13: commandBatteryState();
             break;
    default: break;
  }
  commandReceived = false;
}

// ====================================================================
// ========================= Commands =================================
// ====================================================================

void commandYPR(){
  Serial.println("Command: RequestYPR");
  byte ln = commandBuff[1];
  byte callbackId[2] = {commandBuff[2], commandBuff[3]};
  byte yaw[4];
  byte pitch[4];
  byte roll[4];
  float2Bytes(1.23, yaw);
  float2Bytes(4.56, pitch);
  float2Bytes(7.89, roll);
  byte response[15];
  response[0] = commandBuff[0];
  response[1] = 16;
  response[2] = commandBuff[2];
  response[3] = commandBuff[3];
  response[4] = yaw[0];
  response[5] = yaw[1];
  response[6] = yaw[2];
  response[7] = yaw[3];
  response[8] = pitch[0];
  response[9] = pitch[1];
  response[10] = pitch[2];
  response[11] = pitch[3];
  response[12] = roll[0];
  response[13] = roll[1];
  response[14] = roll[2];
  response[15] = roll[3];
  sendResponse(response, 16);
}

void commandStreamYPR(){
  Serial.println("Command: streamYPR");
  byte activeStream = commandBuff[2];
  if (activeStream) {
    streamYPR = true;
  } else {
    streamYPR = false;
  }
}

void commandBatteryState() {
  Serial.println("Command: BatteryState");
  byte response[5];
  response[0] = commandBuff[0];
  response[1] = 5;
  response[2] = commandBuff[2];
  response[3] = commandBuff[3];
  response[4] = batteryState;
  sendResponse(response, 5);
}

void commandLedOn(){
  Serial.println("Command: ledOn");
  byte red = commandBuff[3];
  byte green = commandBuff[4];
  byte blue = commandBuff[5];
  analogWrite(LED_RED, red);
  analogWrite(LED_GREEN, green);
  analogWrite(LED_BLUE, blue);
}

void stream(){
  
}

byte batteryStateValue(int sensorValue){
  float voltage = sensorValue * (5.0 / 1023.0);
  if (voltage < 3.4) {
    return 0;
  } else if (voltage > 4.2) {
    return 100;
  } else {
    float percentage = (( voltage - 3.4 ) / 0.8) * 100;
    return (unsigned byte) (percentage);
  }
}

void sendResponse(byte* response, int len){
   Serial.print("Sending response:");
  for(int i = 0; i < len; i++) {
    Serial.print(" ");
    Serial.print(response[i]);
    mySerial.write(response[i]);
  }
  Serial.print(" " + len);
  Serial.println(" bytes sent!");
}


// ==========================================================================
// ========================= Type conversions ===============================
// ==========================================================================

// Type to byte array
void float2Bytes(float val,byte* bytes_array){
  // Create union of shared memory space
  union {
    float float_variable;
    byte temp_array[4];
  } u;
  // Overite bytes of union with float variable
  u.float_variable = val;
  // Assign bytes to input array
  memcpy(bytes_array, u.temp_array, 4);
}


