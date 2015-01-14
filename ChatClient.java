/**
 * Created by Vlad on 1/13/2015.
 */
import java.io.*;
import java.net.*;



public class ChatClient {
    StringBuffer incoming;
    StringBuffer outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        String incoming;
        String outgoing;
        ChatClient client = new ChatClient();
        client.go();
    }

        public void go() {
            setUpNetworking();
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();

        }
    private void setUpNetworking() {

        try {
            sock = new Socket("127.0.0.1", 3333);
            InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine())!= null) {
                    System.out.println("read " + message) ;
                    incoming.append(message + "\n");

                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

