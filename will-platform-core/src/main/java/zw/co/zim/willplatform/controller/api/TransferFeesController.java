package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.TransferFeesDto;
import zw.co.zim.willplatform.processor.TransferFeesProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.TransferFeeRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/transferFees")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransferFeesController {

    private final TransferFeesProcessor processor;

    public TransferFeesController(TransferFeesProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<TransferFeesDto>> getAllTransferFees(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<TransferFeesDto> fees = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(fees);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<TransferFeesDto>> createTransferFee(@RequestBody @Valid TransferFeeRequest transferFeeRequest) {
        ApiResponse<TransferFeesDto> recordDto = processor.save(transferFeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{transferFeeId}")
    public ResponseEntity<ApiResponse<TransferFeesDto>> updateTransferFee(@RequestBody @Valid TransferFeesDto transferFeesDto, @PathVariable("transferFeeId") Long transferFeeId) {
        ApiResponse<TransferFeesDto> recordDto = processor.update(transferFeeId, transferFeesDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransferFeesDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<TransferFeesDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{transferFeeId}")
    public ResponseEntity<ApiResponse<TransferFeesDto>> deleteTransferFeeById(
        @PathVariable("transferFeeId") Long transferFeeId) {
        ApiResponse<TransferFeesDto> recordDto = processor.deleteById(transferFeeId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
