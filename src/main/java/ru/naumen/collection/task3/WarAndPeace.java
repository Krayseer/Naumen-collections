package ru.naumen.collection.task3;


import java.nio.file.Path;
import java.util.*;


/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");


    /**
     * Сложность выполнения алгоритма O(n*log(N)), где n - колличество слов в файле, N - колличество первых слов с конца и начала.
     * Конкретно в этой задаче N = 10, так что сложность будет O(n)
     */
    public static void main(String[] args) {
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        Map<String, Long> wordCounts = new LinkedHashMap<>();
        Queue<Map.Entry<String, Long>> topWords = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));
        Queue<Map.Entry<String, Long>> lastWords = new PriorityQueue<>((a, b) -> Long.compare(b.getValue(), a.getValue()));
        int N = 10;

        wordParser.forEachWord(word -> wordCounts.put(word, wordCounts.getOrDefault(word, 0L) + 1L));

        for (Map.Entry<String, Long> entry : wordCounts.entrySet()) {
            topWords.offer(entry);
            lastWords.offer(entry);

            if (topWords.size() > N) {
                topWords.poll();
            }

            if (lastWords.size() > N) {
                lastWords.poll();
            }
        }

        System.out.printf("TOP %s%n", N);
        while (!topWords.isEmpty()) {
            Map.Entry<String, Long> entry = topWords.poll();
            System.out.printf("%s : %s%n", entry.getKey(), entry.getValue());
        }

        System.out.printf("%nLAST %s%n", N);
        while (!lastWords.isEmpty()) {
            Map.Entry<String, Long> entry = lastWords.poll();
            System.out.printf("%s : %s%n", entry.getKey(), entry.getValue());
        }
    }

}
