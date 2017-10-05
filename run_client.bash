CP=$1
SHARED_FILES=$2
URL=$3
NAME=$4
RMI=$5
PORT=$6
cd src
java -cp $CP:$SHARED_FILES/presence.jar -Djava.rmi.server.codebase=$URL -Djava.security.policy=client/client.policy client.ChatClient $NAME $RMI:$PORT
