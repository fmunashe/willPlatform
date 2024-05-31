package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillPasswordInstructionsDto;
import zw.co.zim.willplatform.model.WillPasswordsInstructions;

import java.util.function.Function;

@Service
public class WillPasswordInstructionsDtoMapper implements Function<WillPasswordsInstructions, WillPasswordInstructionsDto> {
    @Override
    public WillPasswordInstructionsDto apply(WillPasswordsInstructions willPasswordsInstructions) {
        return WillPasswordInstructionsDto.builder()
            .id(willPasswordsInstructions.getId())
            .userId(willPasswordsInstructions.getUserId())
            .instructions(willPasswordsInstructions.getInstructions())
            .recordStatus(willPasswordsInstructions.getRecordStatus())
            .createdAt(willPasswordsInstructions.getCreatedAt())
            .updatedAt(willPasswordsInstructions.getUpdatedAt())
            .build();
    }
}
