
package com.namnd.englishvocabextension.controllers;

import java.util.List;

import com.namnd.englishvocabextension.enums.EProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.namnd.englishvocabextension.dtos.VocabularyDto;
import com.namnd.englishvocabextension.models.Vocabulary;
import com.namnd.englishvocabextension.services.VocabularyService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/vocab")
public class VocabularyController {
	
	@Autowired
	private VocabularyService vocabularyService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createVocab(@Valid @RequestBody VocabularyDto dto){
		this.vocabularyService.create(dto);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateVocab(@RequestBody @Valid VocabularyDto dto){
		this.vocabularyService.update(dto);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getVocab(){
		List<Vocabulary> vocabularies = this.vocabularyService.getAllByCreateTimeAndProcess(EProcess.SECOND_DAY);
		
		return new ResponseEntity(vocabularies, HttpStatus.OK);
	}

}
