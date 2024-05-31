package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;

import java.util.Optional;

public interface SignupProgressJourneyService extends BaseService<SignupProgressJourney> {
    Page<SignupProgressJourney> findAll(Integer pageNo, Integer pageSize);
    Optional<SignupProgressJourney> findFirstByUserId(Client userId);
}
