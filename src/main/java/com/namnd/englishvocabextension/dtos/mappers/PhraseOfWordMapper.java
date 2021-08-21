package com.namnd.englishvocabextension.dtos.mappers;

import com.namnd.englishvocabextension.dtos.PhraseOfWordDto;
import com.namnd.englishvocabextension.models.PhraseOfWord;
import com.namnd.englishvocabextension.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author nam.nd
 * @created 19/08/2021 - 1:59 PM
 */

@Component
public class PhraseOfWordMapper {

    public PhraseOfWord toEntity(PhraseOfWordDto dto){
        if(dto == null){
            return null;
        }

        PhraseOfWord entity = new PhraseOfWord();

        BeanUtils.copyProperties(dto, entity);

        if(!StringUtils.isEmpty(dto.getId())){
            entity.setId(Util.stringToLong(dto.getId()));
        }

        if(!StringUtils.isEmpty(dto.getTimeCreate())){
            entity.setTimeCreate(Util.stringToInstant(dto.getTimeCreate()));
        }
        return entity;
    }

    public PhraseOfWordDto toDto(PhraseOfWord entity){
        if(entity == null){
            return null;
        }

        PhraseOfWordDto dto = new PhraseOfWordDto();

        BeanUtils.copyProperties(entity, dto);

        if(entity.getTimeCreate() != null){
            dto.setTimeCreate(Util.instantToString(entity.getTimeCreate()));
        }

        if(entity.getId() != null){
            dto.setId(Util.longToString(entity.getId()));
        }

        return dto;
    }
}
