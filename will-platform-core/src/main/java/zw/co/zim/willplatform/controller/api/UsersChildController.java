package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.UsersChildDto;
import zw.co.zim.willplatform.processor.UsersChildProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.UsersChildRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/children")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersChildController {
    private final UsersChildProcessor processor;

    public UsersChildController(UsersChildProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<UsersChildDto>> getAllChildren(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<UsersChildDto> children = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(children);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UsersChildDto>> createChild(@RequestBody @Valid UsersChildRequest childRequest) {
        ApiResponse<UsersChildDto> recordDto = processor.save(childRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{childId}")
    public ResponseEntity<ApiResponse<UsersChildDto>> updateChild(@RequestBody @Valid UsersChildDto childDto, @PathVariable("childId") Long childId) {
        ApiResponse<UsersChildDto> recordDto = processor.update(childId, childDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<UsersChildDto>> findFirstByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<UsersChildDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersChildDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<UsersChildDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<ApiResponse<UsersChildDto>> deleteChildById(
        @PathVariable("childId") Long childId) {
        ApiResponse<UsersChildDto> recordDto = processor.deleteById(childId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
