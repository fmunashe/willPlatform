package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.LiabilitiesUserOwesMoneyDto;
import zw.co.zim.willplatform.processor.LiabilitiesUserOwesMoneyProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.UserOwesMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/liabilities/userOwesMoney")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiabilitiesUserOwesMoneyController {

    private final LiabilitiesUserOwesMoneyProcessor processor;

    public LiabilitiesUserOwesMoneyController(LiabilitiesUserOwesMoneyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<LiabilitiesUserOwesMoneyDto>> getAllUsersWhoOweMoney(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<LiabilitiesUserOwesMoneyDto> dues = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(dues);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<LiabilitiesUserOwesMoneyDto>> createUserOweMoney(@RequestBody @Valid UserOwesMoneyRequest moneyRequest) {
        ApiResponse<LiabilitiesUserOwesMoneyDto> recordDto = processor.save(moneyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{moneyId}")
    public ResponseEntity<ApiResponse<LiabilitiesUserOwesMoneyDto>> updateUserOweMoney(@RequestBody @Valid LiabilitiesUserOwesMoneyDto userOwesMoneyDto, @PathVariable("moneyId") Long moneyId) {
        ApiResponse<LiabilitiesUserOwesMoneyDto> recordDto = processor.update(moneyId, userOwesMoneyDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<LiabilitiesUserOwesMoneyDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<LiabilitiesUserOwesMoneyDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LiabilitiesUserOwesMoneyDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<LiabilitiesUserOwesMoneyDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{moneyId}")
    public ResponseEntity<ApiResponse<LiabilitiesUserOwesMoneyDto>> deleteUserOweMoneyById(
        @PathVariable("moneyId") Long moneyId) {
        ApiResponse<LiabilitiesUserOwesMoneyDto> recordDto = processor.deleteById(moneyId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
