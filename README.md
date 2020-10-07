# Chat Application Server
<p>The project represents the server side of a multi-user chat application.</p>

 ## Table of Contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Sources](#sources)

## General info
<p>The project has been created to support the server side of a multi-user chat application. 
It works in cooperation with another project from my github profile - Chat Application Client.</p>

## Technologies
<p>
    <li>Java 8+</li>
    <li>Maven</li>
    <li>The application has been created with usage of the SocketServer class, the Runnable interface and 
    the ExecutorService class.</li>
</p>

## Setup
<p>Go to chat-application-server/src/main/java directory.<br>
Run the commands:</p>

    javac com/zur/RunServer.java
    java com.zur.RunServer
    
## Features
<p>
<ol>
    <li>Possibility to connect a few users at the same time.</li>
    <li>Receiving and sending messages between chat users.</li>
    <li>Displaying a message when the client is connected or leaves the chat.</li>
    <li>Informing about broken connection.</li>
</ol>
</p>

## Sources
<p>The inspiration to create the application was simply curiosity how such an application works.</p>
