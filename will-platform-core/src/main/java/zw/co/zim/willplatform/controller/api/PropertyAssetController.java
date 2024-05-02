package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.processor.PropertyAssetProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/property")
public class PropertyAssetController {
    private final PropertyAssetProcessor propertyAssetProcessor;

    public PropertyAssetController(PropertyAssetProcessor propertyAssetProcessor) {
        this.propertyAssetProcessor = propertyAssetProcessor;
    }

    @GetMapping
    public ResponseEntity<List<PropertyAssetRecordDto>> getAllPropertyAssets() {
        List<PropertyAssetRecordDto> propertyAssets = propertyAssetProcessor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(propertyAssets);
    }

    @PostMapping("/save")
    public ResponseEntity<PropertyAssetRecordDto> createPropertyAsset(@RequestBody @Valid PropertyAssetRecordDto propertyAssetRecordDto) {
        PropertyAssetRecordDto recordDto = propertyAssetProcessor.save(propertyAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PropertyAssetRecordDto> updatePropertyAsset(@PathVariable("id") Long id, @RequestBody PropertyAssetRecordDto propertyAssetRecordDto) {
        PropertyAssetRecordDto recordDto = propertyAssetProcessor.update(id, propertyAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyAssetRecordDto> findById(@PathVariable("id") Long id) {
        Optional<PropertyAssetRecordDto> recordDto = propertyAssetProcessor.findById(id);
        return recordDto.map(bankAsset -> ResponseEntity.status(HttpStatus.OK).body(bankAsset)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyAsset(@PathVariable("id") Long id) {
        propertyAssetProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
