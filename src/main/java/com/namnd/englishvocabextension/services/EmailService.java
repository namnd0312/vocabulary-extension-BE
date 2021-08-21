package com.namnd.englishvocabextension.services;

import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.models.Vocabulary;

import java.util.List;

/**
 * @author nam.nd
 * @created 18/08/2021 - 12:25 AM
 */
public interface EmailService {

    void sendMail(List<Vocabulary> vocabularies, EProcess process);
    void sendMailPhraseOfWord(List<PhraseOfWord> phraseOfWords, EProcess process);
}
