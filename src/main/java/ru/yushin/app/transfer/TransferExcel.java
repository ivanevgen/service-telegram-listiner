package ru.yushin.app.transfer;

import ru.yushin.app.transfer.model.Message;
import ru.yushin.app.transfer.writers.ExcelWriter;
import ru.yushin.app.transfer.writers.IWriter;

public class TransferExcel implements ITransfer{
    IWriter excelWriter;

    public void transfer(Message message){
        excelWriter = new ExcelWriter();
        excelWriter.write(message);
    }
}
