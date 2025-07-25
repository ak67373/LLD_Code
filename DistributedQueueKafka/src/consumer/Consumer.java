package consumer;

import model.Message;

public interface Consumer {
    void consume(String topic, Message message);
    String getId();
}
