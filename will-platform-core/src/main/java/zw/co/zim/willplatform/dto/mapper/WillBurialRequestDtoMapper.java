package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillBurialRequestDto;
import zw.co.zim.willplatform.model.WillBurialRequest;

import java.util.function.Function;

@Service
public class WillBurialRequestDtoMapper implements Function<WillBurialRequest, WillBurialRequestDto> {
    @Override
    public WillBurialRequestDto apply(WillBurialRequest willBurialRequest) {
        return WillBurialRequestDto.builder()
            .id(willBurialRequest.getId())
            .userId(willBurialRequest.getUserId())
            .burialInformation(willBurialRequest.getBurialInformation())
            .livingWill(willBurialRequest.getLivingWill())
            .burialType(willBurialRequest.getBurialType())
            .recordStatus(willBurialRequest.getRecordStatus())
            .createdAt(willBurialRequest.getCreatedAt())
            .updatedAt(willBurialRequest.getUpdatedAt())
            .build();
    }
}
