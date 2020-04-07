#!/bin/bash

arr=$(find . -name  "*.java")

function replacePrivate(){
  save=$(awk '{gsub("private", "");print}' $1)
  echo -e "${save}" > $1
}

function replaceAllPrivateMethods() {
 for i in $arr;
    do
      replacePrivate $i
    done
}
replaceAllPrivateMethods

#for x in $arr; do echo fsdf $x; done;
#
#echo $arr
#create_cnames "${arr[@]}"



