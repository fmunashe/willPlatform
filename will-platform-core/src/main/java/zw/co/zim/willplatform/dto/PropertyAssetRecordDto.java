package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDateTime;

@Builder
public record PropertyAssetRecordDto(
    Long id,
    @NotNull(message = "Property name field is required")
    @NotBlank(message = "property name field cannot be blank")
    String propertyName,
    @NotNull(message = "property address field is required")
    @NotBlank(message = "property address field cannot be blank")
    Address address,

    @NotNull(message = "property description field is required")
    @NotBlank(message = "property description field cannot be blank")
    String description,

    @NotNull(message = "property value field is required")
    Double value,
    Boolean haveABond,

    String bondIsWith,
    Boolean inYourName,
    Boolean isFarm,
    @NotNull(message = "person this property is under field is required")
    @NotBlank(message = "person this property is under field cannot be blank")
    String personPropertyIsUnder,
    Boolean youHoldDeed,
    String personWhoHoldsDeed,
    String additionalInformation,
    Client user,
    RecordStatus recordStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
