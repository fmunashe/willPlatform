package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.TotalDemiseDto;
import zw.co.zim.willplatform.processor.TotalDemiseProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.TotalDemiseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/totalDemise")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TotalDemiseController {
    private final TotalDemiseProcessor processor;

    public TotalDemiseController(TotalDemiseProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<TotalDemiseDto>> getAllTotalDemise(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<TotalDemiseDto> demise = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(demise);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<TotalDemiseDto>> createTotalDemise(@RequestBody @Valid TotalDemiseRequest demiseRequest) {
        ApiResponse<TotalDemiseDto> recordDto = processor.save(demiseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{demiseId}")
    public ResponseEntity<ApiResponse<TotalDemiseDto>> updateTotalDemise(@RequestBody @Valid TotalDemiseDto totalDemiseDto, @PathVariable("demiseId") Long demiseId) {
        ApiResponse<TotalDemiseDto> recordDto = processor.update(demiseId, totalDemiseDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<TotalDemiseDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<TotalDemiseDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TotalDemiseDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<TotalDemiseDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{demiseId}")
    public ResponseEntity<ApiResponse<TotalDemiseDto>> deleteTotalDemiseById(
        @PathVariable("demiseId") Long demiseId) {
        ApiResponse<TotalDemiseDto> recordDto = processor.deleteById(demiseId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
