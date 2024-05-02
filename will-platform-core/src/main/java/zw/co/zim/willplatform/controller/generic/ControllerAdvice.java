package zw.co.zim.willplatform.controller.generic;

import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleException(RecordNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<String> handleRecordAlreadyExistsException(RecordExistsException exception) {
        return ResponseEntity.status(HttpStatus.FOUND).body(exception.getMessage());
    }
}
