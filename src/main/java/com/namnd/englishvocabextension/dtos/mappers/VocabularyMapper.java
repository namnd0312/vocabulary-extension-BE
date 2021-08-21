package com.namnd.englishvocabextension.dtos.mappers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.namnd.englishvocabextension.dtos.VocabularyDto;
import com.namnd.englishvocabextension.models.Vocabulary;
import com.namnd.englishvocabextension.utils.Util;

@Component
public class VocabularyMapper {

	
	 public Vocabulary toEntity(VocabularyDto dto) {
	        if (dto == null) {
	            return null;
	        }

	        Vocabulary temporary = new Vocabulary();

	        if (!StringUtils.isEmpty(dto.getTimeCreate())) {
	            temporary.setTimeCreate(Util.stringToInstant(dto.getTimeCreate()));
	        }

	        BeanUtils.copyProperties(dto, temporary);
	        return temporary;
	    }
	 
	 
	    public VocabularyDto toDto(Vocabulary entity) {
	        if (entity == null) {
	            return null;
	        }
	        VocabularyDto dto = new VocabularyDto();
	        BeanUtils.copyProperties(entity, dto);
	       
	        
	        if(entity.getTimeCreate() != null) {
	        	dto.setTimeCreate(Util.instantToString(entity.getTimeCreate()));
	        }
	        
	        if(entity.getId() != null) {
	        	dto.setId(Util.longToString(entity.getId()));
	        }
	        
	        return dto;
	    }
}
