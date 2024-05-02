package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.processor.BankAssetProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/bank")
public class BankAssetController {
    private final BankAssetProcessor bankAssetProcessor;

    public BankAssetController(BankAssetProcessor bankAssetProcessor) {
        this.bankAssetProcessor = bankAssetProcessor;
    }

    @GetMapping
    public ResponseEntity<List<BankAssetRecordDto>> getAllBankAssets() {
        List<BankAssetRecordDto> bankAssets = bankAssetProcessor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bankAssets);
    }

    @PostMapping("/save")
    public ResponseEntity<BankAssetRecordDto> createBankAsset(@RequestBody @Valid BankAssetRecordDto bankAssetRecordDto) {
        BankAssetRecordDto recordDto = bankAssetProcessor.save(bankAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BankAssetRecordDto> updateBankAsset(@PathVariable("id") Long id, @RequestBody BankAssetRecordDto bankAssetRecordDto) {
        BankAssetRecordDto recordDto = bankAssetProcessor.update(id, bankAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAssetRecordDto> findById(@PathVariable("id") Long id) {
        Optional<BankAssetRecordDto> recordDto = bankAssetProcessor.findById(id);
        return recordDto.map(bankAsset -> ResponseEntity.status(HttpStatus.OK).body(bankAsset)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankAsset(@PathVariable("id") Long id) {
        bankAssetProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
