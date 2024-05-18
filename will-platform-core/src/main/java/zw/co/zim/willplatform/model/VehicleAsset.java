package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.enums.RecordStatus;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleAsset extends BaseEntity {
    private String make;
    private String model;
    private String color;
    private String registrationNumber;
    private String engineNumber;
    private LocalDate manufactureYear;
    private Double vehicleValue;
    private Boolean fullyPaid;
    private String registrationPaperWith;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public VehicleAsset(VehicleAssetRecordDto vehicleAssetRecordDto) {
        this.make = vehicleAssetRecordDto.make();
        this.model = vehicleAssetRecordDto.model();
        this.color = vehicleAssetRecordDto.color();
        this.registrationNumber = vehicleAssetRecordDto.registrationNumber();
        this.engineNumber = vehicleAssetRecordDto.engineNumber();
        this.manufactureYear = vehicleAssetRecordDto.manufactureYear();
        this.vehicleValue = vehicleAssetRecordDto.value();
        this.fullyPaid = vehicleAssetRecordDto.fullyPaid();
        this.registrationPaperWith = vehicleAssetRecordDto.registrationPaperWith();
        this.userId = vehicleAssetRecordDto.user();
        this.recordStatus = vehicleAssetRecordDto.recordStatus();
    }
}
