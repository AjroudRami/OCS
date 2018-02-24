#!/usr/bin/env bash
echo "deb http://ftp.de.debian.org/debian stretch-backports main " >> /etc/apt/sources.list
apt-get update
apt-get install git -y
apt-get install oracle-java8-jdk -y
apt-cache search maven
apt-get install maven -y
apt-get install bluetooth bluez blueman -y