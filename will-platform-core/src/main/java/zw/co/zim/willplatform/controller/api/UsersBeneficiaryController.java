package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.UsersBeneficiaryDto;
import zw.co.zim.willplatform.processor.UsersBeneficiaryProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.UsersBeneficiaryRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/api/beneficiaries")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersBeneficiaryController {
    private final UsersBeneficiaryProcessor processor;

    public UsersBeneficiaryController(UsersBeneficiaryProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> getAllBeneficiaries(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<UsersBeneficiaryDto> beneficiaries = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(beneficiaries);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> createBeneficiary(@RequestBody @Valid UsersBeneficiaryRequest beneficiaryRequest) {
        ApiResponse<UsersBeneficiaryDto> recordDto = processor.save(beneficiaryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> updateBeneficiary(@PathVariable("id") Long id, @RequestBody UsersBeneficiaryDto beneficiaryDto) {
        ApiResponse<UsersBeneficiaryDto> recordDto = processor.update(id, beneficiaryDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<UsersBeneficiaryDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientIdAndNationalIdNumber/{clientId}/{nationalId}")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> findByNationalIdNumber(
        @PathVariable("clientId") Long clientId,
        @PathVariable("nationalId") String nationalId) {
        ApiResponse<UsersBeneficiaryDto> recordDto = processor.findBeneficiaryByClientIdAndIdNumber(clientId, nationalId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientIdAndEmail/{clientId}/{email}")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> findByEmail(
        @PathVariable("clientId") Long clientId,
        @PathVariable("email") String email) {
        ApiResponse<UsersBeneficiaryDto> recordDto = processor.findBeneficiaryByClientIdAndEmail(clientId, email);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> deleteBeneficiary(@PathVariable("id") Long id) {
        ApiResponse<UsersBeneficiaryDto> response = processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byClientId{clientId}")
    public ResponseEntity<ApiResponse<UsersBeneficiaryDto>> findByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<UsersBeneficiaryDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
