import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            list.add(random.nextInt(15));
        }
        System.out.println("That your collection: " + list);

        List<Integer> listAscending = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("That your ascending sort collection: " + listAscending);

        List<Integer> listWithoutThree = list.stream()
                .filter(i -> i % 3 != 0 || i == 0)
                .collect(Collectors.toList());
        System.out.println("That your collection without three: " + listWithoutThree);

        String listIntToString = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining((", ")));
        System.out.println("That your collection Int to String: " + listIntToString);
    }
}