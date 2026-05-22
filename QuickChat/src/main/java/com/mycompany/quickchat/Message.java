package com.mycompany.quickchat;

import java.util.Random;

public class Message {

    private static int totalMessages = 0;

    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(String recipient, String message) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        totalMessages++;
        this.numMessagesSent = totalMessages;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long number =
                1000000000L
                        + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash() {

        String[] words = message.trim().split("\\s+");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + numMessagesSent
                + ":"
                + firstWord
                + lastWord;
    }

    public String SentMessage(String option) {

        switch (option.toLowerCase()) {

            case "send":
                return "Message successfully sent.";

            case "store":
                storeMessage();
                return "Message successfully stored.";

            case "discard":
                return "Press 0 to delete the message.";

            default:
                return "Invalid option selected.";
        }
    }

    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    public void storeMessage() {

        try {

            java.io.FileWriter writer =
                    new java.io.FileWriter(
                            "stored_messages.json",
                            true
                    );

            writer.write("{\n");
            writer.write("\"MessageID\":\""
                    + messageID
                    + "\",\n");

            writer.write("\"Recipient\":\""
                    + recipient
                    + "\",\n");

            writer.write("\"Message\":\""
                    + message
                    + "\",\n");

            writer.write("\"MessageHash\":\""
                    + messageHash
                    + "\"\n");

            writer.write("}\n");

            writer.close();

        } catch (Exception e) {

            System.out.println(
                    "Error storing message: "
                            + e.getMessage()
            );
        }
    }
}