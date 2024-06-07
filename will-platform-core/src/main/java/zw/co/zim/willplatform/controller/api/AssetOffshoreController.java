package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.processor.AssetOffshoreProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AssetOffshoreRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/assetOffShores")
public class AssetOffshoreController {
    private final AssetOffshoreProcessor processor;

    public AssetOffshoreController(AssetOffshoreProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetOffshoreRecordDto>> getAllOffshoreAssets(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetOffshoreRecordDto> assetOffshoreRecordDtos = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(assetOffshoreRecordDtos);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetOffshoreRecordDto>> createOffshoreAsset(@RequestBody @Valid AssetOffshoreRequest offshoreRequest) {
        ApiResponse<AssetOffshoreRecordDto> recordDto = processor.save(offshoreRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetOffshoreRecordDto>> updateOffshoreAsset(@PathVariable("id") Long id, @RequestBody AssetOffshoreRecordDto assetOffshoreRecordDto) {
        ApiResponse<AssetOffshoreRecordDto> recordDto = processor.update(id, assetOffshoreRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetOffshoreRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetOffshoreRecordDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId{id}")
    public ResponseEntity<ApiResponse<AssetOffshoreRecordDto>> findByClientId(
        @PathVariable("id") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetOffshoreRecordDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOtherAsset(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
