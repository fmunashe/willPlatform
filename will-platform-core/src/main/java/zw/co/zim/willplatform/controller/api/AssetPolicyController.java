package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.processor.AssetPolicyProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AssetPolicyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/assetPolicies")
public class AssetPolicyController {

    private final AssetPolicyProcessor processor;

    public AssetPolicyController(AssetPolicyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> getAllPolicies(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<AssetPolicyRecordDto> policies = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(policies);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> createAssetPolicy(@RequestBody @Valid AssetPolicyRequest policyRequest) {
        ApiResponse<AssetPolicyRecordDto> recordDto = processor.save(policyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> updateAssetPolicy(@PathVariable("id") Long id, @RequestBody AssetPolicyRecordDto policyRecordDto) {
        ApiResponse<AssetPolicyRecordDto> recordDto = processor.update(id, policyRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetPolicyRecordDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/number/{policyNumber}")
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> findById(@PathVariable("policyNumber") String policyNumber) {
        ApiResponse<AssetPolicyRecordDto> recordDto = processor.findFirstByPolicyNumber(policyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> findByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<AssetPolicyRecordDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetPolicyRecordDto>> deleteAssetPolicy(@PathVariable("id") Long id) {
        ApiResponse<AssetPolicyRecordDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
