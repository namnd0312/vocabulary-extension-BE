package com.namnd.englishvocabextension.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vocabulary")
public class Vocabulary implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "word", length = 100, nullable = false)
    private String word;

    @Column(name = "meaning", length = 100, nullable = false)
    private String meaning;

    @Column(name = "transcription", length = 100, nullable = false)
    private String transcription;

    @Column(name = "time_create", nullable = false)
    private Instant timeCreate;

    @Column(name = "positive_sentence", length = 200)
    private String positiveSentence;

    @Column(name = "negative_sentence", length = 200)
    private String negativeSentence;

    @Column(name = "question_sentence", length = 200)
    private String questionSentence;

    @Column(name = "progress",  nullable = false, length = 100)
    private String progress;

    @Column(name = "type_of_word",  nullable = false,  length = 5)
    private String typeOfWord;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public Instant getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Instant timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getPositiveSentence() {
        return positiveSentence;
    }

    public void setPositiveSentence(String positiveSentence) {
        this.positiveSentence = positiveSentence;
    }

    public String getNegativeSentence() {
        return negativeSentence;
    }

    public void setNegativeSentence(String negativeSentence) {
        this.negativeSentence = negativeSentence;
    }

    public String getQuestionSentence() {
        return questionSentence;
    }

    public void setQuestionSentence(String questionSentence) {
        this.questionSentence = questionSentence;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getTypeOfWord() {
        return typeOfWord;
    }

    public void setTypeOfWord(String typeOfWord) {
        this.typeOfWord = typeOfWord;
    }
}
