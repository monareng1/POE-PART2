package com.mycompany.quickchat;

import javax.swing.JOptionPane;

public class QuickChat {

    public static void main(String[] args) {

        int login = JOptionPane.showConfirmDialog(
                null,
                "Have you logged in successfully?",
                "Login",
                JOptionPane.YES_NO_OPTION
        );

        if (login != JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(
                    null,
                    "You must log in first."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "Welcome to QuickChat."
        );

        int numberOfMessages =
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "How many messages would you like to send?"
                        )
                );

        int count = 0;

        while (count < numberOfMessages) {

            String menu =
                    "Choose an option:\n"
                    + "1. Send Messages\n"
                    + "2. Show recently sent messages\n"
                    + "3. Quit";

            int choice =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(menu)
                    );

            switch (choice) {

                case 1:

                    String recipient =
                            JOptionPane.showInputDialog(
                                    "Enter recipient number (+27xxxxxxxxx):"
                            );

                    String messageText =
                            JOptionPane.showInputDialog(
                                    "Enter message (max 250 chars):"
                            );

                    if (messageText.length() > 250) {

                        int excess =
                                messageText.length() - 250;

                        JOptionPane.showMessageDialog(
                                null,
                                "Message exceeds 250 characters by "
                                + excess
                        );

                        break;
                    }

                    JOptionPane.showMessageDialog(
                            null,
                            "Message ready to send."
                    );

                    Message msg =
                            new Message(
                                    recipient,
                                    messageText
                            );

                    JOptionPane.showMessageDialog(
                            null,
                            msg.checkRecipientCell()
                    );

                    JOptionPane.showMessageDialog(
                            null,
                            "Hash: "
                            + msg.createMessageHash()
                    );

                    String option =
                            JOptionPane.showInputDialog(
                                    "Choose:\nSend\nStore\nDiscard"
                            );

                    JOptionPane.showMessageDialog(
                            null,
                            msg.SentMessage(option)
                    );

                    JOptionPane.showMessageDialog(
                            null,
                            msg.printMessages()
                    );

                    count++;
                    break;

                case 2:

                    JOptionPane.showMessageDialog(
                            null,
                            "Coming Soon."
                    );

                    break;

                case 3:

                    System.exit(0);
                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid choice."
                    );
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "Total messages sent: "
                + Message.returnTotalMessages()
        );
    }
}
