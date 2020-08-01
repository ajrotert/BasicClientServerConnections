# Preview
Simple Speedometer Plus Map                 |  Desktop App
:-------------------------:|:-------------------------:
![alt text 1](https://github.com/ajrotert/BasicClientServerConnections/blob/master/ClientHostMessage.gif?raw=true)  |   Java Jar Files


PROJECT TITLE: Basic Client Server Connection using JAVA

PURPOSE OF PROJECT: Using java, explore theroies learned in Operating Systems

VERSION or DATE: 1.0.1

ABOUT:
A client application can connect to a host applications using sockets.

HOW TO USE:
Download the Host.jar and Client.jar files. Any client can connect to the host by inputing the ip address that the host is running on. 




/************************************************* Command Line Version *************************************************/
ABOUT:

Server:
    Classes:
                Server:         This is the parent thread. Its main purpose is to find connections for new clients. It also will starts the server controls and Holds information about its self.
                ServerControls: This is a child thread. Its purpose is to read server side input, and handle a few functions.
                NewClient:      This is a child thread. Its purpose is to wait for a client to connect, and handle the client needs. This will also start the input and output on the server for a client.
                NewClientInput: This is a child thread. It will wait for input from a client, and print it off.
                NewClientOutput:This is a child thread. It will wait for input from a server and send it to its connected client.
Client
    Classes:
                Client:         This is a parent thread. Its main purpose is to connect to a server. It will start client controls and hold information about itself.
                ClientControls: This is a child thread. Its purpose is to read client side inpus and handle a few functions.
                ClientOutput:   This is a child thread. Its purpose is to wait for output from a client and send it to the server. 
                ClientInput:    This is a child thread. It will wait for output from a server and print it off.
HOW TO START THIS PROJECT:
    Download the server files on a host machine running java.
    Download the client files on a client machine running java. 
    Start the server files
    Locate IP address of the server, on the clients side run the program, and connect to the server IP address
AUTHORS: Andrew Rotert

EXPLAIN:
The server starts, clients connect to it. Clients can send messages to server, and server can send messages to clients

WHATS NEXT:
I completed my inital goal for this project. The next phase may be to make it a desktop application. 

# Project
Copyright © 2020 Andrew Rotert. All rights reserved.
#### Client-Server Chat
JavaFX working with basic server connections between a host and multiple clients.

- [ ] Published 
- [x] Completed


# Motivation
Idea formed from Operating Systems class.


# Tech
Built using Object-Oriented Design and Java.
