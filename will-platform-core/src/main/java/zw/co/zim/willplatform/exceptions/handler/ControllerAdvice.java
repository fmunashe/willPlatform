package zw.co.zim.willplatform.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zw.co.zim.willplatform.exceptions.BusinessException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.messages.response.basic.GenericResponse;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GenericResponse<String>> handleException(BusinessException exception) {
        GenericResponse<String> response = GenericResponse.<String>builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .success(false)
            .message(exception.getMessage())
            .responseDTOS(null)
            .responseDTO(null)
            .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleException(RecordNotFoundException exception) {
        GenericResponse<String> response = GenericResponse.<String>builder()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .success(false)
            .message(exception.getMessage())
            .responseDTOS(null)
            .responseDTO(null)
            .build();
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        GenericResponse<String> response = GenericResponse.<String>builder()
            .statusCode(400)
            .success(false)
            .message("Something went wrong")
            .responseDTOS(errors)
            .responseDTO(null)
            .build();
        return ResponseEntity.ok(response);
    }
}
