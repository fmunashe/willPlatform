package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.WillBurialRequestDto;
import zw.co.zim.willplatform.processor.WillBurialRequestProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.BurialRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/burialRequest")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WillBurialRequestController {
    private final WillBurialRequestProcessor processor;

    public WillBurialRequestController(WillBurialRequestProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<WillBurialRequestDto>> getAllBurialRequests(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<WillBurialRequestDto> burialRequests = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(burialRequests);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<WillBurialRequestDto>> createBurialRequest(@RequestBody @Valid BurialRequest burialRequest) {
        ApiResponse<WillBurialRequestDto> recordDto = processor.save(burialRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{burialRequestId}")
    public ResponseEntity<ApiResponse<WillBurialRequestDto>> updateSpecialBequest(@RequestBody @Valid WillBurialRequestDto burialRequestDto, @PathVariable("burialRequestId") Long burialRequestId) {
        ApiResponse<WillBurialRequestDto> recordDto = processor.update(burialRequestId, burialRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<WillBurialRequestDto>> findFirstByClientId(
        @PathVariable("clientId") Long clientId) {
        ApiResponse<WillBurialRequestDto> recordDto = processor.findFirstByUserId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WillBurialRequestDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<WillBurialRequestDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{burialRequestId}")
    public ResponseEntity<ApiResponse<WillBurialRequestDto>> deleteBurialRequestById(
        @PathVariable("burialRequestId") Long burialRequestId) {
        ApiResponse<WillBurialRequestDto> recordDto = processor.deleteById(burialRequestId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
