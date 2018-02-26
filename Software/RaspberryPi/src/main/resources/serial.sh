#!/usr/bin/env bash

sudo rfcomm release rfcomm1
sudo rfcomm bind /dev/rfcomm1 98:D3:36:80:EF:EE 1