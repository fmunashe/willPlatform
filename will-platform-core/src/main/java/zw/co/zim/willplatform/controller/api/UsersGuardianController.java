package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.UsersGuardianDto;
import zw.co.zim.willplatform.processor.UsersGuardianProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.GuardianRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/guardians")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersGuardianController {

    private final UsersGuardianProcessor processor;

    public UsersGuardianController(UsersGuardianProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<UsersGuardianDto>> getAllGuardians(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<UsersGuardianDto> guardians = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(guardians);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UsersGuardianDto>> createGuardian(@RequestBody @Valid GuardianRequest guardianRequest) {
        ApiResponse<UsersGuardianDto> recordDto = processor.save(guardianRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{guardianId}")
    public ResponseEntity<ApiResponse<UsersGuardianDto>> updateGuardian(@RequestBody @Valid UsersGuardianDto guardianDto, @PathVariable("guardianId") Long guardianId) {
        ApiResponse<UsersGuardianDto> recordDto = processor.update(guardianId, guardianDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<UsersGuardianDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<UsersGuardianDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersGuardianDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<UsersGuardianDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{guardianId}")
    public ResponseEntity<ApiResponse<UsersGuardianDto>> deleteGuardianById(
        @PathVariable("guardianId") Long guardianId) {
        ApiResponse<UsersGuardianDto> recordDto = processor.deleteById(guardianId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
