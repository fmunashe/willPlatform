package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.UsersSpouseDto;
import zw.co.zim.willplatform.processor.UsersSpouseProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.SpouseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/spouse")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersSpouseController {
    private final UsersSpouseProcessor processor;

    public UsersSpouseController(UsersSpouseProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<UsersSpouseDto>> getAllSpouses(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<UsersSpouseDto> spouse = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(spouse);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UsersSpouseDto>> createSpouse(@RequestBody @Valid SpouseRequest spouseRequest) {
        ApiResponse<UsersSpouseDto> recordDto = processor.save(spouseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{spouseId}")
    public ResponseEntity<ApiResponse<UsersSpouseDto>> updateSpouse(@RequestBody @Valid UsersSpouseDto spouseDto, @PathVariable("spouseId") Long spouseId) {
        ApiResponse<UsersSpouseDto> recordDto = processor.update(spouseId, spouseDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<UsersSpouseDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<UsersSpouseDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersSpouseDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<UsersSpouseDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{spouseId}")
    public ResponseEntity<ApiResponse<UsersSpouseDto>> deleteSpouseById(
        @PathVariable("spouseId") Long spouseId) {
        ApiResponse<UsersSpouseDto> recordDto = processor.deleteById(spouseId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
