package ru.yushin.app.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yushin.app.transfer.TransferExcel;
import ru.yushin.app.transfer.TransferMessageService;
import ru.yushin.app.transfer.TransferTxt;
import ru.yushin.app.transfer.model.Message;
import ru.yushin.mail.Email;

import javax.mail.MessagingException;
import java.io.IOException;

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
        // если отправлен документ, сохраним его file_id в log.txt
        checkForDocumentUpload(update);

        // получить id чата отправителя сообщения
        String chatIdReceivedUser = update.getMessage().getChat().getId().toString();

        // получим текст сообщения
        String input = Util.getMessage(update);

        // получим first + last name пользователя
        String userName = Util.getFirstAndLastNameReceiverMessage(update);

        // на 20 число, чтобы выявить айдишники недостающих монтажников
        String userId = update.getMessage().getFrom().getId().toString();
        System.out.println(input);
        System.out.println(String.format("---[id юзера[%s]=[%s]---", userName ,userId));
        System.out.println();

        // отправим в excel файл если это сообщение от монтажника
        if(input.contains("Установлено ПУ 1Т") || input.contains("Установлено ПУ 2Т")){
            // -336352650 общий чат СМНУ ВК
            // -526991630 admin bot
            message = new Message(update, userName, input);
            transferMessagesServiceEXCEL = new TransferMessageService(message, new TransferExcel());
            transferMessagesServiceEXCEL.transferExcel();
        }

        sendReportToEmail(userId, input, chatIdReceivedUser, "Efremov16@yandex.ru");
    }

    /**
     * Проверим, что отправленный файл является отчетом.
     * @param update
     */
    private void checkForDocumentUpload(Update update){
        TransferMessageService transferMessagesServiceLOG;
        Document document = update.getMessage().getDocument();
        if(document != null){
            if(document.getFileName().equalsIgnoreCase("actual.xlsx")){
                String fileId = document.getFileId();
                transferMessagesServiceLOG = new TransferMessageService(
                        new Message(fileId),
                        new TransferTxt()
                );

                transferMessagesServiceLOG.transferInTextFile();
            }
        }
    }

    /**
     *
     * @param userId id пользователя
     * @param input сообщение чата, если соответствует паттерну, то отправим на почту
     * @param chatIdReceivedUser чат, куда напишем сообщение об успещной отправке сообщения на почту
     * @param emailAddress целевой email адрес куда отправляем отчёт
     */
    private void sendReportToEmail(String userId,
                                   String input,
                                   String chatIdReceivedUser,
                                   String emailAddress){

        Email email = new Email();

        if((userId.equals("313243971") || userId.equals("266119069")) && input.equalsIgnoreCase("выгрузить")){
            Util.sendMessageInChat("Выгружаем", chatIdReceivedUser);
            try {
                String sended = email.sendEmail(emailAddress);
                Util.sendMessageInChat(sended, chatIdReceivedUser);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }
}
