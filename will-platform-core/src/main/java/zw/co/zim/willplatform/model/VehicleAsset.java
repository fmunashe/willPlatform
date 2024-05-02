package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

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
    }
}
