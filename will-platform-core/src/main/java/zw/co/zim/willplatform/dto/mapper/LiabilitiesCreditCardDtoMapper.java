package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesCreditCardDto;
import zw.co.zim.willplatform.model.LiabilitiesCreditCard;

import java.util.function.Function;

@Service
public class LiabilitiesCreditCardDtoMapper implements Function<LiabilitiesCreditCard, LiabilitiesCreditCardDto> {
    @Override
    public LiabilitiesCreditCardDto apply(LiabilitiesCreditCard liabilitiesCreditCard) {
        return LiabilitiesCreditCardDto.builder()
            .id(liabilitiesCreditCard.getId())
            .userId(liabilitiesCreditCard.getUserId())
            .nameOfInstitution(liabilitiesCreditCard.getNameOfInstitution())
            .cardValue(liabilitiesCreditCard.getCardValue())
            .recordStatus(liabilitiesCreditCard.getRecordStatus())
            .createdAt(liabilitiesCreditCard.getCreatedAt())
            .updatedAt(liabilitiesCreditCard.getUpdatedAt())
            .build();
    }
}
