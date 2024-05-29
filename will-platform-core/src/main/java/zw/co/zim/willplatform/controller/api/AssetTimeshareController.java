package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.processor.AssetTimeshareProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.TimeshareRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/timeshare")
public class AssetTimeshareController {
    private final AssetTimeshareProcessor processor;

    public AssetTimeshareController(AssetTimeshareProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AssetTimeShareDto>> getAllTimeshares(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<AssetTimeShareDto> timeshare = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(timeshare);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<AssetTimeShareDto>> createTimeshare(@RequestBody @Valid TimeshareRequest timeshareRequest) {
        ApiResponse<AssetTimeShareDto> recordDto = processor.save(timeshareRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AssetTimeShareDto>> updateTimeshare(@PathVariable("id") Long id, @RequestBody AssetTimeShareDto assetTimeShareDto) {
        ApiResponse<AssetTimeShareDto> recordDto = processor.update(id, assetTimeShareDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetTimeShareDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<AssetTimeShareDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId{id}")
    public ResponseEntity<ApiResponse<AssetTimeShareDto>> findByClientId(
        @PathVariable("id") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<AssetTimeShareDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetTimeShareDto>> deleteTimeshare(@PathVariable("id") Long id) {
        ApiResponse<AssetTimeShareDto>response =processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
