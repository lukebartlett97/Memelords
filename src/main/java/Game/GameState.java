package Game;

import Quests.QuestCollection;

public class GameState {
    private Player player;
    private GameMode gameMode;

    public GameState() {
        this(2, 2);
    }

    public GameState(int x, int y) {
        player = new Player(x,y);
        gameMode = new PlayMode();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
