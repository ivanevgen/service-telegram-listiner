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
        //names.put("266119069", "Денис Клевцов"); //подсунуть сообщение в отчёт
    }

    static String getRealNameById(String id){
        for(String key : names.keySet()){
            if(id.equalsIgnoreCase(key)){
                return names.get(key);
            }
        }
        return "[ERROR]id="+id;
    }
}
