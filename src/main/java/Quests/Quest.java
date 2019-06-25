package Quests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Quest {
    private QuestStatus status = QuestStatus.Unstarted;
    protected static final Logger logger = LogManager.getRootLogger();

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
        logger.debug(getID() + " quest status set to " + getStatus());
        statusChanged();
    }

    abstract void statusChanged();

    public abstract String getID();

    public String displayMessage() {
        return "";
    }
}
