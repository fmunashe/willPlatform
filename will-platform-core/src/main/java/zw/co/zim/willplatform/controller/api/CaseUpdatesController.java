package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.CaseUpdatesDto;
import zw.co.zim.willplatform.processor.CaseUpdatesProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/case/updates")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CaseUpdatesController {
    private final CaseUpdatesProcessor processor;

    public CaseUpdatesController(CaseUpdatesProcessor processor) {
        this.processor = processor;
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<CaseUpdatesDto>> getAllCaseUpdates(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CaseUpdatesDto> allocations = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(allocations);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CaseUpdatesDto>> createCaseAUpdate(@RequestBody @Valid CaseUpdatesDto updatesDto) {
        ApiResponse<CaseUpdatesDto> recordDto = processor.save(updatesDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{updateId}")
    public ResponseEntity<ApiResponse<CaseUpdatesDto>> updateCaseUpdate(@RequestBody @Valid CaseUpdatesDto updatesDto, @PathVariable("updateId") Long updateId) {
        ApiResponse<CaseUpdatesDto> recordDto = processor.update(updateId, updatesDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CaseUpdatesDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<CaseUpdatesDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseId/{caseId}")
    public ResponseEntity<ApiResponse<CaseUpdatesDto>> findAllUpdatesByCaseId(
        @PathVariable("caseId") Long caseId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<CaseUpdatesDto> recordDto = processor.findAllByCaseId(caseId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byCaseUpdateId/{caseUpdateId}")
    public ResponseEntity<ApiResponse<CaseUpdatesDto>> deleteCaseUpdateById(
        @PathVariable("caseUpdateId") Long caseUpdateId) {
        ApiResponse<CaseUpdatesDto> recordDto = processor.deleteById(caseUpdateId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
