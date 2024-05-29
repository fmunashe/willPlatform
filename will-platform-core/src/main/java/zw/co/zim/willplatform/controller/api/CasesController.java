package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.CaseDto;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.processor.CaseServiceProcessor;
import zw.co.zim.willplatform.utils.AppConstants;

@RestController
@RequestMapping("cases")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CasesController {
    private final CaseServiceProcessor processor;

    public CasesController(CaseServiceProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<CaseDto>> getAllCases(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CaseDto> cases = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(cases);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CaseDto>> createCase(@RequestBody @Valid CaseDto caseDto) {
        ApiResponse<CaseDto> recordDto = processor.save(caseDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{caseId}")
    public ResponseEntity<ApiResponse<CaseDto>> updateCase(@RequestBody @Valid CaseDto caseDto, @PathVariable("caseId") Long caseId) {
        ApiResponse<CaseDto> recordDto = processor.update(caseId, caseDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byAssignedAgent/{agentId}")
    public ResponseEntity<ApiResponse<CaseDto>> getAllCasesByAssignedAgent(
        @PathVariable("agentId") Long agentId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CaseDto> recordDto = processor.findAllByAssignedAgent(agentId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseType/{caseType}")
    public ResponseEntity<ApiResponse<CaseDto>> getAllCasesByCaseType(
        @PathVariable("caseType") String caseType,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CaseDto> recordDto = processor.findAllByCaseType(caseType, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseId/{caseId}")
    public ResponseEntity<ApiResponse<CaseDto>> getCaseById(
        @PathVariable("caseId") Long caseId) {
        ApiResponse<CaseDto> recordDto = processor.findById(caseId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCaseNumber/{caseNumber}")
    public ResponseEntity<ApiResponse<CaseDto>> getCaseByNumber(
        @PathVariable("caseNumber") String caseNumber) {
        ApiResponse<CaseDto> recordDto = processor.findFirstByCaseNumber(caseNumber);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byCaseId/{caseId}")
    public ResponseEntity<ApiResponse<CaseDto>> deleteCaseById(
        @PathVariable("caseId") Long caseId) {
        ApiResponse<CaseDto> recordDto = processor.deleteById(caseId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
