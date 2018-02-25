#!/bin/bash
rm tmp.wav
touch tmp.wav
pico2wave -l=fr-FR -w=tmp.wav "$1"
aplay tmp.wav