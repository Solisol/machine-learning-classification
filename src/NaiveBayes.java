import java.util.*;

/**
 * Created by sol on 2015-10-21.
 */
public class NaiveBayes {

    //All possible categories
    Set<String> categories;

    //Total word count
    int totalWords = 0;
    //Total document count
    int totalDocuments = 0;
    //Number of times a certain word occured per category
    Map<String,Map<String, Integer>> occurrencesPerWordAndCategory;
    //Amount of documents per category
    Map<String, Integer> documentsPerCategory;
    //Amount of words per category
    Map<String, Integer> numberOfWordsPerCategory;
    //Filler words
    Set<String> stopWords;

    private static final String[] STOP_WORDS = {"a","about","above","after","again","against","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only","or","other","ought","our","oursourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves"};

    public NaiveBayes(Set<String> categories) {
        this.categories = categories;
        this.occurrencesPerWordAndCategory = new HashMap<String,Map<String, Integer>>();
        this.documentsPerCategory = new HashMap<String, Integer>();
        this.numberOfWordsPerCategory = new HashMap<String, Integer>();
        this.stopWords = new HashSet<String>(Arrays.asList(STOP_WORDS));
        setupCounters();
    }

    /**
     * Setup the neccessary counters with default values.
     */
    private void setupCounters() {
        for (String category : categories) {
            occurrencesPerWordAndCategory.put(category, new HashMap<String, Integer>());
            numberOfWordsPerCategory.put(category, 0);
            documentsPerCategory.put(category, 0);
        }
    }

    /**
    * Adds the given document to the training set
    * Pseudo code:
    *   word_occurrences = occurrences_per_word_and_category[category]
    *   for each word in document
    *       word_occurrences[word] += 1
    *   end
    *   number_of_words_per_category[category] += words.length
    *   documents_per_category[category] += 1
    *   total_words += words.length
    *   total_documents += 1
    * @param category, The category of the document
    * @param document, The document to add to the training set
    */
    public void train(String category, String document){
        // TODO implement train
    }

    /**
    * Takes a document and returns the category with its best probability
    * Pseudo code:
    *   for each category in categories
    *       probability = probability(category, document)
    *       if probability > best_probability
    *           best_probability = probability
    *           best_category = category
    *       end
    *       return best_category
    *   end
    *
    * @param document, The document to classify
    */
    public String classify(String document){
        //TODO implement classify
        return "";
    }

    /**
    * Calculates the probability of the document for a given category
    * Pseudo code:
    *   return document_belongs_to_category(category, document) * is_category(category)
    * @param category, the category for which the document should be evaluated
    * @param document, The document which is being classified
    */
    private double probability(String category, String document) {
        return 0; //Placeholder
    }

    /**
    * Calculates the probability that any document is of the given category
    * Pseudo code:
    *   return documents_per_category[category] / total_documents
    * @param category, The category for which the probability is calculated
    */
    private double isCategoryProbability(String category) {
        return 0; //Placeholder
    }

    /**
    * Returns the probability that a document belongs to a given category
    * Pseudo code:
    *   probability = 1
    *   for each word in words
    *       probability = probability * word_belongs_to_category(category, word)
    *   end
    *   return probability
    * @param category, The category for which the document is evaluated for
    * @param document, The document which is being classified
    */
    private double documentBelongsToCategoryProbability(String category, String document) {
        return 0; //Placeholder
    }

    /**
    * Returns the probability that a word belongs to a given category
    * Pseudo code:
    *   word_count = occurrences_per_word_and_category[category][word]
    *   return word_count + 1/ number_of_words_per_category[category] + 1
    * @param category, The category for which the word will be evaluated
    * @params word, The word which is being classified
    */
    private double wordBelongsToCategoryProbability(String category, String word) {
        return 0; //Placeholder
    }

    /**
    * Parses the words of the document.
    * Pseudo code (basic):
    *   return document.split
    *
    * Pseudo code improvement 1 (to lower case):
    *   all_words = document.split
    *   words = []
    *   for each word in all_words
    *       word = word.to_lower_case
    *       words.add(word)
    *   end
    *
    * Pseudo code improvement 2 (exclude non alphabetical characters):
    *   all_words = document.split
    *   words = []
    *   for each word in all_words
    *       word = word.to_lower_case
    *       word = word.remove_non_alphabetical_characters (regex example [^a-zA-Z\'])
    *       words.add(word)
    *   end
    *
    * Pseudo code improvement 3 (exclude stop words):
    *   all_words = document.split
    *   words = []
    *   for each word in words
    *       word = word.to_lower_case
    *       word = word.remove_non_alphabetical_characters
    *       if !stop_words.contains(word)
    *           words.add(word)
    *       end
    *   end
    * @param document, The document to be parsed.
    */
    private String[] parseWords(String document) {
        String[] allWords = document.split(" ");
        return allWords;
    }
}
