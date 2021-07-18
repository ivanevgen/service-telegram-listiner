package ru.yushin.app.transfer;

import ru.yushin.app.transfer.model.Message;

public interface ITransfer {
    /**
     * интерфейс для передачи данных, например в txt, docx, excel, html
     * @param message сообщение
     */
    void transfer(Message message);
}

