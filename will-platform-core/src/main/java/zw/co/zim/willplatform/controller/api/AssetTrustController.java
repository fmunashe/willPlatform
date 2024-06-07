package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.processor.AssetTrustProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AssetTrustRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/assetTrusts")
public class AssetTrustController {
    private final AssetTrustProcessor processor;

    public AssetTrustController(AssetTrustProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetTrustDto>> getAllTrustAssets(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<AssetTrustDto> assetTrusts = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(assetTrusts);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetTrustDto>> createTrustAsset(@RequestBody @Valid AssetTrustRequest trustRequest) {
        ApiResponse<AssetTrustDto> recordDto = processor.save(trustRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetTrustDto>> updateTrustAsset(@PathVariable("id") Long id, @RequestBody AssetTrustDto assetTrustDto) {
        ApiResponse<AssetTrustDto> recordDto = processor.update(id, assetTrustDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetTrustDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetTrustDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId{id}")
    public ResponseEntity<ApiResponse<AssetTrustDto>> findByClientId(
        @PathVariable("id") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetTrustDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetTrustDto>> deleteTimeshare(@PathVariable("id") Long id) {
        ApiResponse<AssetTrustDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
