package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.WillPasswordInstructionsDto;
import zw.co.zim.willplatform.processor.WillPasswordInstructionsProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.WillPasswordInstructionsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/passwordInstructions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WillPasswordInstructionsController {

    private final WillPasswordInstructionsProcessor processor;

    public WillPasswordInstructionsController(WillPasswordInstructionsProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<WillPasswordInstructionsDto>> getAllWillPasswordInstructions(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<WillPasswordInstructionsDto> instructions = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(instructions);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<WillPasswordInstructionsDto>> createWillPasswordInstructions(@RequestBody @Valid WillPasswordInstructionsRequest passwordInstructionsRequest) {
        ApiResponse<WillPasswordInstructionsDto> recordDto = processor.save(passwordInstructionsRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{instructionId}")
    public ResponseEntity<ApiResponse<WillPasswordInstructionsDto>> updateWillPasswordInstructions(@RequestBody @Valid WillPasswordInstructionsDto passwordInstructionsDto, @PathVariable("instructionId") Long instructionId) {
        ApiResponse<WillPasswordInstructionsDto> recordDto = processor.update(instructionId, passwordInstructionsDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<WillPasswordInstructionsDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<WillPasswordInstructionsDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WillPasswordInstructionsDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<WillPasswordInstructionsDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{instructionId}")
    public ResponseEntity<ApiResponse<WillPasswordInstructionsDto>> deleteWillPasswordInstructionsById(
        @PathVariable("instructionId") Long instructionId) {
        ApiResponse<WillPasswordInstructionsDto> recordDto = processor.deleteById(instructionId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
