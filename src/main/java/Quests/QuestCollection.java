package Quests;

import java.util.ArrayList;
import java.util.List;

public class QuestCollection {
    List<Quest> questList = new ArrayList<>();

    public QuestCollection() {
        loadQuests();
    }

    private void loadQuests() {
        questList.add(new CollectCoins());
    }

    public Quest getQuest(String questName) {
        for (Quest quest: questList) {
            if(quest.getID().equals(questName)) {
                return quest;
            }
        }
        return null;
    }
}
