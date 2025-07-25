package topic;

import consumer.Consumer;
import model.Message;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Topic {
    private final String name;
    private final List<Consumer> subscribers = new CopyOnWriteArrayList<>();
    private final BlockingQueue<Message> messages = new LinkedBlockingQueue<>();

    public Topic(String name) {
        this.name = name;
        startDispatcher();
    }

    public String getName() {
        return name;
    }

    public void subscribe(Consumer consumer) {
        subscribers.add(consumer);
    }

    public void publish(Message message) {
        messages.offer(message);
    }

    private void startDispatcher() {
        Thread dispatcher = new Thread(() -> {
            while (true) {
                try {
                    Message message = messages.take();
                    for (Consumer consumer : subscribers) {
                        new Thread(() -> consumer.consume(name, message)).start();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        dispatcher.setDaemon(true);
        dispatcher.start();
    }
}
