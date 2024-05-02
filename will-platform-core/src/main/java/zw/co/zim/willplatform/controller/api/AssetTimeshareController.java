package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.processor.AssetTimeshareProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/timeshare")
public class AssetTimeshareController {
    private final AssetTimeshareProcessor processor;

    public AssetTimeshareController(AssetTimeshareProcessor processor) {
        this.processor = processor;
    }
    @GetMapping
    public ResponseEntity<List<AssetTimeShareDto>> getAllTimeshares() {
        List<AssetTimeShareDto> timeshare = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(timeshare);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetTimeShareDto> createTimeshare(@RequestBody @Valid AssetTimeShareDto assetTimeShareDto) {
        AssetTimeShareDto recordDto = processor.save(assetTimeShareDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetTimeShareDto> updateTimeshare(@PathVariable("id") Long id, @RequestBody AssetTimeShareDto assetTimeShareDto) {
        AssetTimeShareDto recordDto = processor.update(id, assetTimeShareDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetTimeShareDto> findById(@PathVariable("id") Long id) {
        Optional<AssetTimeShareDto> recordDto = processor.findById(id);
        return recordDto.map(timeShareDto -> ResponseEntity.status(HttpStatus.OK).body(timeShareDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTimeshare(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
