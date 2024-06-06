package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.LiabilitiesSubscribedWrittenPublicationDto;
import zw.co.zim.willplatform.processor.LiabilitiesSubscribedWrittenPublicationProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.WrittenPublicationRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/liabilities/writtenPublication")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiabilitiesSubscribedWrittenPublicationController {

    private final LiabilitiesSubscribedWrittenPublicationProcessor processor;

    public LiabilitiesSubscribedWrittenPublicationController(LiabilitiesSubscribedWrittenPublicationProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<LiabilitiesSubscribedWrittenPublicationDto>> getAllPublications(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> publications = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(publications);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<LiabilitiesSubscribedWrittenPublicationDto>> createWrittenPublication(@RequestBody @Valid WrittenPublicationRequest publicationRequest) {
        ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> recordDto = processor.save(publicationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{publicationId}")
    public ResponseEntity<ApiResponse<LiabilitiesSubscribedWrittenPublicationDto>> updateWrittenPublication(@RequestBody @Valid LiabilitiesSubscribedWrittenPublicationDto publicationDto, @PathVariable("publicationId") Long publicationId) {
        ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> recordDto = processor.update(publicationId, publicationDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<LiabilitiesSubscribedWrittenPublicationDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LiabilitiesSubscribedWrittenPublicationDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byPublicationId/{publicationId}")
    public ResponseEntity<ApiResponse<LiabilitiesSubscribedWrittenPublicationDto>> deleteWrittenPublicationById(
        @PathVariable("publicationId") Long publicationId) {
        ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> recordDto = processor.deleteById(publicationId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
