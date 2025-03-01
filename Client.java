import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client{
    public static void main(String [] args){
        Scanner scanner =new Scanner(System.in);
        System.out.println("entrer le mode de conversion");
        String modeconversion = scanner.nextLine().trim().toLowerCase();
        System.out.println("entrer votre montant : ");
        double montant =Double.parseDouble(scanner.nextLine()); 
        int choix = 0;
        String adresse = "localhost";
        int port = 1234;
        while(choix == 0){
            switch(modeconversion){
                case "dollar vers mad":
                    choix = 1;
                    break;
                case "mad vers dollar":
                    choix = 2;
                    break;
                default:
                    continue;
            }
        }
        try(Socket socket = new Socket(adresse,port)){
            PrintWriter PW = new PrintWriter(socket.getOutputStream());
            PW.println(choix);
            PW.flush();
            BufferedReader BR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = BR.readLine();
            System.out.println("serveur: "+data);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}