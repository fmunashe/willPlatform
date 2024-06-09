package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.ClientDto;
import zw.co.zim.willplatform.utils.messages.request.ChangePasswordRequest;
import zw.co.zim.willplatform.utils.messages.request.ClientRequest;
import zw.co.zim.willplatform.utils.messages.request.LoginRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface ClientServiceProcessor extends BaseProcessor<ClientDto, ClientRequest> {
    ApiResponse<ClientDto> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<ClientDto> findFirstByEmailOrNationalIdNumber(String email, String nationalId);

    ApiResponse<ClientDto> findFirstByNationalIdNumber(String nationalId);

    ApiResponse<ClientDto> findFirstByPassportNumber(String passportNumber);

    ApiResponse<ClientDto> changePassword(ChangePasswordRequest changePasswordRequest);

    ApiResponse<ClientDto> login(LoginRequest loginRequest);
}
