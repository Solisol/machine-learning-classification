import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sol on 2015-10-21.
 */
public class NaiveBayesTest {

    public static void main(String[] args) {
        Set<String> categorySet = new HashSet<String>();
        categorySet.addAll(Arrays.asList("saga", "horror"));
        NaiveBayes naiveBayes = new NaiveBayes(categorySet);
        naiveBayes.train("horror", "it was scary as shit once");
        naiveBayes.train("saga", "once upon a time there was a girl");
        naiveBayes.train("saga", "once upon a time there was a dog");
        String expectedResult = "saga";
        String result = naiveBayes.classify("once upon a time there was a cat");
        if (expectedResult.equals(result)) {
            System.out.println("YAY");
        } else {
            System.out.println("Noooooes " + result);
        }
    }
}
