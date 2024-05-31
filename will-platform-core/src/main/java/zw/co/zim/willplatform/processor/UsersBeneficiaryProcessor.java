package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.UsersBeneficiaryDto;
import zw.co.zim.willplatform.utils.messages.request.UsersBeneficiaryRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface UsersBeneficiaryProcessor extends BaseProcessor<UsersBeneficiaryDto, UsersBeneficiaryRequest> {
    ApiResponse<UsersBeneficiaryDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);

    ApiResponse<UsersBeneficiaryDto> findBeneficiaryByClientIdAndEmail(Long clientId, String email);

    ApiResponse<UsersBeneficiaryDto> findBeneficiaryByClientIdAndIdNumber(Long clientId, String nationalId);
}
