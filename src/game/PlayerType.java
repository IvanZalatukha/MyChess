package game;

public enum PlayerType {
    WHITE("W"),
    BLACK("B");

    private final String value;

    PlayerType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    PlayerType player;
    public PlayerType getPlayer() {
        return player;
    }
}
