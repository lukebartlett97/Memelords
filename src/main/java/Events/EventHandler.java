package Events;

public class EventHandler {
    private static EventHandler eventHandler;
    public CollectEvent collectEvent = new CollectEvent();

    public static EventHandler getInstance() {
        if(eventHandler == null) {
            eventHandler = new EventHandler();
        }
        return eventHandler;
    }

}
