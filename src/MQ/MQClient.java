package MQ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
public class MQClient {
    public static void Produce(String message)throws Exception{
        Socket socket =new Socket(InetAddress.getLocalHost(),BrokerSever.SERVICE_PORT);
        try(PrintWriter out=new PrintWriter(socket.getOutputStream())){
            out.println(message);
            out.flush();
        }
    }
    public static String consume()throws Exception{
        Socket socket=new Socket(InetAddress.getLocalHost(),BrokerSever.SERVICE_PORT);
        try(BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out=new PrintWriter(socket.getOutputStream())){
            out.println("CONSUME");
            out.flush();
            String message=in.readLine();
            return message;
        }

    }

}
