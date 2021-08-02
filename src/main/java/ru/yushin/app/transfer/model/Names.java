package ru.yushin.app.transfer.model;

import java.util.HashMap;
import java.util.Map;

public class Names {
    static Map<String, String> names = new HashMap<String, String>();

    static {
        names.put("1641782618", "Георгий Гусев");
        names.put("1133639559", "Виктор Бузин");
        names.put("1512980956", "Андрей Корнеев");
        names.put("1426724083", "Александр Сквиницкий");
        names.put("1589305105", "Евгений Ермолаев");
        names.put("1047540189", "Евгений Галин");
        names.put("1402631319", "Вадим Морозов");
        names.put("1400704599", "И. Ефимов");
        names.put("1549856357", "Андрей Клевцов");
        names.put("1491334345", "Николай Камакин");
        names.put("1354004580", "Артур Абрамян");
        names.put("132537132", "Владимир Алдошкин");
        names.put("1441852663", "Иван Тюрин");
        names.put("1453539680", "Денис Клевцов");
        names.put("1285703032", "Алексей Лазарев");
        names.put("211178620", "Александр Шалимов");
        names.put("1917107181", "Геннадий Лунин");
        //names.put("266119069", "Денис Клевцов"); //подсунуть сообщение в отчёт
    }

    /**
     *
     * @param userName имя как в телеге
     * @param id айдишник
     * @return вернуть реальное имя по айди, если нет айди вернем имя как в телеге
     */
    static String getRealNameById(String userName, String id){
        for(String key : names.keySet()){
            if(id.equalsIgnoreCase(key)){
                return names.get(key);
            }
        }
        return userName + "[id="+id+"]";
    }
}
