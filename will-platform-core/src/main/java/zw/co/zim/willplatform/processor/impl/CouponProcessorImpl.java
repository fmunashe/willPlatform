package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CouponsDto;
import zw.co.zim.willplatform.dto.mapper.CouponDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Coupons;
import zw.co.zim.willplatform.model.Products;
import zw.co.zim.willplatform.processor.CouponsProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.CouponService;
import zw.co.zim.willplatform.service.ProductsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.KeyGen;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.CouponRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class CouponProcessorImpl implements CouponsProcessor {
    private final ClientsService clientsService;
    private final CouponService couponService;
    private final ModelMapper modelMapper;

    private final CouponDtoMapper mapper;
    private final ProductsService productsService;

    public CouponProcessorImpl(ClientsService clientsService, CouponService couponService, ModelMapper modelMapper, CouponDtoMapper mapper, ProductsService productsService) {
        this.clientsService = clientsService;
        this.couponService = couponService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.productsService = productsService;
    }

    @Override
    public ApiResponse<CouponsDto> findAll(Integer pageNo, Integer pageSize) {
        Page<Coupons> coupons = couponService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(coupons, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<CouponsDto> findById(Long id) {
        Optional<Coupons> optional = couponService.findById(id);

        return optional.map(coupon -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(coupon)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find coupon record with Id of " + id));
    }

    @Override
    public ApiResponse<CouponsDto> save(CouponRequest couponRequest) {
        Optional<Products> product = productsService.findById(couponRequest.getProductId());
        if (product.isEmpty()) {
            throw new RecordNotFoundException("Failed to find product record with Id " + couponRequest.getProductId());
        }
        CouponsDto recordDto = CouponsDto.builder()
            .code(KeyGen.generateCouponCode())
            .applied(false)
            .discount(couponRequest.getDiscount())
            .expiryDate(couponRequest.getExpiryDate())
            .productId(product.get().getId())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        Coupons coupon = modelMapper.map(recordDto, Coupons.class);
        coupon = couponService.save(coupon);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(coupon));
    }

    @Override
    public ApiResponse<CouponsDto> update(Long id, CouponsDto couponsDto) {
        Optional<Coupons> coupon = couponService.findById(id);

        if (coupon.isEmpty() || !Objects.equals(coupon.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find coupon record with Id " + id);
        }
        Coupons updatedRecord = couponService.update(id, modelMapper.map(couponsDto, Coupons.class));
        CouponsDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<CouponsDto> deleteById(Long id) {
        Optional<Coupons> coupon = couponService.findById(id);

        if (coupon.isEmpty() || !coupon.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find coupon record with Id " + id);
        }
        couponService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<CouponsDto> findCouponByCode(String couponCode) {
        Optional<Coupons> optional = couponService.findCouponByCode(couponCode);

        return optional.map(coupon -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(coupon)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find coupon record with code " + couponCode));
    }

    @Override
    public ApiResponse<CouponsDto> findAllPagedAppliedCoupons(Integer pageNo, Integer pageSize, Boolean appliedYes) {
        Page<Coupons> coupons = couponService.findAllPagedAppliedCoupons(pageNo, pageSize, appliedYes);
        return HelperResponse.buildApiResponse(coupons, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CouponsDto> findAllExpiredAndNotAppliedCoupons(Integer pageNo, Integer pageSize, LocalDate expiryDate, Boolean appliedNo) {
        Page<Coupons> coupons = couponService.findAllPagedAppliedCoupons(pageNo, pageSize, appliedNo);
        return HelperResponse.buildApiResponse(coupons, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CouponsDto> applyCoupon(String couponCode, Long clientId) {
        Optional<Coupons> optional = couponService.findCouponByCode(couponCode);

        if (optional.isEmpty()) {
            throw new RecordNotFoundException("Failed to find coupon record with code " + couponCode);
        }

        Optional<Client> optionalClient = clientsService.findById(clientId);

        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client record with id " + clientId);
        }

        Coupons coupon = optional.get();
        coupon.setApplied(true);
        coupon.setUserId(optionalClient.get());
        coupon = couponService.applyCoupon(coupon);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(coupon));
    }
}
