package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.CurrencyDto;
import zw.co.zim.willplatform.processor.CurrencyProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.CurrencyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/currencies")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CurrencyController {
    private final CurrencyProcessor processor;

    public CurrencyController(CurrencyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<CurrencyDto>> getAllCurrencies(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CurrencyDto> currencies = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(currencies);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CurrencyDto>> createCurrency(@RequestBody @Valid CurrencyRequest currencyRequest) {
        ApiResponse<CurrencyDto> recordDto = processor.save(currencyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{currencyId}")
    public ResponseEntity<ApiResponse<CurrencyDto>> updateCurrency(@RequestBody @Valid CurrencyDto currencyDto, @PathVariable("currencyId") Long currencyId) {
        ApiResponse<CurrencyDto> recordDto = processor.update(currencyId, currencyDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/byName/{currencyName}")
    public ResponseEntity<ApiResponse<CurrencyDto>> findCurrencyByName(@PathVariable("currencyName") String currencyName) {
        ApiResponse<CurrencyDto> recordDto = processor.findCurrencyByName(currencyName);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CurrencyDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<CurrencyDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{currencyId}")
    public ResponseEntity<ApiResponse<CurrencyDto>> deleteCurrencyById(
        @PathVariable("currencyId") Long currencyId) {
        ApiResponse<CurrencyDto> recordDto = processor.deleteById(currencyId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
