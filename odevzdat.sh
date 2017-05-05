#!/usr/bin/env bash

mkdir ./xbures29
mkdir ./xbures29/dest-client
mkdir ./xbures29/doc
mkdir ./xbures29/lib
cp ./lib/get-libs.sh ./xbures29/lib
cp ./rozdeleni.txt ./xbures29/
cp ./build.xml ./xbures29/
cp ./readme.txt ./xbures29/
cp -r ./examples ./xbures29/
cp -r ./src ./xbures29/
zip -r xbures29.zip  ./xbures29
rm -r ./xbures29
rm -r ./testFolder
mkdir ./testFolder
mv ./xbures29.zip ./testFolder
cd ./testFolder
unzip ./xbures29.zip
cd ./xbures29/lib
./get-libs.sh
cd ../
ant run


