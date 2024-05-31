package zw.co.zim.willplatform.utils.rules;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.UsersBeneficiary;

import java.util.List;
import java.util.function.BiPredicate;

@Service
public class UsersBeneficiaryChecker implements BiPredicate<List<UsersBeneficiary>, Double> {
    @Override
    public boolean test(List<UsersBeneficiary> usersBeneficiaries, Double currentPercentage) {
        if (usersBeneficiaries.isEmpty()) {
            return true;
        }

        Double currentTotal = usersBeneficiaries.stream().mapToDouble(UsersBeneficiary::getPercentage).sum();
        return currentTotal + currentPercentage <= 100;
    }
}
