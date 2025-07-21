package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.processor.AssetInvestmentProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AssetInvestmentRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/assetInvestments")
public class AssetInvestmentController {

    private final AssetInvestmentProcessor processor;

    public AssetInvestmentController(AssetInvestmentProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> getAllInvestmentAssets(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<AssetInvestmentRecordDto> investmentRecordDtos = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(investmentRecordDtos);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> createInvestmentAsset(@RequestBody @Valid AssetInvestmentRequest investmentRequest) {
        ApiResponse<AssetInvestmentRecordDto> recordDto = processor.save(investmentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> updateInvestmentAsset(@PathVariable("id") Long id, @RequestBody AssetInvestmentRecordDto assetInvestmentRecordDto) {
        ApiResponse<AssetInvestmentRecordDto> recordDto = processor.update(id, assetInvestmentRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetInvestmentRecordDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byInvestmentType{type}")
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> findByInvestmentType(
        @PathVariable("type") String investmentType,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetInvestmentRecordDto> recordDto = processor.findAllByInvestmentType(investmentType, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId{id}")
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> findByClientId(
        @PathVariable("id") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetInvestmentRecordDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetInvestmentRecordDto>> deleteInvestmentAsset(@PathVariable("id") Long id) {
        ApiResponse<AssetInvestmentRecordDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
