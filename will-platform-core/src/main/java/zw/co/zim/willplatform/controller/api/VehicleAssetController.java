package zw.co.zim.willplatform.controller.api;

import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.processor.VehicleAssetProcessor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleAssetController {
    private final VehicleAssetProcessor vehicleAssetProcessor;

    public VehicleAssetController(VehicleAssetProcessor vehicleAssetProcessor) {
        this.vehicleAssetProcessor = vehicleAssetProcessor;
    }

    @GetMapping
    public ResponseEntity<List<VehicleAssetRecordDto>> getAllVehicles() {
        List<VehicleAssetRecordDto> vehicles = vehicleAssetProcessor.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }


    @PostMapping("/save")
    public ResponseEntity<VehicleAssetRecordDto> createVehicle(@RequestBody @Valid VehicleAssetRecordDto vehicleAssetRecordDto) {
        VehicleAssetRecordDto recordDto = vehicleAssetProcessor.save(vehicleAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleAssetRecordDto> updateVehicle(@PathVariable("id") Long id, @RequestBody VehicleAssetRecordDto vehicleAssetRecordDto) {
        VehicleAssetRecordDto recordDto = vehicleAssetProcessor.update(id, vehicleAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleAssetRecordDto> findById(@PathVariable("id") Long id) {
        Optional<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.findById(id);
        return recordDto.map(vehicleRecordDto -> ResponseEntity.status(HttpStatus.OK).body(vehicleRecordDto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long id) {
        vehicleAssetProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Record with id of " + id + " successfully deleted");
    }
}
