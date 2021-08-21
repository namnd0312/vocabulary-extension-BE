package com.namnd.englishvocabextension.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.namnd.englishvocabextension.models.Vocabulary;


@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
	
	/**
	 * Hàm này trả về danh sách các từ mới trong 1 khoảng thời gian từ
	 *  start date đến end date và progress theo ngày, tuần, tháng
	 * @param startDate
	 * @param endDate
	 * @param progress
	 * @return
	 */
	
	@Query(value = "select * from vocabulary as v where v.progress = ?3 and (v.time_create between ?1 and ?2)", nativeQuery  = true)
	List<Vocabulary> getAllByCreateTimeAndLearnProcess(Instant startDate, Instant endDate, String progress);

}
