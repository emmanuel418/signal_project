package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
/**
 * A TCP output strategy for sending cardio data to a client over a network connection.
 * This class follows TCP networking protocols, meaning it establishes a TCP server socket and waits for client connections.
 * When a client connects, it sends the cardio data to the client.
 * The TCP connection is terminated after each data transmission
 */

public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * this method creates a new TcpOutputStrategy that listens on the specified port for incoming connections.
     *
     * @param port This is the port on which the server socket will listen for incoming connections.
     */
    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs cardio data for a patient over the TCP connection.
     *
     * @param patientId The ID of the patient.
     * @param timestamp The timestamp of the cardio reading.
     * @param label     The label with the cardio reading.
     * @param data      The data of the cardio reading.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}
