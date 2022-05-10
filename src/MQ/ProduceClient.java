package MQ;
import java.io.IOException;

public class ProduceClient {
    public static void main(String[] args) throws Exception {
        MQClient client = new MQClient();
        client.Produce("hello world");
    }
}
