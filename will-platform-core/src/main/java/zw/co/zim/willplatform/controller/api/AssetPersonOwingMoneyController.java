package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.processor.AssetPersonOwingMoneyProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.AssetPersonOwingMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/assetPersonOwingMoney")
public class AssetPersonOwingMoneyController {
    private final AssetPersonOwingMoneyProcessor processor;

    public AssetPersonOwingMoneyController(AssetPersonOwingMoneyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetPersonOwingMoneyRecordDto>> getAllPersonOwingMoneyList(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<AssetPersonOwingMoneyRecordDto> personOwingMoneyRecordDtoList = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(personOwingMoneyRecordDtoList);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetPersonOwingMoneyRecordDto>> createPersonOwingMoney(@RequestBody @Valid AssetPersonOwingMoneyRequest personOwingMoneyRequest) {
        ApiResponse<AssetPersonOwingMoneyRecordDto> recordDto = processor.save(personOwingMoneyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetPersonOwingMoneyRecordDto>> updateOtherAsset(@PathVariable("id") Long id, @RequestBody AssetPersonOwingMoneyRecordDto personOwingMoneyRecordDto) {
        ApiResponse<AssetPersonOwingMoneyRecordDto> recordDto = processor.update(id, personOwingMoneyRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetPersonOwingMoneyRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetPersonOwingMoneyRecordDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId{clientId}")
    public ResponseEntity<ApiResponse<AssetPersonOwingMoneyRecordDto>> findByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<AssetPersonOwingMoneyRecordDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetPersonOwingMoneyRecordDto>> deleteOtherAsset(@PathVariable("id") Long id) {
        ApiResponse<AssetPersonOwingMoneyRecordDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
