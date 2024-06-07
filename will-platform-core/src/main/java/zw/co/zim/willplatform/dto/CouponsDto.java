package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CouponsDto extends BaseDto {
    private String code;
    private Long productId;
    private LocalDate expiryDate;
    private Double discount;
    private boolean applied;
    private Long userId;
}
