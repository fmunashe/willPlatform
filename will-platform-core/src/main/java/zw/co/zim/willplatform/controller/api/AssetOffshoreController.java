package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.processor.AssetOffshoreProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/offshore")
public class AssetOffshoreController {
    private final AssetOffshoreProcessor processor;

    public AssetOffshoreController(AssetOffshoreProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<List<AssetOffshoreRecordDto>> getAllOffshoreAssets() {
        List<AssetOffshoreRecordDto> assetOffshoreRecordDtos = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(assetOffshoreRecordDtos);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetOffshoreRecordDto> createOffshoreAsset(@RequestBody @Valid AssetOffshoreRecordDto assetOffshoreRecordDto) {
        AssetOffshoreRecordDto recordDto = processor.save(assetOffshoreRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetOffshoreRecordDto> updateOffshoreAsset(@PathVariable("id") Long id, @RequestBody AssetOffshoreRecordDto assetOffshoreRecordDto) {
        AssetOffshoreRecordDto recordDto = processor.update(id, assetOffshoreRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetOffshoreRecordDto> findById(@PathVariable("id") Long id) {
        Optional<AssetOffshoreRecordDto> recordDto = processor.findById(id);
        return recordDto.map(assetOffshoreRecordDto -> ResponseEntity.status(HttpStatus.OK).body(assetOffshoreRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOtherAsset(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
