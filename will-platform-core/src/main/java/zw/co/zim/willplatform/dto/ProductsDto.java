package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.utils.enums.ProductNames;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductsDto extends BaseDto {
    private ProductNames name;
    private String description;
    private Double price;
    private Currency currency;
}
