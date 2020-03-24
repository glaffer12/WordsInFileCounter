/*      На входе имеется файл, заполненный словами. Необходимо считать все слова из файла,
        отсортировать их в алфавитном порядке и вывести на экран. Посчитать, сколько раз встречается
        в файле каждое из слов и вывести статистику на экран.
        Найти слово, встречающееся максимальное число раз в файле и его частоту и вывести на экран.

        Замечания:
        - Предусмотреть ввод пользователем пути к файлу, как абсолютного, так и относительного.
        - В случае нескольких слов с максимальной частотой выводить необходимо все.
        - Предусмотреть, что слова могут быть разделены не только пробелами, но и знаками препинания,
            табуляции и переноса строки, все эти символы не должны учитываться.
        - Допускается только один полный обход файла.
        - Максимально предусмотреть возможные исключения.*/


import java.io.*;
import java.util.*;

public class WordsInFileCounter {

    private TreeMap<String, Integer> wordsInFile = new TreeMap<>();

    public static void main(String[] args) throws IOException {

        WordsInFileCounter counter = new WordsInFileCounter();

        counter.readFile()
                .printWords()
                .wordCount()
                .maxWordCount();
    }

    private WordsInFileCounter readFile()throws IOException{
        System.out.println("Введите путь к файлу: ");
        BufferedReader textReader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = textReader.readLine();
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = fileReader.readLine()) != null) {
            sb.append(str.replaceAll("([\\p{Punct}])", "").toLowerCase() + " ");
        }
        String[] words = sb.toString().split("\\s+");
        for (String word : words) {
            if (!wordsInFile.containsKey(word)) {
                wordsInFile.put(word, 0);
            }
            wordsInFile.put(word, wordsInFile.get(word) + 1);
        }
        System.out.println();
        textReader.close();
        fileReader.close();
        return this;
    }

    private WordsInFileCounter printWords(){
        System.out.println("Слова в алфавитном порядке: \n");
        for (String word : wordsInFile.keySet())
            System.out.println(word);
        System.out.println();
        return this;
    }

    private WordsInFileCounter wordCount(){
        for (String word : wordsInFile.keySet()) {
            System.out.println("Слово " + word + " встречается в файле " + wordsInFile.get(word) + wordEnding(wordsInFile.get(word)));
        }
        System.out.println();
        return this;
    }

    private WordsInFileCounter maxWordCount() {
        int maxCount = Collections.max(wordsInFile.values());
        for (String word : wordsInFile.keySet()) {
            if (wordsInFile.get(word) == maxCount) {
                System.out.println("Чаще всего в файле встречается слово: " + word + " - " + wordsInFile.get(word) + wordEnding(wordsInFile.get(word)));
            }
        }
        System.out.println();
        return this;
    }

    private String wordEnding(int count) {
        String s1 = " раз";
        String s2 = " раза";
        if((count%10)>=2 && (count%10)<=4) return s2;
        else return s1;
    }
}
