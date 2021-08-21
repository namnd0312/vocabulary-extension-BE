package com.namnd.englishvocabextension.services;

import com.namnd.englishvocabextension.dtos.VocabularyDto;
import com.namnd.englishvocabextension.enums.EProcess;
import com.namnd.englishvocabextension.models.Vocabulary;

import java.util.List;

public interface VocabularyService {
	
	Vocabulary create(VocabularyDto dto);
	
	Vocabulary update(VocabularyDto dto);
	
	void deleteById(String id);

	VocabularyDto findById(String id);
	
	List<Vocabulary> getAllByCreateTimeAndProcess(EProcess process);
	
}
