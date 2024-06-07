package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.WillCommentsDto;
import zw.co.zim.willplatform.processor.WillCommentsProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.WillCommentsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/willComments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WillCommentsInstructionsController {
    private final WillCommentsProcessor processor;

    public WillCommentsInstructionsController(WillCommentsProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<WillCommentsDto>> getAllWillComments(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<WillCommentsDto> comments = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<WillCommentsDto>> createWillComments(@RequestBody @Valid WillCommentsRequest commentsRequest) {
        ApiResponse<WillCommentsDto> recordDto = processor.save(commentsRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{commentsId}")
    public ResponseEntity<ApiResponse<WillCommentsDto>> updateWillComments(@RequestBody @Valid WillCommentsDto willCommentsDto, @PathVariable("commentsId") Long commentsId) {
        ApiResponse<WillCommentsDto> recordDto = processor.update(commentsId, willCommentsDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<WillCommentsDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<WillCommentsDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WillCommentsDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<WillCommentsDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{commentsId}")
    public ResponseEntity<ApiResponse<WillCommentsDto>> deleteWillCommentsById(
        @PathVariable("commentsId") Long commentsId) {
        ApiResponse<WillCommentsDto> recordDto = processor.deleteById(commentsId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
