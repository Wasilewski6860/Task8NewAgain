import java.io.*;
import java.util.*;

import static java.lang.System.out;

public class InputArgs {

    public String[] params;

    public InputArgs(String[] params) { // вспомогательный коструктор

        this.params = params;

    }

    public static InputArgs parseCmdArgs(String[] args) {  // Прием параметров командной строки

        InputArgs p = new InputArgs(args);

        return p;
    }


    public static List<Integer> strToList(String str) {   //Преобразование строки в список

        String[] items = str.split(",");  //СОздается строковый массив дроблением строки после ","

        for (int i = 0; i < str.length(); i++) {  // Проверка на корректность введенной строки

            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z' || ch >= 'а' && ch <= 'я') {
                return null;
            }
        }

        List<Integer> list = new ArrayList<>();

        for (String item : items) {   // Сама запись элементов массива в качестве Integer-элемента

            try {
                list.add(Integer.parseInt(item));

            } catch (NumberFormatException nfe) {

                return null;
            }
        }
        return list;

    }

    public static List<Integer> fileToList(String path) throws FileNotFoundException { // Считывание списка из файла, получает путь к файлу в виде String

        File file = new File(path);  // по пути создается File, после в нем/по нему(не знаю как говорится правильно) создается сканнер
        Scanner scn = new Scanner(file);
        List<Integer> list = new ArrayList<Integer>();

        if (file.length() == 0) { // Проверка на пустоту файла
            out.println("File if empty");
            return null;
        } else {
            String[] str = scn.nextLine().split(","); // Далее аналогично методу strToList
            for (int i = 0; i < str.length; i++) {

                try {

                    list.add(Integer.parseInt(str[i]));

                } catch (NumberFormatException nfe) {

                    //  out.println("Wrong Format");
                    return null;
                }
                ;
            }

        }
        return list;

    }


    public static void savingOfList(PrintStream ps, List<Integer> list) { // Запись списка в файл по входному PrintStream и списку
        for (int i = 0; i < list.size(); i++) {
            ps.print(list.get(i) + " ");
        }
        out.flush(); //заливаем все в поток
        out.close(); // закрываем его
    }

    public static void printOfList(List<Integer> list) { // метод для вывода списка


        for (int i = 0; i < list.size(); i++) {
            out.print(list.get(i) + " ");
        }

    }


}
