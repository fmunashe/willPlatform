package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.processor.AssetOtherProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/assetOther")
public class AssetOtherController {
    private final AssetOtherProcessor processor;

    public AssetOtherController(AssetOtherProcessor processor) {
        this.processor = processor;
    }


    @GetMapping
    public ResponseEntity<List<AssetOtherRecordDto>> getAllOtherAssets() {
        List<AssetOtherRecordDto> assetOther = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(assetOther);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetOtherRecordDto> createOtherAsset(@RequestBody @Valid AssetOtherRecordDto assetOtherRecordDto) {
        AssetOtherRecordDto recordDto = processor.save(assetOtherRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetOtherRecordDto> updateOtherAsset(@PathVariable("id") Long id, @RequestBody AssetOtherRecordDto assetOtherRecordDto) {
        AssetOtherRecordDto recordDto = processor.update(id, assetOtherRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetOtherRecordDto> findById(@PathVariable("id") Long id) {
        Optional<AssetOtherRecordDto> recordDto = processor.findById(id);
        return recordDto.map(assetOtherRecordDto -> ResponseEntity.status(HttpStatus.OK).body(assetOtherRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOtherAsset(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
