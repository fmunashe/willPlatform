package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersGuardianDto;
import zw.co.zim.willplatform.model.UsersGuardian;

import java.util.function.Function;

@Service
public class UsersGuardianDtoMapper implements Function<UsersGuardian, UsersGuardianDto> {
    @Override
    public UsersGuardianDto apply(UsersGuardian usersGuardian) {
        return UsersGuardianDto.builder()
            .id(usersGuardian.getId())
            .title(usersGuardian.getTitle())
            .name(usersGuardian.getName())
            .middleName(usersGuardian.getMiddleName())
            .surname(usersGuardian.getSurname())
            .email(usersGuardian.getEmail())
            .contactNumber(usersGuardian.getContactNumber())
            .IdNumber(usersGuardian.getIdNumber())
            .passportNumber(usersGuardian.getPassportNumber())
            .gender(usersGuardian.getGender())
            .relationship(usersGuardian.getRelationship())
            .userId(usersGuardian.getUserId())
            .dob(usersGuardian.getDob())
            .address(usersGuardian.getAddress())
            .recordStatus(usersGuardian.getRecordStatus())
            .createdAt(usersGuardian.getCreatedAt())
            .updatedAt(usersGuardian.getUpdatedAt())
            .build();
    }
}
