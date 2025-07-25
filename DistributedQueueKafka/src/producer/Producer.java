package producer;

import model.Message;
import topic.Topic;
import topic.TopicManager;

public class Producer {
    private final String id;

    public Producer(String id) {
        this.id = id;
    }

    public void publish(String topicName, String content) {
        Topic topic = TopicManager.getInstance().getTopic(topicName);
        if (topic != null) {
            topic.publish(new Message(content));
        }
    }
}
