/* ============================================
I2Cdev device library code is placed under the MIT license
Copyright (c) 2012 Jeff Rowberg

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
===============================================
*/
// I2Cdev and MPU6050 must be installed as libraries, or else the .cpp/.h files
// for both classes must be in the include path of your project
#include "I2Cdev.h"

#include "MPU6050_6Axis_MotionApps20.h"
//#include "MPU6050.h" // not necessary if using MotionApps include file

// Arduino Wire library is required if I2Cdev I2CDEV_ARDUINO_WIRE implementation
// is used in I2Cdev.h
#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
    #include "Wire.h"
#endif

#include <SoftwareSerial.h>
 
#define LED_RED 3
#define LED_GREEN 5
#define LED_BLUE 6
#define BATTERY_PIN 3
#define GYRO_INT 2

SoftwareSerial mySerial(10, 11); // RX pin on arduino pin 10, TX pin on arduino pin 11

boolean commandReceived = false;
byte commandBuff[64];
int commandLength;

boolean streamYPR = false;
byte batteryState;
float yaw;
float pitch;
float roll;

MPU6050 mpu;

// MPU control/status vars
bool dmpReady = false;  // set true if DMP init was successful
uint8_t mpuIntStatus;   // holds actual interrupt status byte from MPU
uint8_t devStatus;      // return status after each device operation (0 = success, !0 = error)
uint16_t packetSize;    // expected DMP packet size (default is 42 bytes)
uint16_t fifoCount;     // count of all bytes currently in FIFO
uint8_t fifoBuffer[64]; // FIFO storage buffer

// orientation/motion vars
Quaternion q;           // [w, x, y, z]         quaternion container
VectorFloat gravity;    // [x, y, z]            gravity vector
float ypr[3];           // [yaw, pitch, roll]   yaw/pitch/roll container and gravity vector


// ================================================================
// ===               INTERRUPT DETECTION ROUTINE                ===
// ================================================================
volatile bool mpuInterrupt = false;     // indicates whether MPU interrupt pin has gone high
void dmpDataReady() {
    mpuInterrupt = true;
}


void setup() {
  Serial.begin(38400);
    //while (!Serial);
  Serial.println("Serial OK");
  
  pinMode(LED_RED, OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_BLUE, OUTPUT);

  analogWrite(LED_BLUE, 255);

  println(F("Initializing Bluetooth Software Serial Connection"));
  mySerial.begin(9600);
  println(F("Bluetooth Software Serial OK"));

  setupMPU();

}

void loop() {
  
  pollData();
  pollCommand();
  executeCommand();
  stream();
  delay(20);
}


void pollData(){
  batteryState = batteryStateValue(analogRead(BATTERY_PIN));
  pollMPU();
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
  byte yawB[4];
  byte pitchB[4];
  byte rollB[4];
  float2Bytes(yaw, yawB);
  float2Bytes(pitch, pitchB);
  float2Bytes(roll, rollB);
  byte response[15];
  response[0] = commandBuff[0];
  response[1] = 16;
  response[2] = commandBuff[2];
  response[3] = commandBuff[3];
  response[4] = yawB[0];
  response[5] = yawB[1];
  response[6] = yawB[2];
  response[7] = yawB[3];
  response[8] = pitchB[0];
  response[9] = pitchB[1];
  response[10] = pitchB[2];
  response[11] = pitchB[3];
  response[12] = rollB[0];
  response[13] = rollB[1];
  response[14] = rollB[2];
  response[15] = rollB[3];
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
  Serial.print("sensor state: ");
  Serial.println(analogRead(BATTERY_PIN));
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
   Serial.print("Sending response: ");
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

void setupMPU(){
      // join I2C bus (I2Cdev library doesn't do this automatically)
    #if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
        Wire.begin();
        TWBR = 24; // 400kHz I2C clock (200kHz if CPU is 8MHz)
    #elif I2CDEV_IMPLEMENTATION == I2CDEV_BUILTIN_FASTWIRE
        Fastwire::setup(400, true);
    #endif

    // initialize device
    Serial.println(F("Initializing I2C MPU..."));
    mpu.initialize();

    // verify connection
    Serial.println(F("Testing MPU connections..."));
    Serial.println(mpu.testConnection() ? F("MPU6050 connection successful") : F("MPU6050 connection failed"));

    // load and configure the DMP
    Serial.println(F("Initializing DMP (MPU)..."));
    devStatus = mpu.dmpInitialize();

    // supply your own gyro offsets here, scaled for min sensitivity
    mpu.setXGyroOffset(220);
    mpu.setYGyroOffset(76);
    mpu.setZGyroOffset(-85);
    mpu.setZAccelOffset(1788); // 1688 factory default for my test chip

    // make sure it worked (returns 0 if so)
    if (devStatus == 0) {
        // turn on the DMP, now that it's ready
        Serial.println(F("Enabling DMP (MPU)..."));
        mpu.setDMPEnabled(true);

        // enable Arduino interrupt detection
        Serial.println(F("Enabling MPU interrupt detection (Arduino external interrupt pin 2)..."));
        attachInterrupt(digitalPinToInterrupt(GYRO_INT), dmpDataReady, RISING);
        mpuIntStatus = mpu.getIntStatus();

        // set our DMP Ready flag so the main loop() function knows it's okay to use it
        Serial.println(F("DMP ready! Waiting for MPU first interrupt..."));
        dmpReady = true;
        Serial.println(F("MPU Ready to use!"));
        // get expected DMP packet size for later comparison
        packetSize = mpu.dmpGetFIFOPacketSize();
    } else {
        // ERROR!
        // 1 = initial memory load failed
        // 2 = DMP configuration updates failed
        // (if it's going to break, usually the code will be 1)
        Serial.print(F("DMP Initialization failed (code "));
        Serial.print(devStatus);
        Serial.println(F(")"));
    }
}

void pollMPU(){
  // if programming failed, don't try to do anything
    if (!dmpReady) return;

    // wait for MPU interrupt or extra packet(s) available
    if (!mpuInterrupt && fifoCount < packetSize) return;

    // reset interrupt flag and get INT_STATUS byte
    mpuInterrupt = false;
    mpuIntStatus = mpu.getIntStatus();

    // get current FIFO count
    fifoCount = mpu.getFIFOCount();

    // check for overflow (this should never happen unless our code is too inefficient)
    if ((mpuIntStatus & 0x10) || fifoCount == 1024) {
        // reset so we can continue cleanly
        mpu.resetFIFO();
        Serial.println(F("FIFO overflow!"));

    // otherwise, check for DMP data ready interrupt (this should happen frequently)
    } else if (mpuIntStatus & 0x02) {
        // wait for correct available data length, should be a VERY short wait
        while (fifoCount < packetSize) fifoCount = mpu.getFIFOCount();

        // read a packet from FIFO
        mpu.getFIFOBytes(fifoBuffer, packetSize);
        
        // track FIFO count here in case there is > 1 packet available
        // (this lets us immediately read more without waiting for an interrupt)
        fifoCount -= packetSize;
        
        mpu.dmpGetQuaternion(&q, fifoBuffer);
        mpu.dmpGetGravity(&gravity, &q);
        mpu.dmpGetYawPitchRoll(ypr, &q, &gravity);
        yaw = ypr[0] * 180/M_PI;
        pitch = ypr[1] * 180/M_PI;
        roll = ypr[2] * 180/M_PI;
    }
}

