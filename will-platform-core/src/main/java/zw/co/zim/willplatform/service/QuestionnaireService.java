package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Questionnaire;

import java.util.Optional;

public interface QuestionnaireService extends AppService<Questionnaire> {
    Page<Questionnaire> findAll(Integer pageNo, Integer pageSize);

    Optional<Questionnaire> findFirstByUserId(Client userId);
}
