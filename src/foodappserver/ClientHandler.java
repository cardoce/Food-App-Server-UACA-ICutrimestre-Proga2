/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodappserver;

import Classes.Authentication;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author fernandocardoce
 */
public class ClientHandler extends Thread {
    

    private final Socket s;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    Authentication auth = new Authentication();

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }


    public void run() 
    {
        String username;
        String password;
        String received;
        String toreturn;
        while (!s.isClosed()) 
        {
            try {
 

                 
                // receive the answer from client
                received = dis.readUTF();

                switch (received) {
                    case "LogIn":
                        username = dis.readUTF();
                        password = dis.readUTF();
                        if (auth.logIn(username, password)) {
                            toreturn = "true";
                        } else {
                            toreturn = "false";
                        }
                        dos.writeUTF(toreturn);
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        break;
                }
                
                 
                // write on output stream based on the
                // answer from the client
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
