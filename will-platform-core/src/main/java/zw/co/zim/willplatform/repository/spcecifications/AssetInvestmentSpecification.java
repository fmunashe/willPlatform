package zw.co.zim.willplatform.repository.spcecifications;

import org.springframework.data.jpa.domain.Specification;
import zw.co.zim.willplatform.model.AssetInvestment;

public class AssetInvestmentSpecification {
    public static Specification<AssetInvestment> ofType(String investmentType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("investmentType"), investmentType);
    }
}
