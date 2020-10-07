package com.zur.server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ChatParticipant {
    private List<PrintWriter> chatParticipants = new ArrayList<>();

    public void addParticipant(PrintWriter participant) {
        chatParticipants.add(participant);
    }

    public List<PrintWriter> getChatParticipants() {
        return chatParticipants;
    }
}
