# CIS656-Lab2-part2

This implementation will simply have a text interface, like the early “talk” program on Unix that existed long before conventional IM solutions. It’s also simple in the sense that we won’t be supporting any sort of friend list, or authentication. In other words, every user becomes visible (and hence a friend) of every other user on the system as soon as they successfully register. There is also no user authentication, and user names are available on a first-come-first-served basis. When a user leaves the system, another user could claim the name.

# Usage

* friends – When this command is entered, the client determines (via the PresenceService) which users are registered with the presence server and prints out the users’ names and whether they are available or not available (via the status field in the registration information).

* talk {username} {message} - When this command is entered, the client 1) first checks to see if the user is present and available (via the PresenceService). 2) If the user is registered and available, a connection to the target client is established,based on their registration info, and they are sent the given message. Note that when a client receives a message, it will simply print it out to the console, and re-prompt the user to enter his/her next command. (i.e. you will need multiple threads of execution here within your client process.)

* broadcast {message} – The client broadcasts the message to every user that is currently registered and available. Clients should NOT broadcast the message to themselves.

* busy – The client updates its registration with the presence server, indicating it is not currently available. If the client is already in not available when this command is entered, nothing needs to be done, though it would be good to prompt the user and indicate they already are not available. A client that is busy should not receive any messages whether they be sent with the talk or the broadcast command.

* available – The client updates its registration information with the presence server, indicating it is now available. If the client is already available when this command is entered, nothing needs to be done, though it would be good to prompt the user and indicate they are already registered as available.

* exit – When this command is entered, the ChatClient will unregister itself with the PresenceService and terminate.

## How to build
Run the following scripts

```bash
bash shared_jar_creation.bash <shared_folder_location>
bash compile_server.bash <shared_folder_location>
bash compile_client.bash <shared_folder_location>
```

## How to run
Modify your ```.security``` files to match the absolute path to src folder in this repo

For example:

```
grant codeBase "file:<path_to_repo>/src/" {
    permission java.security.AllPermission;
};
```

Then run the following scripts in one terminal:

```bash
bash run_rmiregistry.bash
bash run_server.bash <absolute path to src> <shared_folder_location> <url_to_shared_codebase> <rmi_hostname>
```

And in other terminals (as many as chat clients you want) run:

```bash
bash run_client.bash <absolute path to src> <shared_folder_location> <url_to_shared_codebase> <nickname> [<rmi_hostname> [<rmi_port>]]
```

Note that if you don't provide an rmi_hostname and rmi_port it will default to localhost 1099.
