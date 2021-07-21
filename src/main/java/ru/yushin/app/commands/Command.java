package ru.yushin.app.commands;

import ru.yushin.app.bot.Util;

import javax.mail.MessagingException;

/**
 * Класс команд для бота
 */
public class Command {

    /**
     *
     * @param userId id пользователя
     * @param input сообщение чата, если соответствует паттерну, то отправим на почту /sendToEmails
     * @param chatIdReceivedUser чат, куда напишем сообщение об успещной отправке сообщения на почту
     * @param emailAddress целевой email адрес куда отправляем отчёт
     */
    public static void commandSendReportToEmails(String userId,
                                         String input,
                                         String chatIdReceivedUser,
                                         String[] emailAddress){
        if(input.equalsIgnoreCase("выгрузить")){

            if((userId.equals("313243971") || userId.equals("266119069"))){

                EmailSender email = new EmailSender();
                Util.sendMessageInChat("Выгружаем", chatIdReceivedUser);
                try {
                    for (String address : emailAddress) {
                        String sended = email.sendEmail(address);
                        Util.sendMessageInChat(sended, chatIdReceivedUser);
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
