package com.namnd.englishvocabextension.exceptions;

import com.namnd.englishvocabextension.dtos.ResponseDto;
import com.namnd.englishvocabextension.enums.MessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionTranslator extends RequestBodyAdviceAdapter {
    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);
    private HttpServletRequest request;


    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
//
//    @Override
//    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
//                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//        if (!request.getRequestURI().contains("medias")) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                log.info("[URI: {}][REQUEST_BODY]: {}",
//                        this.request.getRequestURI(), mapper.writeValueAsString(body));
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//
//        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
//    }

    /**
     Handle common exception all system;
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> processRuntimeException(Exception e) {
        log.error(e.getMessage(), e);
        ResponseDto response = new ResponseDto(MessageEnum.INTERNAL_API_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
        Handle exception for validate input data field and custom validation;
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getField() + " " + e.getDefaultMessage()));

        String description = String.join("#", new ArrayList<>(errors.values()));

        String code = "501";
        ResponseDto response = new ResponseDto(code, description, Instant.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(LogicException.class)
    public ResponseEntity<?> logicException(LogicException ex) {
        MessageEnum messageEnum = ex.getMessageEnum();
        ResponseDto response = new ResponseDto(messageEnum);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
