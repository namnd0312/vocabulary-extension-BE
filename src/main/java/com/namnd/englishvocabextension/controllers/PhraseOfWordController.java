package com.namnd.englishvocabextension.controllers;

import com.namnd.englishvocabextension.dtos.PhraseOfWordDto;
import com.namnd.englishvocabextension.dtos.VocabularyDto;
import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.models.Vocabulary;
import com.namnd.englishvocabextension.services.PhraseOfWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author nam.nd
 * @created 19/08/2021 - 2:45 PM
 */

@RestController
@RequestMapping("/api/phrase")
public class PhraseOfWordController {

    @Autowired
    private PhraseOfWordService phraseOfWordService;

    @PostMapping("/create")
    public ResponseEntity<?> createPhrase(@RequestBody @Valid PhraseOfWordDto dto){
        this.phraseOfWordService.create(dto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePhrase(@RequestBody @Valid PhraseOfWordDto dto){
        this.phraseOfWordService.update(dto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPhrase(){
        List<PhraseOfWord> phraseOfWords = this.phraseOfWordService.getAllBySchedule(EProcess.SECOND_DAY);

        return new ResponseEntity(phraseOfWords, HttpStatus.OK);
    }
}
