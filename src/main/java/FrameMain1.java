import javax.swing.*;
import javax.swing.table.DefaultTableModel;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class FrameMain1 extends JFrame {
    private JTextField pathOfReading;
    private JPanel panelMain;
    private JTable table2;
    private JButton ManualReadingFromFileButton;
    private JButton ManualWritingToFileButton;
    private JTextField pathOfWriting;
    private JButton FileProcessingButton;
    private JTextField Errors;
    private JButton ReadFromDirectory;
    private JButton WriteToFileDirectory;
    private JButton minColumn1;
    private JButton plusColmn1;
    private JTable table1;
    private JButton minColmn2;
    private JButton plusColmn2;

    private int[][] startArr1 = new int[1][1];  // Массивы для расширения/сжатия JTable
    private int[][] startArr2 = new int[1][1];
    private JPanel panel1;

    public FrameMain1() {

        this.setTitle("Table");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        ManualReadingFromFileButton.addActionListener(new ActionListener() {  // считывание списков из файлов, путь к которым передан как строка в тексовом поле
            @Override
            public void actionPerformed(ActionEvent e) {

                String path = pathOfReading.getText(); // получаем строку - пути к файлам

                String[] pathes = path.split(" "); // строковый массив из дробления path после пробела


                try {

                    List<Integer> list1 = InputArgs.fileToList(pathes[0]); // считывание первого списка из файла по заданному пути

                    try {
                        List<Integer> list2 = InputArgs.fileToList(pathes[1]); //считывание второго списка

                        int[] array1 = list1.stream().mapToInt(Integer::intValue).toArray();//Преобразование списков в массивы, поскольку
                        int[] array2 = list2.stream().mapToInt(Integer::intValue).toArray();// метод writeArrayToJTable из бибилиотеки JTableUtils
                        //работает только с массивами

                        ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table1, array1); // Отображение в JTable
                        ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table2,array2);
                    } catch (FileNotFoundException ex) {
                        Errors.setText("Wrong path of reading second list, input you path like .\\\\yourpath.txt");
                        return;
                    }

                } catch (FileNotFoundException ex) {
                    Errors.setText("Wrong path of reading first list,input you path like .\\\\yourpath.txt ");
                    return;
                }


            }
        });
        ReadFromDirectory.addActionListener(new ActionListener() {  // Чтение с FileChooser

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser file1open = new JFileChooser(); // Создаются два объекта JFileChooser
                JFileChooser file2open = new JFileChooser();
                int ret = file1open.showDialog(null, "Открыть файл"); // Открывается первый

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = file1open.getSelectedFile();  // file получает выбранный в JFileChooser файл
                    String path = file.toString();  // В строку path записывается его имя

                    try {

                        List<Integer> list1 = InputArgs.fileToList(path);       // По данному пути создается список

                        int ret2 = file2open.showDialog(null, "Открыть файл");// Действия, аналогичные вышеперечисленным
                        if (ret2 == JFileChooser.APPROVE_OPTION) {

                            File file2 = file2open.getSelectedFile();
                            String path2 = file2.toString();

                            try {
                                List<Integer> list2 = InputArgs.fileToList(path2); // Создается второй список аналогично первому
                                // Списки преобразуются в массивы int[] для записи в JTable , т.к. JTableUtils принимает только массивы

                                int[] array1 = list1.stream().mapToInt(Integer::intValue).toArray(); // Перебираются все Integer-значения, преобразуются
                                int[] array2 = list2.stream().mapToInt(Integer::intValue).toArray();// в int и добавляются в массив
                                // Запись в JTable
                                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table1,array1);
                                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table2, array2);

                            } catch (FileNotFoundException exception) {
                                Errors.setText("Wrong path of reading second list"); // В соотв.поле записывается сообщение об ошибке
                                return;
                            }
                        }

                    } catch (FileNotFoundException ex) {
                        Errors.setText("Wrong path of reading first list ");  // Аналогично
                        return;
                    }
                }

            }
        });
        WriteToFileDirectory.addActionListener(new ActionListener() { // Запись JTable  в файл с использованием JFileChooser
                                                                        // Аналогично чтению с использованием JFileChooser

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");

                if (ret == JFileChooser.APPROVE_OPTION) {

                    File file = fileopen.getSelectedFile();
                    String path = file.toString();
                    List<Integer> list = WorkWithJT.JTtoList(table1); // Считывается список с table1

                    try {
                        PrintStream ps = new PrintStream(path); // По пути создается PrintStream
                        if (list!=null) {
                            InputArgs.savingOfList(ps, list); // Происходит запись списка
                        }else Errors.setText("List is empty");
                    } catch (FileNotFoundException ex) {  // Сообщение об ошибке
                        Errors.setText("Wrong path of writing ");
                        return;
                    }
                }
            }
        });

        ManualWritingToFileButton.addActionListener(new ActionListener() { // Запись в файл с указанием пути к файлу в текстовом поле
            @Override
            public void actionPerformed(ActionEvent e) {

                String path = pathOfWriting.getText(); // Из соотв.поля в строку записывается путь записи

                List<Integer> list = WorkWithJT.JTtoList(table1);// Список считывается с JTable

                try {
                    PrintStream ps = new PrintStream(path); // По пути из строки создается PrintStream
                    InputArgs.savingOfList(ps, list); // Список сохраняется

                } catch (FileNotFoundException ex) {
                    Errors.setText("Wrong path of writing ");  // Вывод сообщения об ошибке
                    return;
                }

            }
        });


        plusColmn1.addActionListener(new ActionListener() {    // Увеличение числа столбцов
            @Override
            public void actionPerformed(ActionEvent e) {

                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table1, startArr1); // JTable перезаписывается
                int[][] arr = new int[startArr1.length][startArr1[0].length + 1]; // Массив, на основе которого расширяется JTable, изменяется в размерах
                startArr1 = arr;

            }
        });

        plusColmn2.addActionListener(new ActionListener() {    // Увеличение числа столбцов, принцип plusColmn1
            @Override
            public void actionPerformed(ActionEvent e) {
                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table2, startArr2);
                int[][] arr = new int[startArr2.length ][startArr2[0].length + 1];
                startArr2 = arr;

            }
        });

        minColumn1.addActionListener(new ActionListener() {   // Уменьшение числа столбцов, принцип plusColmn1
            @Override
            public void actionPerformed(ActionEvent e) {
                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table1, startArr1);
                int[][] arr = new int[startArr1.length][startArr1[0].length - 1];
                startArr1 = arr;

            }
        });
        minColmn2.addActionListener(new ActionListener() {   // Уменьшение числа столбцов, принцип plusColmn1
            @Override
            public void actionPerformed(ActionEvent e) {
                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table2, startArr2);
                int[][] arr = new int[startArr2.length][startArr2[0].length - 1];
                startArr2 = arr;

            }
        });

        FileProcessingButton.addActionListener(new ActionListener() {   // Обработка JTable

            @Override
            public void actionPerformed(ActionEvent e) {

                List<Integer> list1 = WorkWithJT.JTtoList(table1); // Список считывается с JTable
                List<Integer> list2 = WorkWithJT.JTtoList(table2);// Список считывается с JTable

                // table1 и table2 очищаются
                DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                model1.setRowCount(0);
                DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
                model2.setRowCount(0);

                // Создается новый список путем обработки предыдущих( непосредственное решение задачи)
                List<Integer> list3 = logic.inList1XorInList2(list1, list2);
                int[] array = list3.stream().mapToInt(Integer::intValue).toArray();// Он записывается в список для вывода в таблице
                ru.vsu.baryshev.util.JTableUtils.writeArrayToJTable(table1, array);  // Отображение решения в JTable

            }

        });

    }

}
