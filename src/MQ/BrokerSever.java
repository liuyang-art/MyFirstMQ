package MQ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*public class BrokerSever implements Runnable {
    public static int SERVICE_PORT = 9999;
    private final Socket socket;

    public BrokerSever(Socket socket) {
        this.socket=socket;
    }
    public void run(){
        try( BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out=new PrintWriter(socket.getOutputStream()))
        {
            while(true){
                String str=in.readLine();
                if(str==null)
                    continue;
                System.out.println("接收到原始数据:"+str);
                if(str.equals("CONSUME")){
                    String message=Broker.consume();
                    out.println(message);
                    out.flush();
                }else
                    Broker.produce(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String []arg)throws Exception{
        ServerSocket server=new ServerSocket(SERVICE_PORT);
        while (true){
            BrokerSever brokerSever=new BrokerSever(server.accept());
            new Thread(brokerSever).start();
        }
    }

}*/

/*public class BrokerServer extends Thread{
    public static int SERVER_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())){
            while (true){
                String str = in.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("接收到原始数据:" + str);
                if (str.equals("consume")) {
                    String message = Broker.consume();
                    out.println (message);
                    out.flush();
                } else {
                    Broker.produce(str);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server  = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }
    }
}*/
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class BrokerServer implements Callable<String>{
    public static int SERVER_PORT = 10010;

    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        String str=null;
        try (BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())){
            while (true){
                str = in.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("接收到的数据是：" + str);
                if (str.equals("consume")) {
                    String message = Broker.consume();
                    out.println (message);
                    out.flush();
                } else {
                    Broker.produce(str);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server  = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            FutureTask<String> futureTask = new FutureTask<>(brokerServer);
            new Thread(futureTask).start();
        }
    }
}

