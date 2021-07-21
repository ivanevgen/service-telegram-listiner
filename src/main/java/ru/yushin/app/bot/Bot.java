package ru.yushin.app.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yushin.app.commands.Command;
import ru.yushin.app.transfer.TransferExcel;
import ru.yushin.app.transfer.TransferMessageService;
import ru.yushin.app.transfer.TransferTxt;
import ru.yushin.app.transfer.model.Message;
import ru.yushin.mail.Email;

import javax.mail.MessagingException;

public class Bot extends TelegramLongPollingBot {

    static final String BOT_TOKEN = "1729676833:AAHqC72pHn6aQKNL4WRPO5f_TMpZrzK-JsQ";
    static final String BOT_NAME = "VigryzkaProd_bot";

    TransferMessageService transferMessagesServiceEXCEL;
    Message message;

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void onUpdateReceived(Update update) {

        // получить id чата отправителя сообщения
        String chatIdReceivedUser = update.getMessage().getChat().getId().toString();

        // получим текст сообщения
        String input = Util.getMessage(update);

        // получим first + last name пользователя
        String userName = Util.getFirstAndLastNameReceiverMessage(update);

        String userId = update.getMessage().getFrom().getId().toString();

        // отправим в excel файл если это сообщение от монтажника
        if(input.contains("Установлено ПУ 1Т") || input.contains("Установлено ПУ 2Т")){
            message = new Message(update, userName, input);
            transferMessagesServiceEXCEL = new TransferMessageService(message, new TransferExcel());
            transferMessagesServiceEXCEL.transferExcel();
        }

        Command.commandSendReportToEmails(userId, input, chatIdReceivedUser, Util.getEmailsForRecent());
    }
}
