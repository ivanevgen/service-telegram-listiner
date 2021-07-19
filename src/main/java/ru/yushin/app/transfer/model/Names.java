package ru.yushin.app.transfer.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Names {
    static Map<String, String> names = new HashMap<String, String>();
    static Map<String, String> namesById = new HashMap<String, String>();

    static {
        names.put("Ivan Evgenievich", "DEVELOPER");
        names.put("Георгий null", "Георгий Гусев");
        names.put("Ефимов И. Г. Вомифе", "И. Ефимов");
        names.put("Вадим null", "Вадим Морозов");
        names.put("АНДРЕЙ null", "Андрей Корнеев");
        names.put("Иван Тюрин", "Иван Тюрин");
        names.put("Денис Клевцов", "Денис Клевцов");
        names.put("Артур null", "Артур Абрамян");
        names.put("Камакин Николай", "Николай Камакин");
        names.put("Demidov Andrey", "Андрей Демидов");
        names.put("Евгений Евгений", "Евгений Ермолаев");
        names.put("Алексей Лазарев", "Алексей Лазарев");
        names.put("Евгений null", "Евгений Галин");

        namesById.put("1641782618", "Георгий Гусев");
        namesById.put("1133639559", "Виктор Бузин");
        namesById.put("1512980956", "Андрей Корнеев");
        namesById.put("1426724083", "Александр Сквиницкий");
        namesById.put("1589305105", "Евгений Ермолаев");
        namesById.put("1047540189", "Евгений Галин");
        namesById.put("1402631319", "Вадим Морозов");
        namesById.put("1400704599", "И. Ефимов");
        namesById.put("1549856357", "Андрей Клевцов");
    }

    static String getRealName(String fakeName){
        for(String key : names.keySet()){
            if(fakeName.equalsIgnoreCase(key)){
                return names.get(key);
            }
        }
        return fakeName;
    }

    static String getRealNameById(String id){
        for(String key : namesById.keySet()){
            if(id.equalsIgnoreCase(key)){
                return namesById.get(key);
            }
        }
        return "[ERROR]id="+id;
    }
}
