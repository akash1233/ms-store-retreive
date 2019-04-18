#!/bin/sh

SL_FOLDER="."

echo "** Restoring POM\n"
java -jar $SL_FOLDER/sl-build-scanner.jar -restore -workspacepath ".."

echo "** SL - Updating POM\n"
java -jar $SL_FOLDER/sl-build-scanner.jar -pom -configfile sl-mvn-configuration.json -workspacepath ".."

echo "\n\n** SL - End of POM update script\n"
