import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sol on 2015-10-21.
 */
public class NaiveBayes {

    List<String> categories;

    int totalWords = 0;
    int totalDocuments = 0;
    int totalCategories;
    Map<String,Map<String, Integer>> occurrencesPerWordAndCategory;
    Map<String, Integer> documentsPerCategory;
    Map<String, Integer> numberOfWordsPerCategory;

    public NaiveBayes(List<String> categories) {
        this.categories = categories;
        totalCategories = categories.size();
        occurrencesPerWordAndCategory = new HashMap();
        documentsPerCategory = new HashMap();
        numberOfWordsPerCategory = new HashMap();
        setupMaps();
    }

    /**
     * Initiate maps in order to avoid nullpointer
     */
    private void setupMaps() {
        for (String category : categories) {
            occurrencesPerWordAndCategory.put(category, new HashMap<String, Integer>());
            numberOfWordsPerCategory.put(category, 0);
            documentsPerCategory.put(category, 0);
        }
    }

    public void train(String category, String document){
//        document = document.toLowerCase();
        Map<String, Integer> occurrencesPerWordLocal = occurrencesPerWordAndCategory.get(category);
        String[] words = document.split(" ");
        for (String word : words) {
            //Count occurrences of word in category
            Integer occurrences = occurrencesPerWordLocal.get(word);
            if (occurrences == null) {
                occurrencesPerWordLocal.put(word, 1);
            } else {
                occurrencesPerWordLocal.put(word, occurrences + 1);
            }

        }
        //total number of words in category
        numberOfWordsPerCategory.put(category, numberOfWordsPerCategory.get(category) + words.length);
        totalWords = totalWords + words.length;
        documentsPerCategory.put(category, documentsPerCategory.get(category) + 1);
        totalDocuments++;
    }

    public String classify(String document){
        //calculate probability for each category given the document
        String bestCategory = "";
        double bestProbability = 0;
        double probability = 0;
        for (String category : categories) {
            System.out.print(category + " ");
            probability = probabilty(category, document);
            System.out.println(probability);
            if (probability > bestProbability) {
                bestProbability = probability;
                bestCategory = category;
            }
        }
        return bestCategory;
    }

    private double probabilty(String category, String document) {
        return documentBelongsToCategoryProbability(category, document) * isCategoryProbability(category);
    }

    private double isCategoryProbability(String category) {
        return (double) documentsPerCategory.get(category) / totalDocuments;
    }

    private double documentBelongsToCategoryProbability(String category, String document) {
        double probability = 1;
        String[] words = document.split(" ");
        for (String word : words) {
            probability = probability * wordBelongsCategoryProbability(category, word);
        }
        return probability;
    }

    private double wordBelongsCategoryProbability(String category, String word) {
        Integer wordCount = occurrencesPerWordAndCategory.get(category).get(word);
        if (wordCount == null) {
            wordCount = 1;
        } else {
            wordCount++;
        }
        return (double) wordCount / numberOfWordsPerCategory.get(category);
    }

}
