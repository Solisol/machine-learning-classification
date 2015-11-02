# machine-learning-classification
## An Introduction to Machine Learning Classification
Machine learning is used to solve various problems of predictions. With the help of machine learning it's possible to predict things like waiting time in emergency rooms, equipment failures and detect network intrusion.

It's all about training an algorithm on training data and then letting it apply it's knowledge in making predictions about unknown data.
One common use is the prediction of spam mails. By teaching an algorithm the characteristics of a spam mail, it can then predict if an incoming mail is spam and set that classification to the mail. This is the type of machine learning we'll look into in this tutorial; Machine Learning Classification.

So what is the goal with machine learning classification? Given an input, you want to be able to classify it as belonging to one of all known categories. In the case of spam, your input is the email with its topic, message, sender and all information accessible in that email. Given that input the classifier should classify it as either of the categories spam and not_spam.

How is that done? It starts with teaching the classifier how emails from the different categories may look. When it has learned enough, it can be used to classify email based on what it has learned.

### Introduction to Naive Bayes

### Data set used in tutorial

## Tutorial

### 1. Install Java 
[Install Java 1.6](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or greater (preferable latest)

### 2. Get the code
Either clone with git or download zip from github
`git clone git@github.com:Solisol/machine-learning-classification.git` and then `git checkout tutorial`
Or download zip from [github](https://github.com/Solisol/machine-learning-classification/archive/tutorial.zip).

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
