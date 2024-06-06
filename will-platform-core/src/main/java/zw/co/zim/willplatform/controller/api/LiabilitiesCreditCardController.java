package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.LiabilitiesCreditCardDto;
import zw.co.zim.willplatform.processor.LiabilitiesCreditCardProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.CreditCardRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/liabilities/creditCard")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LiabilitiesCreditCardController {
    private final LiabilitiesCreditCardProcessor processor;

    public LiabilitiesCreditCardController(LiabilitiesCreditCardProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<LiabilitiesCreditCardDto>> getAllCreditCards(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<LiabilitiesCreditCardDto> cards = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<LiabilitiesCreditCardDto>> createCreditCard(@RequestBody @Valid CreditCardRequest creditCardRequest) {
        ApiResponse<LiabilitiesCreditCardDto> recordDto = processor.save(creditCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{cardId}")
    public ResponseEntity<ApiResponse<LiabilitiesCreditCardDto>> updateCreditCard(@RequestBody @Valid LiabilitiesCreditCardDto creditCardDto, @PathVariable("cardId") Long cardId) {
        ApiResponse<LiabilitiesCreditCardDto> recordDto = processor.update(cardId, creditCardDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LiabilitiesCreditCardDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<LiabilitiesCreditCardDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byClientId/{clientId}")
    public ResponseEntity<ApiResponse<LiabilitiesCreditCardDto>> findAllByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<LiabilitiesCreditCardDto> recordDto = processor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/byCreditCardId/{cardId}")
    public ResponseEntity<ApiResponse<LiabilitiesCreditCardDto>> deleteCreditCardById(
        @PathVariable("cardId") Long cardId) {
        ApiResponse<LiabilitiesCreditCardDto> recordDto = processor.deleteById(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
