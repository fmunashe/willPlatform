package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Questionnaire;
import zw.co.zim.willplatform.repository.QuestionnaireRepository;
import zw.co.zim.willplatform.service.QuestionnaireService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final QuestionnaireRepository repository;

    public QuestionnaireServiceImpl(QuestionnaireRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Questionnaire> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<Questionnaire> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        return repository.save(questionnaire);
    }

    @Override
    public Questionnaire update(Long id, Questionnaire questionnaire) {
        Optional<Questionnaire> optional = this.findById(id);
        if (optional.isPresent()) {
            Questionnaire currentQuestionnaire = getQuestionnaire(questionnaire, optional);
            return repository.save(currentQuestionnaire);
        }
        return questionnaire;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Questionnaire> optional = this.findById(id);
        if (optional.isPresent()) {
            Questionnaire currentQuestionnaire = optional.get();
            currentQuestionnaire.setRecordStatus(RecordStatus.DELETED);
            repository.save(currentQuestionnaire);
        }
    }

    @Override
    public Page<Questionnaire> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<Questionnaire> findFirstByUserId(Client userId) {
        return repository.findFirstByUserIdAndRecordStatusNot(userId, RecordStatus.DELETED);
    }

    private static Questionnaire getQuestionnaire(Questionnaire questionnaire, Optional<Questionnaire> optional) {
        Questionnaire currentQuestionnaire = optional.get();
        currentQuestionnaire.setBusinessOwner(questionnaire.getBusinessOwner());
        currentQuestionnaire.setFormerSpouse(questionnaire.getFormerSpouse());
        currentQuestionnaire.setEducationalLevel(questionnaire.getEducationalLevel());
        currentQuestionnaire.setHaveAssetsOffshore(questionnaire.getHaveAssetsOffshore());
        currentQuestionnaire.setHaveAssetsTrust(questionnaire.getHaveAssetsTrust());
        currentQuestionnaire.setHaveAssetsOffshore(questionnaire.getHaveAssetsOffshore());
        currentQuestionnaire.setHaveBankAccount(questionnaire.getHaveBankAccount());
        currentQuestionnaire.setHaveChildren(questionnaire.getHaveChildren());
        currentQuestionnaire.setHaveCreditCard(questionnaire.getHaveCreditCard());
        currentQuestionnaire.setHaveDependenciesOverSeas(questionnaire.getHaveDependenciesOverSeas());
        currentQuestionnaire.setHaveFireArm(questionnaire.getHaveFireArm());
        currentQuestionnaire.setHaveInvestment(questionnaire.getHaveInvestment());
        currentQuestionnaire.setHaveMedicalAid(questionnaire.getHaveMedicalAid());
        currentQuestionnaire.setHaveOtherAssets(questionnaire.getHaveOtherAssets());
        currentQuestionnaire.setHaveOutstandingAccounts(questionnaire.getHaveOutstandingAccounts());
        currentQuestionnaire.setHaveOutstandingLoans(questionnaire.getHaveOutstandingLoans());
        currentQuestionnaire.setHaveOverseaWill(questionnaire.getHaveOverseaWill());
        currentQuestionnaire.setHavePolicy(questionnaire.getHavePolicy());
        currentQuestionnaire.setHaveVehicle(questionnaire.getHaveVehicle());
        currentQuestionnaire.setHaveProperty(questionnaire.getHaveProperty());
        currentQuestionnaire.setHaveTimeShare(questionnaire.getHaveTimeShare());
        currentQuestionnaire.setMedicalAidName(questionnaire.getMedicalAidName());
        currentQuestionnaire.setMonthlyHouseholdExpense(questionnaire.getMonthlyHouseholdExpense());
        currentQuestionnaire.setMonthlyIncome(questionnaire.getMonthlyIncome());
        currentQuestionnaire.setOccupation(questionnaire.getOccupation());
        currentQuestionnaire.setNumberOfFireArms(questionnaire.getNumberOfFireArms());
        currentQuestionnaire.setRegisteredTaxPayer(questionnaire.getRegisteredTaxPayer());
        currentQuestionnaire.setSmoker(questionnaire.getSmoker());
        currentQuestionnaire.setSomeOneOweMoney(questionnaire.getSomeOneOweMoney());
        currentQuestionnaire.setUserOweMoney(questionnaire.getUserOweMoney());
        currentQuestionnaire.setSubscribedWrittenPublication(questionnaire.getSubscribedWrittenPublication());
        currentQuestionnaire.setUserId(questionnaire.getUserId());
        currentQuestionnaire.setRecordStatus(questionnaire.getRecordStatus());
        return currentQuestionnaire;
    }
}
