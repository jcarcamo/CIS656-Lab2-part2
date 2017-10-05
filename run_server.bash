CP=$1
SHARED_FP=$2
URL=$3
RMI=$4
cd src
java -cp $CP:$SHARED_FP/presence.jar -Djava.rmi.server.codebase=$URL -Djava.rmi.server.hostname=$RMI -Djava.security.policy=server/server.policy server.service.PresenceServiceImpl
cd ..
