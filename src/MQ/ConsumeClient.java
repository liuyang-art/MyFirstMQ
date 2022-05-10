package MQ;

public class ConsumeClient {
    public static void main(String[] args) throws Exception {
        MQClient mqClient = new MQClient();
        String message = mqClient.consume();
        System.out.println("获取的消息为"+message);
    }
}
