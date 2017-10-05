SHARED_FOLDER=$1
cd src
javac -cp $SHARED_FOLDER/presence.jar server/service/PresenceServiceImpl.java
cd ..
