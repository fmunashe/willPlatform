package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersSpouseDto;
import zw.co.zim.willplatform.model.UsersSpouse;

import java.util.function.Function;

@Service
public class UsersSpouseDtoMapper implements Function<UsersSpouse, UsersSpouseDto> {
    @Override
    public UsersSpouseDto apply(UsersSpouse usersSpouse) {
        return UsersSpouseDto.builder()
            .id(usersSpouse.getId())
            .userId(usersSpouse.getUserId())
            .name(usersSpouse.getName())
            .surname(usersSpouse.getSurname())
            .mobile(usersSpouse.getMobile())
            .email(usersSpouse.getEmail())
            .idNumber(usersSpouse.getIdNumber())
            .maritalStatus(usersSpouse.getMaritalStatus())
            .civillyMarriedStatus(usersSpouse.getCivillyMarriedStatus())
            .recordStatus(usersSpouse.getRecordStatus())
            .createdAt(usersSpouse.getCreatedAt())
            .updatedAt(usersSpouse.getUpdatedAt())
            .build();
    }
}
