package zw.co.zim.willplatform.controller.api;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingLoanDto;
import zw.co.zim.willplatform.processor.LiabilitiesOutstandingLoanProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.OutstandingLoanRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/liabilities/outstandingLoans")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiabilitiesOutstandingLoanController {
    private final LiabilitiesOutstandingLoanProcessor processor;

    public LiabilitiesOutstandingLoanController(LiabilitiesOutstandingLoanProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingLoanDto>> getAllOutstandingLoans(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<LiabilitiesOutstandingLoanDto> loans = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(loans);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingLoanDto>> createOutstandingLoan(@RequestBody @Valid OutstandingLoanRequest loanRequest) {
        ApiResponse<LiabilitiesOutstandingLoanDto> recordDto = processor.save(loanRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{loanId}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingLoanDto>> updateOutstandingLoan(@RequestBody @Valid LiabilitiesOutstandingLoanDto loanDto, @PathVariable("loanId") Long loanId) {
        ApiResponse<LiabilitiesOutstandingLoanDto> recordDto = processor.update(loanId, loanDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingLoanDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<LiabilitiesOutstandingLoanDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingLoanDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<LiabilitiesOutstandingLoanDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byLoanId/{loanId}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingLoanDto>> deleteOutstandingLoanById(
        @PathVariable("loanId") Long loanId) {
        ApiResponse<LiabilitiesOutstandingLoanDto> recordDto = processor.deleteById(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
