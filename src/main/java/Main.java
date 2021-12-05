import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.SocketHandler;

public class Main {


    public static String test(String[] args) throws FileNotFoundException {
        Locale.setDefault(Locale.ENGLISH);
        String str="";

        if (args.length == 2) {  // Введение самих списков, где списки представляются как 1,2,3 4,5,6  (списки разделяются через пробел)

            InputArgs p = InputArgs.parseCmdArgs(args);

            List<Integer> list1 = InputArgs.strToList(p.params[0]);
            if(list1==null){ //Вывод сообщения об ошибке
                System.out.println("Wrong input format, do in like 1,2,3,4,5,7");
                return null;

            }

            List<Integer> list2 = InputArgs.strToList(p.params[1]);

            if(list2==null){   //Вывод сообщения об ошибке
                System.out.println("Wrong input format, do in like 1,2,3,4,5,7");
                return null;

            }

            List<Integer> list3 = logic.inList1XorInList2(list1,list2);
             str = list3.toString();
            System.out.println(str);


        }else if(args.length==3){  // Вводятся пути к файлам в виде .\\firstPathOfReading.txt .\\secondPathOfReading.txt .\\pathOfWriting.txt (пути разделяются пробелом)

            InputArgs p = InputArgs.parseCmdArgs(args);

            try {
                List<Integer> list1 = InputArgs.fileToList(args[0]);
                List<Integer> list2 = InputArgs.fileToList(args[1]);
                List<Integer> list3 = logic.inList1XorInList2(list1,list2);
                str = list3.toString();
                System.out.println(str);

                try {

                    PrintStream ps = new PrintStream(args[2]);

                    InputArgs.savingOfList(ps, list3);

                }catch (FileNotFoundException e){

                    System.out.println("Wrong path of writing");
                    return null;
                }

            }catch (FileNotFoundException ex){

                System.out.println("Wrong path of reading, make it like .\\\\yourpath.txt");
                return null;
            }

        }else if(args.length==0){   // работа с JTable

            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new FrameMain1().setVisible(true);
                }
            });

        }
        return str;
    }
    public static void finalTest(String[] lists,String[] io,int number,String rightAnswer) throws FileNotFoundException {
        System.out.println("Тест "+number);
        System.out.print("Ответ ");
        test(lists);
        System.out.println(" Верный ответ: "+rightAnswer);
        test(io);
        System.out.println("Проверьте .\\\\writing"+number+".txt");
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] lists1 = {"1,2,3,1,4,5","1,2,7"};
        String[] io1 = {".\\\\reading1.1.txt .\\\\reading1.2.txt .\\\\writing1.txt"};
        String[] jt = {};
        String[] lists2 = {"1,1,3,5","3,7,9"};
        String[] io2 ={".\\\\reading2.1.txt .\\reading2.2.txt .\\writing2.txt"};
        String[] lists3 = {"1","1"};
        String[] io3 ={".\\\\reading3.1.txt .\\reading3.2.txt .\\writing3.txt"};
        String[] lists4 = {"",""};
        String[] io4 ={".\\\\reading4.1.txt .\\reading4.2.txt .\\writing4.txt"};
        String[] lists5 = {"2,4,7,4","3,2,7"};
        String[] io5 ={".\\\\reading5.1.txt .\\reading5.2.txt .\\writing5.txt"};
        finalTest(lists1,io1,1,"3 4 5 7");
        finalTest(lists2,io2,2,"5 7 9");
        finalTest(lists3,io3,3,"");
        finalTest(lists4,io4,4,"Wrong format");
        finalTest(lists5,io5,5,"3");
        finalTest(jt,io3,6,"Запущен JTable");






    }
}