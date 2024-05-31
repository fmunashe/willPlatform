package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillAlternativeExecutorDto;
import zw.co.zim.willplatform.model.WillAlternativeExecutor;

import java.util.function.Function;

@Service
public class WillAlternativeExecutorDtoMapper implements Function<WillAlternativeExecutor, WillAlternativeExecutorDto> {
    @Override
    public WillAlternativeExecutorDto apply(WillAlternativeExecutor willAlternativeExecutor) {
        return WillAlternativeExecutorDto.builder()
            .id(willAlternativeExecutor.getId())
            .userId(willAlternativeExecutor.getUserId())
            .email(willAlternativeExecutor.getEmail())
            .executorDetails(willAlternativeExecutor.getExecutorDetails())
            .contactNumber(willAlternativeExecutor.getContactNumber())
            .address(willAlternativeExecutor.getAddress())
            .recordStatus(willAlternativeExecutor.getRecordStatus())
            .createdAt(willAlternativeExecutor.getCreatedAt())
            .updatedAt(willAlternativeExecutor.getUpdatedAt())
            .build();
    }
}
