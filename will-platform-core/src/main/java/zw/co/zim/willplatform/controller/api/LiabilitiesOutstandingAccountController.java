package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingAccountDto;
import zw.co.zim.willplatform.processor.LiabilitiesOutstandingAccountProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.OutstandingAccountRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/liabilities/outstandingAccounts")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiabilitiesOutstandingAccountController {
    private final LiabilitiesOutstandingAccountProcessor processor;

    public LiabilitiesOutstandingAccountController(LiabilitiesOutstandingAccountProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingAccountDto>> getAllOutstandingAccounts(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<LiabilitiesOutstandingAccountDto> accounts = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingAccountDto>> createOutstandingAccount(@RequestBody @Valid OutstandingAccountRequest accountRequest) {
        ApiResponse<LiabilitiesOutstandingAccountDto> recordDto = processor.save(accountRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingAccountDto>> updateOutstandingAccount(@RequestBody @Valid LiabilitiesOutstandingAccountDto accountDto, @PathVariable("accountId") Long accountId) {
        ApiResponse<LiabilitiesOutstandingAccountDto> recordDto = processor.update(accountId, accountDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingAccountDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<LiabilitiesOutstandingAccountDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingAccountDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<LiabilitiesOutstandingAccountDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byAccountId/{accountId}")
    public ResponseEntity<ApiResponse<LiabilitiesOutstandingAccountDto>> deleteOutstandingAccountById(
        @PathVariable("accountId") Long accountId) {
        ApiResponse<LiabilitiesOutstandingAccountDto> recordDto = processor.deleteById(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
