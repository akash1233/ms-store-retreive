#!/bin/sh

SL_FOLDER="."

#Start Test stage - commented to use Chrome extension
#java -jar $SL_FOLDER/sl-test-listener.jar start -tokenfile $SL_FOLDER/sltoken.txt -buildsessionidfile $SL_FOLDER/buildSessionId.txt -testStage "UAT"

# Marc's notes:
# 'sl.webappLocation' is needed only when app is launched outside tomcat - see SL doc (needs path to WAR)
# 'server.port' is needed here  because ports 80 and 8080 are used in my local machine
java -javaagent:$SL_FOLDER/sl-test-listener.jar -Dsl.webappLocation="../target" -Dsl.featuresData.enableHttpColoring=true -Dsl.log.toFile=true -Dsl.log.level=info -Dserver.port=8282 -jar ../target/store-retreive-0.0.1-SNAPSHOT.war

#End Test stage - commented to use Chrome extension
#java -jar $SL_FOLDER/sl-test-listener.jar end -tokenfile $SL_FOLDER/sltoken.txt -buildsessionidfile

