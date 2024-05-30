package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.model.PropertyAsset;

import java.util.function.Function;

@Service
public class PropertyAssetDtoMapper implements Function<PropertyAsset, PropertyAssetRecordDto> {
    @Override
    public PropertyAssetRecordDto apply(PropertyAsset propertyAsset) {
        return PropertyAssetRecordDto.builder()
            .id(propertyAsset.getId())
            .propertyName(propertyAsset.getPropertyName())
            .address(propertyAsset.getAddress())
            .description(propertyAsset.getDescription())
            .value(propertyAsset.getPropertyValue())
            .haveABond(propertyAsset.getHaveABond())
            .bondIsWith(propertyAsset.getBondIsWith())
            .inYourName(propertyAsset.getInYourName())
            .isFarm(propertyAsset.getIsFarm())
            .personPropertyIsUnder(propertyAsset.getPersonPropertyIsUnder())
            .youHoldDeed(propertyAsset.getYouHoldDeed())
            .personWhoHoldsDeed(propertyAsset.getPersonWhoHoldsDeed())
            .additionalInformation(propertyAsset.getAdditionalInformation())
            .user(propertyAsset.getUserId())
            .recordStatus(propertyAsset.getRecordStatus())
            .build();
    }
}
