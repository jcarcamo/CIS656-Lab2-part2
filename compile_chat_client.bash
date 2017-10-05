SHARED_FOLDER=$1
cd src
javac -cp $SHARED_FOLDER/presence.jar client/utils/Utils.java client/service/ProcessIncomingRequest.java client/service/MessageListenerService.java client/service/MessageSenderService.java client/ChatClient.java
cd ..
