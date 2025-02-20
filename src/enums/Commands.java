package enums;

public enum Commands {
    ADD("добавить"),
    REMOVE("удалить"),
    EXIT("выйти");

    private String commandValue;
    Commands(String command) {
        this.commandValue = command;
    }

    public String getValue (){
        return commandValue;
    }
}
