package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.processor.AssetPolicyProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/policy")
public class AssetPolicyController {

    private final AssetPolicyProcessor processor;

    public AssetPolicyController(AssetPolicyProcessor processor) {
        this.processor = processor;
    }

    @GetMapping
    public ResponseEntity<List<AssetPolicyRecordDto>> getAllPolicies() {
        List<AssetPolicyRecordDto> policies = processor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(policies);
    }


    @PostMapping("/save")
    public ResponseEntity<AssetPolicyRecordDto> createAssetPolicy(@RequestBody @Valid AssetPolicyRecordDto policyRecordDto) {
        AssetPolicyRecordDto recordDto = processor.save(policyRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetPolicyRecordDto> updateAssetPolicy(@PathVariable("id") Long id, @RequestBody AssetPolicyRecordDto policyRecordDto) {
        AssetPolicyRecordDto recordDto = processor.update(id, policyRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetPolicyRecordDto> findById(@PathVariable("id") Long id) {
        Optional<AssetPolicyRecordDto> recordDto = processor.findById(id);
        return recordDto.map(policyRecordDto -> ResponseEntity.status(HttpStatus.OK).body(policyRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{policyNumber}")
    public ResponseEntity<AssetPolicyRecordDto> findById(@PathVariable("policyNumber") String policyNumber) {
        Optional<AssetPolicyRecordDto> recordDto = processor.findFirstByPolicyNumber(policyNumber);
        return recordDto.map(policyRecordDto -> ResponseEntity.status(HttpStatus.OK).body(policyRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssetPolicy(@PathVariable("id") Long id) {
        processor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }


}
