package Objects.People;

import Game.Player;
import Game.PlayerStatus;
import Objects.TileObject;
import Quests.Quest;
import Quests.QuestStatus;

import java.awt.*;
import java.util.ArrayList;

public class OldMan extends TileObject implements Person {
    static public String PERSON_NAME = "OldMan";

    public OldMan() {
        setImage("Players/OldMan.png", new Color(155,155,155));
        name = "Old Man";
        interactable = true;
    }

    @Override
    public void interact(Player player) {
        player.setStatus(PlayerStatus.TALKING);
        Quest collectCoinsQuest = player.getQuestCollection().getQuest("CollectCoins");
        ArrayList<String> messages = null;
        switch(collectCoinsQuest.getStatus()) {
            case Unstarted:
                collectCoinsQuest.setStatus(QuestStatus.InProgress);
                messages = getUnstartedMessages();
                break;
            case InProgress:
                messages = getProgressMessages();
                break;
            case Complete:
                collectCoinsQuest.setStatus(QuestStatus.HandedIn);
                messages = getCompleteMessages();
                break;
            case HandedIn:
                messages = getHandedInMessages();
                break;
        }
        if(messages != null) {
            player.startTalking(messages);
        }

    }

    private ArrayList<String> getUnstartedMessages() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Hi, my name is Bill!");
        messages.add("I've managed to lose all of my gold.");
        messages.add("Do you think you could grab it for me?");
        return messages;
    }

    private ArrayList<String> getProgressMessages() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Did you manage to find all of my coins?");
        return messages;
    }

    private ArrayList<String> getCompleteMessages() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Oh, thank you!");
        messages.add("Take this for your troubles.");
        return messages;
    }

    private ArrayList<String> getHandedInMessages() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("I'm so glad to have my gold back.");
        return messages;
    }


}
