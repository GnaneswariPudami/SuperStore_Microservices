#!/bin/sh

echo Building Projects
pwd

SKIP_TESTS="true"
HAS_ERROR=0

buildModule(){
  if [ $HAS_ERROR == 1 ]
  then
    exit 0
  fi

  echo Building... $1
  cd $1
  echo $(pwd)
  mvn clean compile install $EXTRA $MVN_OPTS || HAS_ERROR=1
  cd ..
}

echo "Which module do you want to compile?:"
echo "1 - all"
echo "2 - Notifications Service"
echo "3 - Product Service"
echo "4 - UserAccount Service"
echo "5 - Order Service"

read -p "Please enter your choice [1 - ALL]: " optionChosen
MODULE_TO_COMPILE=${optionChosen:-1}

read -p "Skip Tests? [TRUE | false]: " skipTests
SKIP_TESTS=${skipTests:-true}

MVN_OPTS="-P-default -Duser.timezone=Europe/London -Duser.language=en -Duser.country=GB -Duser.variant=GB -Dfile.encoding=windows-1252 -Dproject.build.sourceEncoding=windows-1252 -Dmaven.test.skip=$SKIP_TESTS"

echo "Module to complile ${MODULE_TO_COMPILE}"
echo "MVN_OPTS = ${MVN_OPTS}"

echo pwd

cd ../..

case $MODULE_TO_COMPILE in
	1)
	  buildModule notifications
    buildModule product
    buildModule useraccount
    buildModule order
    ;;
  2)
    buildModule notifications
    ;;
  3)
    buildModule product
    ;;
  4)
    buildModule useraccount
    ;;
  5)
    buildModule order
    ;;
esac
wait

echo "Error Status: ${HAS_ERROR}"
echo Build complete, or Build script execution completed "$:=)"


