package ru.yushin.app.transfer;

import ru.yushin.app.transfer.model.Message;
import ru.yushin.app.transfer.writers.IWriter;
import ru.yushin.app.transfer.writers.TxtWriter;

public class TransferTxt implements ITransfer{
    IWriter txtWriter;

    public void transfer(Message message) {
        txtWriter = new TxtWriter();
        txtWriter.write(message);
    }
}
