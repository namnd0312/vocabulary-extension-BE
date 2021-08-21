package com.namnd.englishvocabextension.repositories;

import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.models.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * @author nam.nd
 * @created 19/08/2021 - 1:32 PM
 */

@Repository
public interface PhraseOfWordRepository extends JpaRepository<PhraseOfWord, Long> {

    /**
     * Hàm này trả về danh sách các cụm từ mới trong 1 khoảng thời gian từ
     * start date đến end date và progress theo ngày, tuần, tháng
     * @param startDate
     * @param endDate
     * @param progress
     * @return
     */
    @Query(value = "select * from phrase_of_word as p where p.progress = ?3 and (p.time_create between ?1 and ?2)", nativeQuery  = true)
    List<PhraseOfWord> getAllPhraseBySchedule(Instant startDate, Instant endDate, String progress);
}
