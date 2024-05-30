package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAssetRequest {
    @NotNull(message = "Property name field is required")
    @NotBlank(message = "property name field cannot be blank")
    String propertyName;

    @NotNull(message = "property address street field is required")
    @NotBlank(message = "property address street field cannot be blank")
    String addressStreet;

    @NotNull(message = "property address suburb field is required")
    @NotBlank(message = "property address suburb field cannot be blank")
    String addressSuburb;

    @NotNull(message = "property address city field is required")
    @NotBlank(message = "property address city field cannot be blank")
    String addressCity;

    @NotNull(message = "property address province field is required")
    @NotBlank(message = "property address province field cannot be blank")
    String addressProvince;

    @NotNull(message = "property address country field is required")
    @NotBlank(message = "property address country field cannot be blank")
    String addressCountry;

    @NotNull(message = "property description field is required")
    @NotBlank(message = "property description field cannot be blank")
    String description;

    @NotNull(message = "property value field is required")
    Double propertyValue;

    Boolean haveABond;

    String bondIsWith;
    Boolean inYourName;
    Boolean isFarm;
    @NotNull(message = "person this property is under field is required")
    @NotBlank(message = "person this property is under field cannot be blank")
    String personPropertyIsUnder;
    Boolean youHoldDeed;
    String personWhoHoldsDeed;
    String additionalInformation;
    Long clientId;
}
