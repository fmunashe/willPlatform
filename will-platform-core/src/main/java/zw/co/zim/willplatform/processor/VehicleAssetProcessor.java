package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.utils.messages.request.VehicleAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface VehicleAssetProcessor extends ProcessorService<VehicleAssetRecordDto, VehicleAssetRequest> {
    ApiResponse<VehicleAssetRecordDto> findVehicleByRegNumber(String regNumber);

    ApiResponse<VehicleAssetRecordDto> findVehicleByEngineNumber(String engineNumber);
    ApiResponse<VehicleAssetRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
