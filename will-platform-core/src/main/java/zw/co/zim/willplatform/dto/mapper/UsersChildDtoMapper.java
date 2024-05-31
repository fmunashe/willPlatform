package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersChildDto;
import zw.co.zim.willplatform.model.UsersChild;

import java.util.function.Function;

@Service
public class UsersChildDtoMapper implements Function<UsersChild, UsersChildDto> {
    @Override
    public UsersChildDto apply(UsersChild usersChild) {
        return UsersChildDto.builder()
            .id(usersChild.getId())
            .userId(usersChild.getUserId())
            .name(usersChild.getName())
            .surname(usersChild.getSurname())
            .dob(usersChild.getDob())
            .trustAge(usersChild.getTrustAge())
            .recordStatus(usersChild.getRecordStatus())
            .createdAt(usersChild.getCreatedAt())
            .updatedAt(usersChild.getUpdatedAt())
            .build();
    }
}
