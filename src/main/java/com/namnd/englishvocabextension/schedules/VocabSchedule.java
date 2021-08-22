package com.namnd.englishvocabextension.schedules;

import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.services.VocabularyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author nam.nd
 * @created 20/08/2021 - 11:58 PM
 */

@Component
@EnableScheduling
public class VocabSchedule {

    private static final Logger log = LoggerFactory.getLogger(VocabSchedule.class);

    @Autowired
    private VocabularyService vocabularyService;

    @Scheduled(cron = "0 50 14 * * ?")
    private void startSchedulingSendVocabsByEmail(){
        this.vocabularyService.getAllByCreateTimeAndProcess(EProcess.TWO_MONTH);
        this.vocabularyService.getAllByCreateTimeAndProcess(EProcess.A_MONTH);
        this.vocabularyService.getAllByCreateTimeAndProcess(EProcess.TWO_WEEK);
        this.vocabularyService.getAllByCreateTimeAndProcess(EProcess.A_WEEK);
        this.vocabularyService.getAllByCreateTimeAndProcess(EProcess.SECOND_DAY);
    }
}
