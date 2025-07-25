package consumer;

import model.Message;

public class SimpleConsumer implements Consumer{
    private final String id;

    public SimpleConsumer(String id) {
        this.id = id;
    }

    @Override
    public void consume(String topic, Message message) {
        System.out.println(id + " received " + message.getContent());
    }

    @Override
    public String getId() {
        return id;
    }
}
