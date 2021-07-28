package ru.yushin.app.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yushin.app.commands.Command;
import ru.yushin.app.transfer.TransferExcel;
import ru.yushin.app.transfer.TransferMessageService;
import ru.yushin.app.transfer.TransferTxt;
import ru.yushin.app.transfer.model.Message;

import javax.mail.MessagingException;

public class Bot extends TelegramLongPollingBot {

    //static final String BOT_TOKEN = "1729676833:AAHqC72pHn6aQKNL4WRPO5f_TMpZrzK-JsQ";
    static final String BOT_TOKEN = "483351577:AAH8iX-MeUTGkUUVv0uu75WISA_WpQd2kzw";
    static final String BOT_NAME = "Reinevanbot";

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
        System.out.println(input);
        // получим first + last name пользователя
        String userName = Util.getFirstAndLastNameReceiverMessage(update);

        String userId = update.getMessage().getFrom().getId().toString();

        // отправим в excel файл если это сообщение от монтажника
        if(input.contains("Установлено ПУ 1Т")
                || input.contains("Установлено ПУ 2Т")
                || input.contains("Установлено ПУ 1т")
                || input.contains("Установлено ПУ 2т")
                || input.contains("Установлено ПУ 3ф 1т")){
            message = new Message(update, userName, input);
            transferMessagesServiceEXCEL = new TransferMessageService(message, new TransferExcel());
            transferMessagesServiceEXCEL.transferExcel();
        }

        Command.commandSendReportToEmails(userId, input, chatIdReceivedUser, Util.getEmailsForRecent());
    }
}
