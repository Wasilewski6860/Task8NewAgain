import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkWithJT {

    public static List<Integer> JTtoList(JTable jtable) { // Запись данных с JTable в список
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < jtable.getColumnCount(); i++) {

            list.add(Integer.parseInt((String) jtable.getValueAt(0, i)));   //т.к. списки одномерные, то row=0

        }
        return list;
    }


}
