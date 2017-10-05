SHARED_FOLDER=$1
cd src
javac server/shared/PresenceService.java server/shared/RegistrationInfo.java
jar cvf ../output/presence.jar server/shared/*.class
mv ../output/presence.jar $SHARED_FOLDER/.
cd ..
