package com.namnd.englishvocabextension.services;

import com.namnd.englishvocabextension.dtos.PhraseOfWordDto;
import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.models.PhraseOfWord;

import java.util.List;

/**
 * @author nam.nd
 * @created 19/08/2021 - 1:31 PM
 */
public interface PhraseOfWordService {

    void create(PhraseOfWordDto phraseOfWordDto);

    void update(PhraseOfWordDto phraseOfWordDto);

    PhraseOfWordDto findById(String id);

    List<PhraseOfWord> getAllBySchedule(EProcess process);
}
