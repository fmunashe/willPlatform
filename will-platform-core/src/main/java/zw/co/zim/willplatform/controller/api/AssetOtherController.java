package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.processor.AssetOtherProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AssetOtherRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/assetOther")
public class AssetOtherController {
    private final AssetOtherProcessor processor;

    public AssetOtherController(AssetOtherProcessor processor) {
        this.processor = processor;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<AssetOtherRecordDto>> getAllOtherAssets(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<AssetOtherRecordDto> assetOther = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(assetOther);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetOtherRecordDto>> createOtherAsset(@RequestBody @Valid AssetOtherRequest assetOtherRequest) {
        ApiResponse<AssetOtherRecordDto> recordDto = processor.save(assetOtherRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetOtherRecordDto>> updateOtherAsset(@PathVariable("id") Long id, @RequestBody AssetOtherRecordDto assetOtherRecordDto) {
        ApiResponse<AssetOtherRecordDto> recordDto = processor.update(id, assetOtherRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetOtherRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetOtherRecordDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId{id}")
    public ResponseEntity<ApiResponse<AssetOtherRecordDto>> findByClientId(
        @PathVariable("id") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetOtherRecordDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetOtherRecordDto>> deleteOtherAsset(@PathVariable("id") Long id) {
        ApiResponse<AssetOtherRecordDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
