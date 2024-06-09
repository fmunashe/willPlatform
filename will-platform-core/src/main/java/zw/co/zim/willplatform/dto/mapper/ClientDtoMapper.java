package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.ClientDto;
import zw.co.zim.willplatform.model.Client;

import java.util.function.Function;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {
    @Override
    public ClientDto apply(Client client) {
        return ClientDto.builder()
            .id(client.getId())
            .firstName(client.getFirstName())
            .middleName(client.getMiddleName())
            .lastName(client.getLastName())
            .firstLanguage(client.getFirstLanguage())
            .knownAs(client.getKnownAs())
            .dateOfBirth(client.getDateOfBirth())
            .email(client.getEmail())
            .mobileNumber(client.getMobileNumber())
            .nationalIdNumber(client.getNationalIdNumber())
            .passportNumber(client.getPassportNumber())
            .sendOtpVia(client.getSendOtpVia())
            .OTP(client.getOTP())
            .acceptedTermsAndConditions(client.getAcceptedTermsAndConditions())
            .physicalAddress(client.getPhysicalAddress())
            .createdAt(client.getCreatedAt())
            .updatedAt(client.getUpdatedAt())
            .recordStatus(client.getRecordStatus())
            .build();
    }
}
