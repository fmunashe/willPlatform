package zw.co.zim.willplatform.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zw.co.zim.willplatform.exceptions.BusinessException;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.basic.GenericResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<String>> handleException(BusinessException exception) {
        ApiResponse<String> response = HelperResponse.buildApiResponse(null, null, false, 400, false, exception.getMessage(), null);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleException(RecordNotFoundException exception) {
        ApiResponse<String> response = HelperResponse.buildApiResponse(null, null, false, 404, false, exception.getMessage(), null);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleException(RecordExistsException exception) {
        ApiResponse<String> response = HelperResponse.buildApiResponse(null, null, false, 400, false, exception.getMessage(), null);
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
