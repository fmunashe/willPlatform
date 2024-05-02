package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.processor.AssetPersonOwingMoneyProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/personOwingMoney")
public class AssetPersonOwingMoneyController {
    private final AssetPersonOwingMoneyProcessor processor;

    public AssetPersonOwingMoneyController(AssetPersonOwingMoneyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<List<AssetPersonOwingMoneyRecordDto>> getAllPersonOwingMoneyList() {
        List<AssetPersonOwingMoneyRecordDto> personOwingMoneyRecordDtoList = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(personOwingMoneyRecordDtoList);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetPersonOwingMoneyRecordDto> createPersonOwingMoney(@RequestBody @Valid AssetPersonOwingMoneyRecordDto personOwingMoneyRecordDto) {
        AssetPersonOwingMoneyRecordDto recordDto = processor.save(personOwingMoneyRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetPersonOwingMoneyRecordDto> updateOtherAsset(@PathVariable("id") Long id, @RequestBody AssetPersonOwingMoneyRecordDto personOwingMoneyRecordDto) {
        AssetPersonOwingMoneyRecordDto recordDto = processor.update(id, personOwingMoneyRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetPersonOwingMoneyRecordDto> findById(@PathVariable("id") Long id) {
        Optional<AssetPersonOwingMoneyRecordDto> recordDto = processor.findById(id);
        return recordDto.map(assetPersonOwingMoneyRecordDto -> ResponseEntity.status(HttpStatus.OK).body(assetPersonOwingMoneyRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOtherAsset(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
