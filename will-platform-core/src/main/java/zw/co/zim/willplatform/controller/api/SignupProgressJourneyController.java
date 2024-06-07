package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.SignupProgressJourneyDto;
import zw.co.zim.willplatform.processor.SignupProgressJourneyProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

import java.util.Map;

@RestController
@RequestMapping("/signupProgress")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SignupProgressJourneyController {
    private final SignupProgressJourneyProcessor processor;

    public SignupProgressJourneyController(SignupProgressJourneyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<SignupProgressJourneyDto>> getAllSignUps(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<SignupProgressJourneyDto> signups = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(signups);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SignupProgressJourneyDto>> createSignupProgressJourney(@RequestBody @Valid SignupProgressJourneyDto signupProgressJourneyDto) {
        ApiResponse<SignupProgressJourneyDto> recordDto = processor.save(signupProgressJourneyDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{signupProgressId}")
    public ResponseEntity<ApiResponse<SignupProgressJourneyDto>> updateSignupProgressJourney(@RequestBody @Valid SignupProgressJourneyDto signupProgressJourneyDto, @PathVariable("signupProgressId") Long signupProgressId) {
        ApiResponse<SignupProgressJourneyDto> recordDto = processor.update(signupProgressId, signupProgressJourneyDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<SignupProgressJourneyDto>> findFirstByUserId(@PathVariable("clientId") Long clientId) {
        ApiResponse<SignupProgressJourneyDto> recordDto = processor.findFirstByClientId(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SignupProgressJourneyDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<SignupProgressJourneyDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/signupProgressStatistics")
    public ResponseEntity<ApiResponse<Map<String, Long>>> signupProgressStatistics() {
        ApiResponse<Map<String, Long>> recordDto = processor.signupProgressStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{signupProgressId}")
    public ResponseEntity<ApiResponse<SignupProgressJourneyDto>> deleteBySignupProgressId(
        @PathVariable("signupProgressId") Long signupProgressId) {
        ApiResponse<SignupProgressJourneyDto> recordDto = processor.deleteById(signupProgressId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
