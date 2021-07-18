package ru.yushin.app.transfer.model;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Message {
    Update update;
    String userName;
    String value;


    public Message(Update update, String userName, String value) {
        this.update = update;
        this.userName = userName;
        this.value = value;
    }

    /**
     * Конструктор для сервисных сообщений
     * @param userName
     * @param value сервисное сообщение
     */
    public Message(String userName, String value){
        this.userName = userName;
        this.value = value;
    }

    /**
     * Конструктор для сохранения file_id отправленного файла в log.txt
     * @param value
     */
    public Message(String value) {
        this.value = value;
    }

    public String getUserNameById(){
        return Names.getRealNameById(
                update.getMessage().getFrom().getId()
        );
    }

    public String[] evaluateValue(){
        return value.split("\n");
    }

    public String getUserName() {
        return Names.getRealName(userName);
    }

    public String getValue() {
        return value;
    }
}
