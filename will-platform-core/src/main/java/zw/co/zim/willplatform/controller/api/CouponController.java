package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.CouponsDto;
import zw.co.zim.willplatform.processor.CouponsProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.messages.request.CouponRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

import java.time.LocalDate;

@RestController
@RequestMapping("/coupons")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CouponController {
    private final CouponsProcessor processor;

    public CouponController(CouponsProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<CouponsDto>> getAllCoupons(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CouponsDto> coupons = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(coupons);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CouponsDto>> createCoupon(@RequestBody @Valid CouponRequest couponRequest) {
        ApiResponse<CouponsDto> recordDto = processor.save(couponRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{couponId}")
    public ResponseEntity<ApiResponse<CouponsDto>> updateCoupon(@RequestBody @Valid CouponsDto couponsDto, @PathVariable("couponId") Long couponId) {
        ApiResponse<CouponsDto> recordDto = processor.update(couponId, couponsDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/expiredNotAppliedCoupons")
    public ResponseEntity<ApiResponse<CouponsDto>> getAllExpiredAndNotAppliedCoupons(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CouponsDto> recordDto = processor.findAllExpiredAndNotAppliedCoupons(pageNo, pageSize, LocalDate.now(), false);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/appliedCoupons")
    public ResponseEntity<ApiResponse<CouponsDto>> getAllAppliedCoupons(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<CouponsDto> recordDto = processor.findAllPagedAppliedCoupons(pageNo, pageSize, true);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CouponsDto>> findById(@PathVariable("id") Long id) {
        ApiResponse<CouponsDto> recordDto = processor.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byCode{couponCode}")
    public ResponseEntity<ApiResponse<CouponsDto>> findById(@PathVariable("couponCode") String couponCode) {
        ApiResponse<CouponsDto> recordDto = processor.findCouponByCode(couponCode);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PostMapping("/applyCoupon/{clientId}/{couponCode}")
    public ResponseEntity<ApiResponse<CouponsDto>> applyCoupon(
        @PathVariable("couponCode") String couponCode,
        @PathVariable("clientId") Long clientId) {
        ApiResponse<CouponsDto> recordDto = processor.applyCoupon(couponCode, clientId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<ApiResponse<CouponsDto>> deleteCouponById(
        @PathVariable("couponId") Long couponId) {
        ApiResponse<CouponsDto> recordDto = processor.deleteById(couponId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

}
