package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.processor.PropertyAssetProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.PropertyAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/property")
public class PropertyAssetController {
    private final PropertyAssetProcessor propertyAssetProcessor;

    public PropertyAssetController(PropertyAssetProcessor propertyAssetProcessor) {
        this.propertyAssetProcessor = propertyAssetProcessor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PropertyAssetRecordDto>> getAllPropertyAssets(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<PropertyAssetRecordDto> propertyAssets = propertyAssetProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(propertyAssets);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<PropertyAssetRecordDto>> createPropertyAsset(@RequestBody @Valid PropertyAssetRequest propertyAssetRequest) {
        ApiResponse<PropertyAssetRecordDto> recordDto = propertyAssetProcessor.save(propertyAssetRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<PropertyAssetRecordDto>> updatePropertyAsset(@PathVariable("id") Long id, @RequestBody PropertyAssetRecordDto propertyAssetRecordDto) {
        ApiResponse<PropertyAssetRecordDto> recordDto = propertyAssetProcessor.update(id, propertyAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyAssetRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<PropertyAssetRecordDto> recordDto = propertyAssetProcessor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyAssetRecordDto>> deletePropertyAsset(@PathVariable("id") Long id) {
        ApiResponse<PropertyAssetRecordDto> response = propertyAssetProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byClientId{clientId}")
    public ResponseEntity<ApiResponse<PropertyAssetRecordDto>> findByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<PropertyAssetRecordDto> recordDto = propertyAssetProcessor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
