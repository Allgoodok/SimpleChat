/**
 * Created by Vlad on 1/13/2015.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    ArrayList clientOutputStreams;

44

    public static class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            new ChatServer().go();
        }

        public void go(){
            clientOutputStreams = new ArrayList()
        }

        private void tellEveryone(String message) {
            Iterator it = clientOutputStreams.iterator();

            while(it.hasNext()) {
                try {
                    PrintWriter writer = (PrintWriter) it.next();
                    writer.println(message);
                    writer.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}




