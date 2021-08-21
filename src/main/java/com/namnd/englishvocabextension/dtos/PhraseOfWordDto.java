package com.namnd.englishvocabextension.dtos;

import com.namnd.englishvocabextension.validators.DateFormatConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author nam.nd
 * @created 19/08/2021 - 1:53 PM
 */
public class PhraseOfWordDto {

    private String id;

    @NotBlank
    private String phrase;

    @NotBlank
    private String meaning;

    @DateFormatConstraint
    @NotEmpty
    private String timeCreate;

    private String progress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
