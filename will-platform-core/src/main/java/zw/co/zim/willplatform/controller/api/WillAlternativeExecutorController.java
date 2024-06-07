package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.WillAlternativeExecutorDto;
import zw.co.zim.willplatform.processor.WillAlternativeExecutorProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AlternativeExecutorRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/alternativeExecutor")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WillAlternativeExecutorController {

    private final WillAlternativeExecutorProcessor processor;

    public WillAlternativeExecutorController(WillAlternativeExecutorProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<WillAlternativeExecutorDto>> getAllWillExecutors(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<WillAlternativeExecutorDto> willExecutors = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(willExecutors);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<WillAlternativeExecutorDto>> createAlternativeWillExecutor(@RequestBody @Valid AlternativeExecutorRequest alternativeExecutorRequest) {
        ApiResponse<WillAlternativeExecutorDto> recordDto = processor.save(alternativeExecutorRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{willExecutorId}")
    public ResponseEntity<ApiResponse<WillAlternativeExecutorDto>> updateWillAlternativeExecutor(@RequestBody @Valid WillAlternativeExecutorDto willAlternativeExecutorDto, @PathVariable("willExecutorId") Long willExecutorId) {
        ApiResponse<WillAlternativeExecutorDto> recordDto = processor.update(willExecutorId, willAlternativeExecutorDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<WillAlternativeExecutorDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<WillAlternativeExecutorDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WillAlternativeExecutorDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<WillAlternativeExecutorDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{willExecutorId}")
    public ResponseEntity<ApiResponse<WillAlternativeExecutorDto>> deleteWillAlternativeExecutorById(
        @PathVariable("willExecutorId") Long willExecutorId) {
        ApiResponse<WillAlternativeExecutorDto> recordDto = processor.deleteById(willExecutorId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
