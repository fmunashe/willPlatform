package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.SpecialBequestDto;
import zw.co.zim.willplatform.processor.SpecialBequestProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.SpecialBequestRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/specialBequests")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SpecialBequestController {
    private final SpecialBequestProcessor processor;

    public SpecialBequestController(SpecialBequestProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<SpecialBequestDto>> getAllSpecialBequests(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<SpecialBequestDto> bequests = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(bequests);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SpecialBequestDto>> createSpecialBequest(@RequestBody @Valid SpecialBequestRequest bequestRequest) {
        ApiResponse<SpecialBequestDto> recordDto = processor.save(bequestRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{bequestId}")
    public ResponseEntity<ApiResponse<SpecialBequestDto>> updateSpecialBequest(@RequestBody @Valid SpecialBequestDto specialBequestDto, @PathVariable("bequestId") Long bequestId) {
        ApiResponse<SpecialBequestDto> recordDto = processor.update(bequestId, specialBequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<SpecialBequestDto>> findFirstByClientId(
        @PathVariable("clientId") Long clientId) {
        ApiResponse<SpecialBequestDto> recordDto = processor.findFirstByUserId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecialBequestDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<SpecialBequestDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{bequestId}")
    public ResponseEntity<ApiResponse<SpecialBequestDto>> deleteSpecialBequestById(
        @PathVariable("bequestId") Long bequestId) {
        ApiResponse<SpecialBequestDto> recordDto = processor.deleteById(bequestId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
