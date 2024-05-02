package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.processor.AssetInvestmentProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/assetInvestment")
public class AssetInvestmentController {

    private final AssetInvestmentProcessor processor;

    public AssetInvestmentController(AssetInvestmentProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<List<AssetInvestmentRecordDto>> getAllInvestmentAssets() {
        List<AssetInvestmentRecordDto> investmentRecordDtos = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(investmentRecordDtos);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetInvestmentRecordDto> createInvestmentAsset(@RequestBody @Valid AssetInvestmentRecordDto assetInvestmentRecordDto) {
        AssetInvestmentRecordDto recordDto = processor.save(assetInvestmentRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetInvestmentRecordDto> updateInvestmentAsset(@PathVariable("id") Long id, @RequestBody AssetInvestmentRecordDto assetInvestmentRecordDto) {
        AssetInvestmentRecordDto recordDto = processor.update(id, assetInvestmentRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetInvestmentRecordDto> findById(@PathVariable("id") Long id) {
        Optional<AssetInvestmentRecordDto> recordDto = processor.findById(id);
        return recordDto.map(investmentRecordDto -> ResponseEntity.status(HttpStatus.OK).body(investmentRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestmentAsset(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
