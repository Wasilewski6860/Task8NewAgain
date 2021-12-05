import java.util.ArrayList;
import java.util.List;


public class logic {
    public static boolean indexOf(List<Integer> list, int value) { // метод для проверки единственности элемента в списке по его значению

        boolean check = false;

        for (int j : list) {

            if (j == value) {

                check = true;
                break;
            }
        }

        return check;
    }

    public static boolean uniquiness(List<Integer> list, int position) { // метод для проверки единственности элемента в списке по его позиции

        boolean check = true;

        for (int i = 0; i < position; i++) {

            int j = list.get(position);
            if (j == list.get(i)) check = false;
        }

        for (int i = position + 1; i < list.size(); i++) {

            int j = list.get(position);
            if (j == list.get(i)) check = false;

        }

        return check;
    }

    public static List<Integer> inList1XorInList2(List<Integer> List1, List<Integer> List2) {

        List<Integer> List3 = new ArrayList<>();

        for (int i = 0; i < List1.size(); i++) {      //проверяется по значению, нет ли i-го элемента во втором исходном списке такого же элемента,
            // не записан ли уже этот элемент в список-решение, и является ли он уникальным для своего "родного списка".

            if (!indexOf(List2, List1.get(i)) && (!indexOf(List3, List1.get(i))) && (uniquiness(List1, i))) {
                List3.add(List1.get(i));
            }

        }
        for (int i = 0; i < List2.size(); i++) {    // аналогично первому циклу
            if (!indexOf(List1, List2.get(i)) && (!indexOf(List3, List2.get(i))) && (uniquiness(List2, i))) {
                List3.add(List2.get(i));
            }
        }

        return List3;

    }

}