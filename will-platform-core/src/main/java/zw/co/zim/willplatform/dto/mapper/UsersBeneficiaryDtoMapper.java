package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersBeneficiaryDto;
import zw.co.zim.willplatform.model.UsersBeneficiary;

import java.util.function.Function;

@Service
public class UsersBeneficiaryDtoMapper implements Function<UsersBeneficiary, UsersBeneficiaryDto> {
    @Override
    public UsersBeneficiaryDto apply(UsersBeneficiary usersBeneficiary) {
        return UsersBeneficiaryDto.builder()
            .id(usersBeneficiary.getId())
            .userId(usersBeneficiary.getUserId())
            .title(usersBeneficiary.getTitle())
            .name(usersBeneficiary.getName())
            .middleName(usersBeneficiary.getMiddleName())
            .lastName(usersBeneficiary.getLastName())
            .dob(usersBeneficiary.getDob())
            .idNumber(usersBeneficiary.getIdNumber())
            .passportNumber(usersBeneficiary.getPassportNumber())
            .contactNumber(usersBeneficiary.getContactNumber())
            .email(usersBeneficiary.getEmail())
            .trustName(usersBeneficiary.getTrustName())
            .trustNumber(usersBeneficiary.getTrustNumber())
            .gender(usersBeneficiary.getGender())
            .relationship(usersBeneficiary.getRelationship())
            .percentage(usersBeneficiary.getPercentage())
            .instructions(usersBeneficiary.getInstructions())
            .overEighteen(usersBeneficiary.getOverEighteen())
            .address(usersBeneficiary.getAddress())
            .recordStatus(usersBeneficiary.getRecordStatus())
            .createdAt(usersBeneficiary.getCreatedAt())
            .updatedAt(usersBeneficiary.getUpdatedAt())
            .build();
    }
}
