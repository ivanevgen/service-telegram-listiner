package ru.yushin.app.transfer.writers;

import ru.yushin.app.bot.Util;
import ru.yushin.app.eval.Evaluate;
import ru.yushin.app.transfer.model.Message;

public class ExcelWriter extends ExcelEngine implements IWriter{
    Message message;
    public static final int START_PARSE_MESSAGE = 2; // c какой строки начинаем парсить сообщение

    public void write(Message message) {
        this.message = message;
//        if(message.getValue().contains("###остаток")){
//            sendDataInExcelFileLostsPY();
//        } else {
//            sendDataInExcelFileActual();
//        }
        sendDataInExcelFileActual();
    }

    /**
     * Запись данных в эксель файл actual
     */
    private void sendDataInExcelFileActual(){
        Evaluate evaluator = new Evaluate(message.evaluateValue());

        // создадим строку перед заполнением
        createRowAndCells();

        insertDataInCellByName("ДАТА", Util.getCurrentTime());

        insertDataInCellByName("Монтажник", message.getUserNameById());

        insertDataInCellByName("Адрес", evaluator.getAddress());

        insertDataInCellByName("Отказы", evaluator.getFailureValue());

        insertDataInCellByName("Брак ПУ", evaluator.getDefectiveValuePY());

        insertDataInCellByName("Брак ПЛ", evaluator.getDefectiveValuePL());

        insertDataInCellByName("Установлено ПУ 1Т", evaluator.parseIstalledAnyPY(evaluator.getValues()[2]));
        insertDataInCellByName("Установлено ПУ 2Т", evaluator.parseIstalledAnyPY(evaluator.getValues()[3]));
        insertDataInCellByName("Установлено ПУ 3Т", evaluator.parseIstalledAnyPY(evaluator.getValues()[4]));
        insertDataInCellByName("Установлено ПУ 3Ф 1Т", evaluator.parseIstalledAnyPY(evaluator.getValues()[5]));
        insertDataInCellByName("Установлено ПУ 3Ф 2Т", evaluator.parseIstalledAnyPY(evaluator.getValues()[6]));
        insertDataInCellByName("Установлено ПУ 3Ф 3Т", evaluator.parseIstalledAnyPY(evaluator.getValues()[7]));

        //парсим сообщение
//        for(int i = START_PARSE_MESSAGE; i < evaluator.getValues().length - 3; i++){
//            String line = evaluator.getValues()[i];
//            String valueToInsert = "";
//            double valueToInsertDouble = 0;
//
//            String cellName = line.split("-")[0].trim();
//            try{
//
//                valueToInsert = line.split("-")[1]
//                        .trim()
//                        .replaceAll(Evaluate.REG_EXP_PATTERN, "");
//                if(valueToInsert.length() == 0) valueToInsert = "0";
//
//                valueToInsertDouble = Double.parseDouble(valueToInsert);
//            } catch (IndexOutOfBoundsException ex){
//                //-- todo ужасный костыль, избавится
//
//                if(line.contains("–")){
//
//                    String[] arr = line.split("–");
//                    if(arr.length > 1){
//                        cellName = arr[0].trim();
//                        valueToInsert = arr[1].replaceAll(Evaluate.REG_EXP_PATTERN, "");
//                        valueToInsertDouble = Double.parseDouble(valueToInsert);
//
//                        insertDataInCellByName(cellName, valueToInsertDouble);
//                        continue;
//                    } else {
//                        valueToInsert = "0";
//                        valueToInsertDouble = Double.parseDouble(valueToInsert);
//                        cellName = arr[0].trim();
//                    }
//
//                } else {
//                    valueToInsert = "0";
//                    valueToInsertDouble = Double.parseDouble(valueToInsert);
//                }
//            }//--
//
//            insertDataInCellByName(cellName, valueToInsertDouble);
//        }

        insertDataInCellByName("ВСЕГО ЗА ДЕНЬ УСТАНОВЛЕНО ПУ", getSumOfInstalledPY());

        insertDataInCellByName("айди сообщения", message.getMessageId());
    }

    private void sendDataInExcelFileLostsPY(){

    }

}
