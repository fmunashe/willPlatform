package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.CaseAllocationDto;
import zw.co.zim.willplatform.processor.CaseAllocationProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/case/allocations")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CaseAllocationController {
    private final CaseAllocationProcessor processor;

    public CaseAllocationController(CaseAllocationProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> getAllCaseAllocations(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CaseAllocationDto> allocations = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(allocations);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> createCaseAllocation(@RequestBody @Valid CaseAllocationDto allocationDto) {
        ApiResponse<CaseAllocationDto> recordDto = processor.save(allocationDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{allocationId}")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> updateCase(@RequestBody @Valid CaseAllocationDto allocationDto, @PathVariable("allocationId") Long allocationId) {
        ApiResponse<CaseAllocationDto> recordDto = processor.update(allocationId, allocationDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<CaseAllocationDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseType/{caseType}")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> getAllCasesByCaseType(
        @PathVariable("caseType") String caseType,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CaseAllocationDto> recordDto = processor.findAllByCaseType(caseType, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseAllocationId/{caseAllocationId}")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> getCaseAllocationById(
        @PathVariable("caseAllocationId") Long allocationId) {
        ApiResponse<CaseAllocationDto> recordDto = processor.findById(allocationId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseId/{caseId}")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> getCaseAllocationByCaseId(
        @PathVariable("caseId") Long caseId) {
        ApiResponse<CaseAllocationDto> recordDto = processor.findFirstByCaseId(caseId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byCaseAllocationId/{caseAllocationId}")
    public ResponseEntity<ApiResponse<CaseAllocationDto>> deleteCaseAllocationById(
        @PathVariable("caseAllocationId") Long caseId) {
        ApiResponse<CaseAllocationDto> recordDto = processor.deleteById(caseId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
