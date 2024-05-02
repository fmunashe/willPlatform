package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.UserRecordDto;
import zw.co.zim.willplatform.model.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<Client, UserRecordDto> {
    @Override
    public UserRecordDto apply(Client user) {
        return new UserRecordDto(user.getId(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getNationalIdNumber(), user.getPassportNumber(), user.getAddress().getStreet(), user.getAddress().getSuburb(), user.getAddress().getCity(), user.getAddress().getProvince(), user.getAddress().getCountry(),user.getRecordStatus());
    }
}
