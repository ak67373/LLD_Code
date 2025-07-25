package topic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {
    private static TopicManager instance;
    private final Map<String, Topic> topics = new ConcurrentHashMap<>();

    private TopicManager() {}

    public static synchronized TopicManager getInstance() {
        if (instance == null) {
            instance = new TopicManager();
        }
        return instance;
    }

    public Topic createTopic(String name) {
        return topics.computeIfAbsent(name, Topic::new);
    }

    public Topic getTopic(String name) {
        return topics.get(name);
    }
}
