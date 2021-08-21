package com.namnd.englishvocabextension.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class VocabularyDto {

	private String id;

	@NotBlank
	@NotNull
	private String word;

	@NotBlank
	private String meaning;

	@NotBlank
	private String transcription;

	private String timeCreate;

	private String positiveSentence;
	
	private String negativeSentence;
	
	private String questionSentence;
	
	private String progress;

	@NotBlank
	private String typeOfWord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
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
