# machine-learning-classification
## An Introduction to Machine Learning Classification
What happens when you train the algorithm?
Simply put, you let the algorithm look at part of your data set, and then for each data point it will learn the correct answer.

### Introduction to Naive Bayes

### Data set used in tutorial

## Tutorial

### 1. Install Java 
Install Java 1.6 or greater (preferable latest)

### 2. Get the code
Either clone with git or download zip from github
`git clone whatever` and then `git checkout tutorial`
Or download zip from <copylinkfromgithubandpaste>

### 3. Unzip resources
Unzip machine-learning-classification/resources/amazon-balanced-6cats.zip into the same directory

### 4. Test dummy classifier
Test running the classifier from the command line
- Go to `cd <path-to-project>/machine-learning-classification/src/`
- Compile with `javac *.java`
- Run with `java NaiveBayesOnReviewsTest <path-to-project>/machine-learning-classification/resources/amazon-balanced-6cats/ <training-set-size-in-percentage>`
    - Example `java NaiveBayesOnReviewsTest /home/myuser/code/machine-learning-classification/resources/amazon-balanced-6cats/ 0.5`

You've now run your classifier, as you can see, it's not that smart yet.. :)
Let's improve it!

### 5. Implement trainer
The goal of your trainer is to extract statistics of the vocabulary for each category in the training set.
Take a look at the following pseudo code

```
function train(category, document)
     word_occurrences = occurrences_per_word_and_category[category]
     for each word in document
        word_occurrences[word] += 1
     end
     number_of_words_per_category[category] += words.length
     documents_per_category[category] += 1
     total_words += words.length
     total_documents += 1
 end function
```

We want to keep track of how many times a word occurs for each category. So we traverse through all words in the document, and increase the counter per word at each occurrence.

Now implement this method in machine-learning-classification/src/NaiveBayes.java

### 6. Implement classifier
The goal of your classifier is to make use of the statistics created in the training method. Based on this information it will then try to classify a provided document.
We have split the classifier into several methods, to increase readability.

```
function classify(document)
    for each category in categories
        probability = probability(category, document)
        if probability > best_probability
            best_probability = probability
            best_category = category
        end
        return best_category
    end
end function
```

```
function probability(category, document)
    return document_belongs_to_category(category, document) * is_category(category)
end function
```

```
function isCategoryProbability(category)
    return documents_per_category[category] / total_documents
end function
```

```
function documentBelongsToCategoryProbability(category, document)
    probability = 1
    for each word in words
        probability = probability * word_belongs_to_category(category, word)
    end
    return probability
end function
```

```
function wordBelongsToCategoryProbability(category, word)
    word_count = occurrences_per_word_and_category[category][word]
    word_count += 1 //To ensure that the probability is never zero
    return word_count / number_of_words_per_category[category]
end function
```

### 7. Test classifier
Redo step 4.

#### 7a. Still not satisfied with the result?
There are various ways to improve your result.
You can experiment with the size of the training set by changing the `<training-set-size-in-percentage>` passed to the classifier.
You probably also have seen the method `parseWords` in NaiveBayes.java. There are also suggestions of a few steps of improvements that can be done in order to increase the correctness of the result.
