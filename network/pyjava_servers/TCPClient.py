#!/usr/bin/python
#TCPClient.py
from socket import *
serverName = "127.0.0.1"
serverPort = 7777
clientSocket = socket(AF_INET, SOCK_STREAM)

clientSocket.connect((serverName,serverPort))
sentence = input("Input lowercase sentence: ")
clientSocket.send((sentence+'\n').encode())
modifiedSentence = clientSocket.recv(1024)
print("From  Server: ", modifiedSentence.decode('utf-8'))
clientSocket.close()

