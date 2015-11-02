import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sol on 2015-11-01.
 */
public class NaiveBayesOnReviewsTest {

    private static final List<Review> trainingReviews = new ArrayList<Review>();
    private static final List<Review> classificationReviews = new ArrayList<Review>();

    public static void main(String[] args) {
        //Read parameters
        String pathToResources = args[0];
        Double trainingSetPercentage = Double.valueOf(args[1]);

        //Read reviews from files and load into training list and classification list
        loadReviews(pathToResources, trainingSetPercentage);

        NaiveBayes naiveBayes = new NaiveBayes(Category.getNames());

        //Train the algorithm on reviews
        for (Review review : trainingReviews) {
            naiveBayes.train(review.category.name, review.text);
        }

        //Classify reviews and count the amount of correct classifications
        int count = 0;
        String result;
        Map<Category, Integer> correctPerCategory = new HashMap<Category, Integer>();
        Map<Category, Integer> unclassified = new HashMap<Category, Integer>();

        for (Review review : classificationReviews) {
            result = naiveBayes.classify(review.text);
            if (result.equals(review.category.name)) {
                count++;
                int numberOfCorrectClassifications = correctPerCategory.get(review.category) != null ? correctPerCategory.get(review.category) + 1 : 1;
                correctPerCategory.put(review.category, numberOfCorrectClassifications);
            } else if (result.isEmpty()) {
                int numberOfUnclassified = unclassified.get(review.category) != null ? unclassified.get(review.category) + 1 : 1;
                unclassified.put(review.category, numberOfUnclassified);
            }
        }
        System.out.println(count + " out of " + classificationReviews.size() + " is correctly classified!");
        System.out.println("Number of correct classifications per category: ");
        System.out.println(correctPerCategory);
        System.out.println("Number of unclassified per category: ");
        System.out.println(unclassified);
    }

    /**
     * Loads all available reviews from file
     * @param filePath
     */
    private static void loadReviews(String filePath, double trainingSetPercentage) {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();

        double percentage = (trainingSetPercentage * listOfFiles.length);
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".txt")) {
                if (i < percentage) {
                    trainingReviews.add(readReviewFromFile(listOfFiles[i]));
                } else {
                    classificationReviews.add(readReviewFromFile(listOfFiles[i]));
                }
            } else if (listOfFiles[i].isDirectory()) {
                loadReviews(listOfFiles[i].getAbsolutePath(), trainingSetPercentage);
            }
        }
    }

    /**
     * Creates a review based on a file
     * @param file
     * @return
     */
    private static Review readReviewFromFile(File file) {
        // File structure is <category>/neg/<reviews> and <category>/pos/<reviews>
        String categoryName = file.getParentFile().getParentFile().getName();
        Category category = Category.valueOf(categoryName.toUpperCase());
        String text = "";
        try {
            text = readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Review(text, category);
    }

    /**
     * Read content from file
     * @param file
     * @return
     * @throws IOException
     */
    private static String readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
