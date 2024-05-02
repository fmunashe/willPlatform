package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.processor.AssetTrustProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/assetTrust")
public class AssetTrustController {
    private final AssetTrustProcessor processor;

    public AssetTrustController(AssetTrustProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<List<AssetTrustDto>> getAllTrustAssets() {
        List<AssetTrustDto> assetTrusts = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(assetTrusts);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetTrustDto> createTrustAsset(@RequestBody @Valid AssetTrustDto assetTrustDto) {
        AssetTrustDto recordDto = processor.save(assetTrustDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetTrustDto> updateTrustAsset(@PathVariable("id") Long id, @RequestBody AssetTrustDto assetTrustDto) {
        AssetTrustDto recordDto = processor.update(id, assetTrustDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetTrustDto> findById(@PathVariable("id") Long id) {
        Optional<AssetTrustDto> recordDto = processor.findById(id);
        return recordDto.map(assetTrustDto -> ResponseEntity.status(HttpStatus.OK).body(assetTrustDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrustAsset(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
