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
                update.getMessage().getFrom().getId().toString()
        );
    }

    /**
     * Костыль против Дениса Клевцова, он лупит своё ФИО в сообщение зачем-то
     * @return
     */
    public String[] evaluateValue(){
        String[] arr = value.split("\n");
        if(arr.length == 11){
            return arr;
        } else {
            String[] newArr = new String[arr.length - 1];
            int j = 1;
            for(int i = 0; i < newArr.length; i++){
                newArr[i] = arr[j];
                j++;
            }
            return newArr;
        }
    }


    public String getValue() {
        return value;
    }
}
