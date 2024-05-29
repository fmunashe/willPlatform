package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.processor.BankAssetProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.BankAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/bank")
public class BankAssetController {
    private final BankAssetProcessor bankAssetProcessor;

    public BankAssetController(BankAssetProcessor bankAssetProcessor) {
        this.bankAssetProcessor = bankAssetProcessor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> getAllBankAssets(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<BankAssetRecordDto> bankAssets = bankAssetProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(bankAssets);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> createBankAsset(@RequestBody @Valid BankAssetRequest bankAssetRequest) {
        ApiResponse<BankAssetRecordDto> recordDto = bankAssetProcessor.save(bankAssetRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> updateBankAsset(@PathVariable("id") Long id, @RequestBody BankAssetRecordDto bankAssetRecordDto) {
        ApiResponse<BankAssetRecordDto> recordDto = bankAssetProcessor.update(id, bankAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<BankAssetRecordDto> recordDto = bankAssetProcessor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byAccountNumber/{accountNumber}")
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> findByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        ApiResponse<BankAssetRecordDto> recordDto = bankAssetProcessor.findBankByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> deleteBankAsset(@PathVariable("id") Long id) {
        ApiResponse<BankAssetRecordDto> response = bankAssetProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byClientId{clientId}")
    public ResponseEntity<ApiResponse<BankAssetRecordDto>> findByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<BankAssetRecordDto> recordDto = bankAssetProcessor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
