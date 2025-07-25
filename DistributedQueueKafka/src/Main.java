import consumer.Consumer;
import consumer.SimpleConsumer;
import producer.Producer;
import topic.Topic;
import topic.TopicManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TopicManager topicManager = TopicManager.getInstance();

        Topic topic1 = topicManager.createTopic("topic1");
        Topic topic2 = topicManager.createTopic("topic2");

        Producer producer1 = new Producer("producer1");
        Producer producer2 = new Producer("producer2");

        Consumer consumer1 = new SimpleConsumer("consumer1");
        Consumer consumer2 = new SimpleConsumer("consumer2");
        Consumer consumer3 = new SimpleConsumer("consumer3");
        Consumer consumer4 = new SimpleConsumer("consumer4");
        Consumer consumer5 = new SimpleConsumer("consumer5");

        topic1.subscribe(consumer1);
        topic1.subscribe(consumer2);
        topic1.subscribe(consumer3);
        topic1.subscribe(consumer4);
        topic1.subscribe(consumer5);

        topic2.subscribe(consumer1);
        topic2.subscribe(consumer3);
        topic2.subscribe(consumer4);

        producer1.publish("topic1", "Message 1");
        producer1.publish("topic1", "Message 2");
        producer2.publish("topic1", "Message 3");
        producer1.publish("topic2", "Message 4");
        producer2.publish("topic2", "Message 5");

        Thread.sleep(2000);
    }
}