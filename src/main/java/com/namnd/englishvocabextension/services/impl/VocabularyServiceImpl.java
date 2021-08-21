package com.namnd.englishvocabextension.services.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.enums.MessageEnum;
import com.namnd.englishvocabextension.exceptions.LogicException;
import com.namnd.englishvocabextension.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namnd.englishvocabextension.dtos.VocabularyDto;
import com.namnd.englishvocabextension.dtos.mappers.VocabularyMapper;
import com.namnd.englishvocabextension.models.Vocabulary;
import com.namnd.englishvocabextension.repositories.VocabularyRepository;
import com.namnd.englishvocabextension.services.VocabularyService;
import com.namnd.englishvocabextension.utils.Util;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public Vocabulary create(VocabularyDto dto) {

        Vocabulary vocabularyMapper = this.vocabularyMapper.toEntity(dto);
        vocabularyMapper.setTimeCreate(Instant.now());
        vocabularyMapper.setProgress(EProcess.SECOND_DAY.name());

        return this.vocabularyRepository.save(vocabularyMapper);
    }

    @Override
    public void deleteById(String id) {
        Optional<Vocabulary> vocab = this.vocabularyRepository.findById(Util.stringToLong(id));

        if (vocab.isPresent()) {
            this.vocabularyRepository.deleteById(Util.stringToLong(id));
        }
    }

    @Override
    public VocabularyDto findById(String id) {
        return this.vocabularyRepository.findById(Util.stringToLong(id)).map(vocabularyMapper::toDto).get();
    }

    @Override
    public Vocabulary update(VocabularyDto dto) {
        Optional<Vocabulary> vocab = this.vocabularyRepository.findById(Util.stringToLong(dto.getId()));

        if (!vocab.isPresent()) {
          throw new LogicException(MessageEnum.RECORD_NOT_FOUND);
        }

        Vocabulary VocabularyMapper = vocabularyMapper.toEntity(dto);
        Vocabulary vocabulary = this.vocabularyRepository.save(VocabularyMapper);
        return vocabulary;
    }

    @Override
    public List<Vocabulary> getAllByCreateTimeAndProcess(EProcess process) {

        int theDays;
        String typePracticedProcess;
        switch (process){
            case SECOND_DAY:
                theDays = 1;
                typePracticedProcess = EProcess.SECOND_DAY.name();
                break;
            case A_WEEK:
                theDays = 7;
                typePracticedProcess = EProcess.A_WEEK.name();
                break;
            case TWO_WEEK:
                theDays = 14;
                typePracticedProcess = EProcess.TWO_WEEK.name();
                break;
            case A_MONTH:
                theDays = 30;
                typePracticedProcess = EProcess.A_MONTH.name();
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

        List<Vocabulary> vocabularies = this.vocabularyRepository.getAllByCreateTimeAndLearnProcess(dateBefore, now, typePracticedProcess);

        if(!vocabularies.isEmpty()){
            this.emailService.sendMail(vocabularies, process);
            String nextProcess = Util.getScheduleForFuture(process);
            vocabularies.forEach(i -> i.setProgress(nextProcess));
            this.vocabularyRepository.saveAll(vocabularies);
        }

        return vocabularies;
    }
}
