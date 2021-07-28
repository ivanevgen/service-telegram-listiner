package ru.yushin.app.eval;

public class Evaluate {

    public static final String REG_EXP_PATTERN = "[^0-9\\\\+]";

    String[] values;

    public Evaluate(String[] values) {
        this.values = values;
    }

    public String[] getValues() {
        return values;
    }

    public String getAddress(){
        String address = values[1].trim();
        return getOriginalAddress(address);
    }

    private String getOriginalAddress(String address) {
        String line = address;
        if(address.contains("Адрес")){
            line = address.replaceAll("Адрес", "");
        }
        if(address.contains("АДРЕС")){
            line = address.replaceAll("АДРЕС", "");
        }
        return line.trim();
    }

    /**
     * //-- для случая, если в адресе присутствует '-'
     * @param address
     * @return красивый адрес
     */
    private String appendAddress(String address){
        String[] arr = address.split("-");
        StringBuilder builder = new StringBuilder();
        for(String line : arr) {
            builder.append(line);
        }
        return builder.toString().trim();
    }

    /**
     * //-- для случая, если в адресе присутствует 'АДРЕС/Адрес'
     * @param address
     * @return красивый адрес
     */
    private String replaceAddress(String address){
        String replaceAddress = "";
        if(address.contains("АДРЕС")) {
            replaceAddress = address.replaceAll("АДРЕС", "");
            return replaceAddress;
        } else if(address.contains("Адрес")){
            replaceAddress = address.replaceAll("Адрес", "");
            return replaceAddress;
        }
        return address;
    }

    //Отказы
    public double getFailureValue() {
        return parseValue(values[values.length-3]);
    }

    //Брак ПУ
    public double getDefectiveValuePY() {
        return parseValue(values[values.length-2]);
    }

    //Брак ПЛ
    public double getDefectiveValuePL() {
        return parseValue(values[values.length-1]);
    }

    private double parseValue(String value){
        return value.replaceAll(Evaluate.REG_EXP_PATTERN, "").length() == 0 ?
                0 : Double.parseDouble(value.replaceAll(Evaluate.REG_EXP_PATTERN, ""));
    }

    /**
     *
     * @param value
     * @return возвращает число установленных ПУ
     */
    public double parseIstalledAnyPY(String value){
        String parsedValue = "";
        if(value.split("-").length > 1 || value.split("–").length > 1){
            if(value.contains("-")){
                parsedValue = value.split("-")[1].trim();
            } else if(value.contains("–")){
                parsedValue = value.split("–")[1].trim();
            }
        } else {
            parsedValue = "0";
        }

        if(parsedValue.length() == 0){
            return 0;
        } else {
            return Double.parseDouble(parsedValue.replaceAll(REG_EXP_PATTERN, ""));
        }
    }
}
