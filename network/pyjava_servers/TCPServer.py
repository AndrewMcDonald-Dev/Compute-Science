#!/usr/bin/python
#TCPServer.py
from socket import *
serverPort = 7777
serverSocket = socket(AF_INET,SOCK_STREAM)
serverSocket.bind(("",serverPort))
serverSocket.listen(1)
print ("The TCP server is ready to receive ... ")

while 1:
    connectionSocket, addr = serverSocket.accept()
    sentence = connectionSocket.recv(1024).decode()
    print ("Received From Client: ", sentence)
    capitalizedSentence = sentence.upper()
    connectionSocket.send(capitalizedSentence.encode())
    print ("Sent back to Client: ", capitalizedSentence)
    connectionSocket.close()

