package ru.yushin.app.transfer;

import ru.yushin.app.transfer.model.Message;

/**
 * Сервис для передачи сообщений в сторонние ресурсы txt, html, excel...etc
 */
public class TransferMessageService {
    Message message;
    ITransfer blankTransfer;

    public TransferMessageService(Message message, ITransfer transfer) {
        this.message = message;
        blankTransfer = transfer;
    }

    public TransferMessageService(ITransfer transfer){
        blankTransfer = transfer;
    }

    /**
     * Отправим конкретный текст сообщения в текстовый файл.
     */
    public void transferInTextFile(Message message){
        blankTransfer.transfer(message);
    }

    /**
     * Отправим текст сообщения в текстовый файл.
     */
    public void transferInTextFile(){
        blankTransfer.transfer(message);
    }

    /**
     * Отправим текст сообщения в excel файл
     */
    public void transferExcel(){
        blankTransfer.transfer(message);
    }

    public void transferDataBase(){
        blankTransfer.transfer(message);
    }
}
