# Encrypted_messaging_DE_2.0

A locally hosted encrypted messaging application built with **JavaFX** and **Java Networking**.

JavaFXEncryptor 2.0 demonstrates the core architecture behind a modern messaging application, including user authentication, client-server communication, encrypted message transmission, chat history management, and a graphical user interface.

The project was created to explore how messaging systems work internally, from user management and networking to encryption and UI design.

---

# Features

## User Authentication

- User registration system
- Login system
- Local user database storage
- Session management

Users can create accounts and securely access their own chat sessions.

---

## Encrypted Messaging

JavaFXEncryptor uses a custom Caesar-based encryption algorithm created specifically for this project.

Features:

- Message encryption before transmission
- Message decryption when received
- Encrypted packets sent through the network
- Original messages are only displayed locally

The encryption system was manually implemented without using external encryption libraries.

---

## Client-Server Architecture

The application uses a local client-server communication model.

Architecture:

```
Client A
   |
   |
   ↓
Server
   |
   |
   ↓
Client B
```

The server:

- Handles incoming connections
- Tracks online users
- Routes messages to the correct recipient

The clients:

- Send encrypted messages
- Receive encrypted messages
- Display decrypted conversations

---

# User Interface

The application uses **JavaFX** to create a desktop messaging interface.

Current UI features:

- Dark themed interface
- Login and registration windows
- Online user sidebar
- User selection system
- Chat bubbles
- Message input area
- Message timestamps
- Clear history functionality


The interface was designed to resemble modern messaging applications while keeping the implementation fully custom.

---

# Project Structure

```
JavaFXEncryptor 2.0

src
│
├── crypto
│   └── CryptoUtils.java
│
├── models
│   ├── User.java
│   ├── Message.java
│   ├── MessageType.java
│   ├── MessageStatus.java
│   ├── Session.java
│   └── ChatPacket.java
│
├── network
│   ├── Client.java
│   └── Server.java
│
├── storage
│   ├── UserManager.java
│   └── HistoryManager.java
│
└── ui
    ├── Main.java
    ├── LoginWindow.java
    ├── RegisterWindow.java
    ├── ChatWindow.java
    │
    └── components
        ├── MessageBubble.java
        └── UserCard.java
```

---

# Technologies Used

## Programming Language

- Java

## GUI Framework

- JavaFX 26.0.1

## Networking

- Java Socket Programming
- TCP communication

## Data Storage

- Local file storage
- Text-based databases

## Development Environment

- IntelliJ IDEA / VS Code
- macOS Terminal
- Java Development Kit

---

# How It Works

## 1. User Login

When a user logs in:

```
Username + Password
        |
        ↓
UserManager checks database
        |
        ↓
Session stores active user
        |
        ↓
Chat window opens
```

---

## 2. Sending Messages

When a message is sent:

```
User writes message

        ↓

Message encrypted

        ↓

Packet created

        ↓

Sent to server

        ↓

Server forwards packet

        ↓

Receiver decrypts message
```

---

# Installation

## Requirements

Before running the project, install:

- Java JDK 26
- JavaFX SDK 26.0.1


JavaFX can be downloaded from:

https://gluonhq.com/products/javafx/

---

# Running The Project

Clone the repository:

```bash
git clone YOUR_REPOSITORY_LINK
```

Move into the project:

```bash
cd JavaFXEncryptor
```

---

## Compile

Delete previous compiled files:

```bash
find src -name "*.class" -delete
```

Compile:

```bash
javac \
--module-path javafx-sdk-26.0.1/lib \
--add-modules javafx.controls,javafx.fxml \
$(find src -name "*.java")
```

---

# Start The Server

Open a terminal:

```bash
java \
-cp src \
network.Server
```

The server should display:

```
Server running...
```

---

# Start The Client

Open another terminal:

```bash
java \
--module-path javafx-sdk-26.0.1/lib \
--add-modules javafx.controls,javafx.fxml \
-cp src \
ui.Main
```

Multiple clients can connect to the same server.

---

# Data Storage

The application automatically creates:

```
users/
    users.txt


history/
    username_receiver.txt
```

Users and conversations are stored locally.

---

# Future Improvements

Possible future upgrades:

## Networking

- Remote server hosting
- Internet communication
- User IP management
- Online status synchronization

## Security

- Stronger encryption algorithms
- Password hashing
- Secure key exchange

## Features

- Image messaging
- File sharing
- Profile pictures
- Message reactions
- Notifications
- Read receipts

## Deployment

- macOS `.dmg` installer
- Windows executable
- Linux package

---

# Learning Goals

This project was created to understand:

- How messaging applications work internally
- Client-server architecture
- Java networking
- GUI development
- Encryption fundamentals
- Data persistence
- Software architecture design

---

# Author

**Doruk Ersoy**

Computer Science student interested in:

- Software Development
- Cybersecurity
- Artificial Intelligence
- Networking

---

# License

This project is licensed for educational and personal use.
