package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.model.PropertyAsset;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PropertyAssetDtoMapper implements Function<PropertyAsset, PropertyAssetRecordDto> {
    @Override
    public PropertyAssetRecordDto apply(PropertyAsset propertyAsset) {
        return new PropertyAssetRecordDto(propertyAsset.getPropertyName(),
            propertyAsset.getAddress(),
            propertyAsset.getDescription(),
            propertyAsset.getPropertyValue(),
            propertyAsset.getHaveABond(),
            propertyAsset.getBondIsWith(),
            propertyAsset.getInYourName(),
            propertyAsset.getIsFarm(),
            propertyAsset.getPersonPropertyIsUnder(),
            propertyAsset.getYouHoldDeed(),
            propertyAsset.getPersonWhoHoldsDeed(),
            propertyAsset.getAdditionalInformation(),
            propertyAsset.getUserId(),
            propertyAsset.getRecordStatus());
    }
}
