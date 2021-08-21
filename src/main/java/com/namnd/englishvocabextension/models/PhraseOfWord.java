package com.namnd.englishvocabextension.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author nam.nd
 * @created 19/08/2021 - 1:25 PM
 */

@Entity
@Table(name = "phrase_of_word")
public class PhraseOfWord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phrase", nullable = false, length = 200)
    private String phrase;

    @Column(name = "meaning", nullable = false, length = 200)
    private String meaning;

    @Column(name = "time_create", nullable = false)
    private Instant timeCreate;

    @Column(name = "progress", length = 100,  nullable = false)
    private String progress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Instant getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Instant timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
