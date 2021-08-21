package com.namnd.englishvocabextension.services.impl;

import com.namnd.englishvocabextension.dtos.PhraseOfWordDto;
import com.namnd.englishvocabextension.dtos.mappers.PhraseOfWordMapper;
import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.exceptions.LogicException;
import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.repositories.PhraseOfWordRepository;
import com.namnd.englishvocabextension.services.EmailService;
import com.namnd.englishvocabextension.services.PhraseOfWordService;
import com.namnd.englishvocabextension.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.namnd.englishvocabextension.enums.EProcess.*;
import static com.namnd.englishvocabextension.enums.MessageEnum.BAD_REQUEST;
import static com.namnd.englishvocabextension.enums.MessageEnum.RECORD_NOT_FOUND;

/**
 * @author nam.nd
 * @created 19/08/2021 - 1:31 PM
 */

@Service
public class PhraseOfWordServiceImpl implements PhraseOfWordService {

    @Autowired
    private PhraseOfWordRepository phraseOfWordRepository;

    @Autowired
    private PhraseOfWordMapper phraseOfWordMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public void create(PhraseOfWordDto phraseOfWordDto) {
        PhraseOfWord entity = this.phraseOfWordMapper.toEntity(phraseOfWordDto);
        entity.setProgress(SECOND_DAY.name());
        this.phraseOfWordRepository.save(entity);
    }

    @Override
    public void update(PhraseOfWordDto phraseOfWordDto) {

        Long id = Util.stringToLong(phraseOfWordDto.getId());

        if(id == null){
            throw new LogicException(BAD_REQUEST);
        }

        Optional<PhraseOfWord> phraseOfWord = this.phraseOfWordRepository.findById(id);

        if(!phraseOfWord.isPresent()){
            throw new LogicException(RECORD_NOT_FOUND);
        }

        PhraseOfWord entity = this.phraseOfWordMapper.toEntity(phraseOfWordDto);
        entity.setProgress(SECOND_DAY.name());
        this.phraseOfWordRepository.save(entity);
    }

    @Override
    public PhraseOfWordDto findById(String id) {

        Optional<PhraseOfWord> phraseOfWord = this.phraseOfWordRepository.findById(Util.stringToLong(id));

        if(!phraseOfWord.isPresent()){
            throw new LogicException(RECORD_NOT_FOUND);
        }

        return phraseOfWordMapper.toDto(phraseOfWord.get());
    }

    @Transactional
    @Override
    public List<PhraseOfWord> getAllBySchedule(EProcess process) {
        int theDays;
        String typePracticedProcess;
        switch (process){
            case SECOND_DAY:
                theDays = 1;
                typePracticedProcess = SECOND_DAY.name();
                break;
            case A_WEEK:
                theDays = 7;
                typePracticedProcess = EProcess.A_WEEK.name();
                break;
            case TWO_WEEK:
                theDays = 14;
                typePracticedProcess = TWO_WEEK.name();
                break;
            case A_MONTH:
                theDays = 30;
                typePracticedProcess = A_MONTH.name();
                break;
            case TWO_MONTH:
                theDays = 60;
                typePracticedProcess = EProcess.TWO_MONTH.name();
                break;

            default: return new ArrayList<>();
        }

        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(theDays));
        Instant dateBefore = Instant.from(before);
        List<PhraseOfWord> phraseOfWords = this.phraseOfWordRepository.getAllPhraseBySchedule(dateBefore, now, typePracticedProcess);

        if(!phraseOfWords.isEmpty()){
            this.emailService.sendMailPhraseOfWord(phraseOfWords, process);
            String nextProcess = Util.getScheduleForFuture(process);
            phraseOfWords.forEach(i -> i.setProgress(nextProcess));
            this.phraseOfWordRepository.saveAll(phraseOfWords);
        }

        return phraseOfWords;
    }
}
