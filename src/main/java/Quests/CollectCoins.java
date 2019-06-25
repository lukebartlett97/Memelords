package Quests;

import Events.EventHandler;
import Events.EventListener;
import Objects.Coin;
import Objects.TileObject;

public class CollectCoins extends Quest implements EventListener {
    private int coinsCollected = 0;

    @Override
    public boolean eventNotified(Object data) {
        TileObject tileObject = (TileObject) data;
        boolean unsubscribe = false;
        if(tileObject.getName().equals(Coin.ITEM_NAME)) {
            coinsCollected += 1;
        }
        if(coinsCollected == 5) {
            setStatus(QuestStatus.Complete);
            unsubscribe = true;
        }
        logger.debug("CollectCoins quest progressed to: " + coinsCollected);
        return unsubscribe;
    }

    @Override
    void statusChanged() {
        switch (getStatus()) {
            case Unstarted:
                break;
            case InProgress:
                subscribe();
                break;
            case Complete:
                break;
            case HandedIn:
                break;
        }
    }

    @Override
    public String getID() {
        return "CollectCoins";
    }

    @Override
    public String displayMessage() {
        return "Collect 5 coins (" + coinsCollected + "/5)";
    }

    private void subscribe() {
        EventHandler.getInstance().collectEvent.subscribe(this);
    }

    private void unsubscribe() {
        EventHandler.getInstance().collectEvent.unsubscribe(this);
    }
}
