package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.processor.VehicleAssetProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.VehicleAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/vehicles")
public class VehicleAssetController {
    private final VehicleAssetProcessor vehicleAssetProcessor;

    public VehicleAssetController(VehicleAssetProcessor vehicleAssetProcessor) {
        this.vehicleAssetProcessor = vehicleAssetProcessor;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> getAllVehicles(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        ApiResponse<VehicleAssetRecordDto> vehicles = vehicleAssetProcessor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> createVehicle(@RequestBody @Valid VehicleAssetRequest vehicleAssetRequest) {
        ApiResponse<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.save(vehicleAssetRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> updateVehicle(@PathVariable("id") Long id, @RequestBody VehicleAssetRecordDto vehicleAssetRecordDto) {
        ApiResponse<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.update(id, vehicleAssetRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/ByRegNumber/{regNumber}")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> findByRegistrationNumber(@PathVariable("regNumber") String regNumber) {
        ApiResponse<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.findVehicleByRegNumber(regNumber);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/ByEngineNumber/{engineNumber}")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> findByEngineNumber(@PathVariable("engineNumber") String engineNumber) {
        ApiResponse<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.findVehicleByRegNumber(engineNumber);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> deleteVehicleAsset(@PathVariable("id") Long id) {
        ApiResponse<VehicleAssetRecordDto> response = vehicleAssetProcessor.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/byClientId{clientId}")
    public ResponseEntity<ApiResponse<VehicleAssetRecordDto>> findByClientId(
        @PathVariable("clientId") Long clientId,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        ApiResponse<VehicleAssetRecordDto> recordDto = vehicleAssetProcessor.findAllByUserId(clientId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
