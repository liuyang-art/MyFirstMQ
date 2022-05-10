package MQ;
import java.util.concurrent.ArrayBlockingQueue;
public class Broker {
    private final static int MAX_SIZE=3;
    private static ArrayBlockingQueue<String >messeQueue=new ArrayBlockingQueue<>(MAX_SIZE);
    public static void produce(String msg){
        if(messeQueue.offer(msg)){
            System.out.println("成功向消息中心投递消息："+msg+",当前暂存的消息数量是："+messeQueue.size());
        }else{
            System.out.println("消息处理中心内暂存的消息达到最大负荷，不能继续放入消息");
        }
    }
    public static  String consume(){
        String msg=messeQueue.poll();
        if(msg!=null){
            System.out.println("已经消费消息："+msg+",当前暂存的消息数量是："+messeQueue.size());
        }else{
            System.out.println("消息处理中心内没有消息可供消费");
        }
        System.out.println("==================");
        return msg;
    }
}