package zw.co.zim.willplatform.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.ProductsDto;
import zw.co.zim.willplatform.processor.ProductsProcessor;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.ProductNames;
import zw.co.zim.willplatform.utils.messages.request.ProductRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductsController {
    private final ProductsProcessor processor;

    public ProductsController(ProductsProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<ProductsDto>> getAllProducts(
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<ProductsDto> products = processor.findAll(pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/findAllProductsByCurrencyName/{currencyName}")
    public ResponseEntity<ApiResponse<ProductsDto>> getAllProducts(
        @PathVariable("currencyName") String name,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
        ApiResponse<ProductsDto> products = processor.findAllByCurrency(name, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ProductsDto>> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        ApiResponse<ProductsDto> recordDto = processor.save(productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<ProductsDto>> updateProduct(@RequestBody @Valid ProductsDto productsDto, @PathVariable("productId") Long productId) {
        ApiResponse<ProductsDto> recordDto = processor.update(productId, productsDto);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductsDto>> findProductById(@PathVariable("productId") Long productId) {
        ApiResponse<ProductsDto> recordDto = processor.findById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @GetMapping("/byName/{productName}")
    public ResponseEntity<ApiResponse<ProductsDto>> findProductByName(@PathVariable("productName") ProductNames productName) {
        ApiResponse<ProductsDto> recordDto = processor.findProductByName(productName);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductsDto>> deleteProductById(
        @PathVariable("productId") Long productId) {
        ApiResponse<ProductsDto> recordDto = processor.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(recordDto);
    }
}
