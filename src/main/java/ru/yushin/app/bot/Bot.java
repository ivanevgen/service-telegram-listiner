package ru.yushin.app.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yushin.app.transfer.TransferExcel;
import ru.yushin.app.transfer.TransferMessageService;
import ru.yushin.app.transfer.TransferTxt;
import ru.yushin.app.transfer.model.Message;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    static final String BOT_TOKEN = "1729676833:AAHqC72pHn6aQKNL4WRPO5f_TMpZrzK-JsQ";
    static final String BOT_NAME = "VigryzkaProd_bot";
    static final String REPORT_CHAT = "-592971739";
    static final String ADMIN_CHAT = "-526991630";

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
        System.out.println("---[id отправителя сообщения "+ userId +"]---");
        System.out.println();
        System.out.println();

        // отправим в excel файл если это сообщение от монтажника
        if(input.contains("Установлено ПУ 1Т") || input.contains("Установлено ПУ 2Т")){
            message = new Message(update, userName, input);
            transferMessagesServiceEXCEL = new TransferMessageService(message, new TransferExcel());
            transferMessagesServiceEXCEL.transferExcel();
        }

        commandToSendExcel(userId, input, chatIdReceivedUser);
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
     *  Сашин id 313243971, сделать так, чтобы только ему по команде отправлялся отчёт
     */
    private void commandToSendExcel(String userId, String input, String chatIdReceivedUser){
        if((userId.equals("313243971") || userId.equals("266119069")) && input.equalsIgnoreCase("выгрузить")){
            Util.sendMessageInChat("Выгружаем", chatIdReceivedUser);
            try {
                Util.sendDocumentToUser(chatIdReceivedUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
