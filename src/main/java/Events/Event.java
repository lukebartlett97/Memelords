package Events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Event {
    public List<EventListener> subscribers = new ArrayList<>();
    protected static final Logger logger = LogManager.getRootLogger();

    public void subscribe(EventListener eventListener) {
        subscribers.add(eventListener);
        logger.debug("New Listener added to " + this.getClass().getSimpleName() + ": " + eventListener.getClass().getSimpleName());
    }

    public void unsubscribe(EventListener eventListener) {
        subscribers.remove(eventListener);
        logger.debug("Listener removed from " + this.getClass().getSimpleName() + ": " + eventListener.getClass().getSimpleName());
    }

    public void notify(Object data) {
        List<EventListener> unsubs = new ArrayList<>();
        for(EventListener eventListener : subscribers) {
            boolean unsubscribe = eventListener.eventNotified(data);
            if(unsubscribe) {
                unsubs.add(eventListener);
            }
        }
        for(EventListener eventListener : unsubs) {
            unsubscribe(eventListener);
        }

    }

}
