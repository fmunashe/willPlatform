package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.repository.SignupProgressJourneyRepository;
import zw.co.zim.willplatform.service.SignupProgressJourneyService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class SignupProgressJourneyServiceImpl implements SignupProgressJourneyService {
    private final SignupProgressJourneyRepository repository;

    public SignupProgressJourneyServiceImpl(SignupProgressJourneyRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<SignupProgressJourney> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<SignupProgressJourney> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public SignupProgressJourney save(SignupProgressJourney signupProgressJourney) {
        return repository.save(signupProgressJourney);
    }

    @Override
    public SignupProgressJourney update(Long id, SignupProgressJourney signupProgressJourney) {
        Optional<SignupProgressJourney> optional = this.findById(id);
        if (optional.isPresent()) {
            SignupProgressJourney journey = optional.get();
            journey.setSubscribed(signupProgressJourney.getSubscribed());
            journey.setCompletedAssetsSection(signupProgressJourney.getCompletedAssetsSection());
            journey.setUserId(signupProgressJourney.getUserId());
            journey.setCompletedQuestionnaire(signupProgressJourney.getCompletedQuestionnaire());
            journey.setCompletedLiabilitiesSection(signupProgressJourney.getCompletedLiabilitiesSection());
            journey.setFinalisedWill(signupProgressJourney.getFinalisedWill());
            journey.setCompletedAssetsSection(signupProgressJourney.getCompletedAssetsSection());
            journey.setCompletedSpouseSection(signupProgressJourney.getCompletedSpouseSection());
            journey.setCompletedWillSection(signupProgressJourney.getCompletedWillSection());
            journey.setWillComplete(signupProgressJourney.getWillComplete());
            journey.setRecordStatus(signupProgressJourney.getRecordStatus());
            return repository.save(journey);
        }
        return signupProgressJourney;
    }

    @Override
    public void deleteById(Long id) {
        Optional<SignupProgressJourney> optional = this.findById(id);
        if (optional.isPresent()) {
            SignupProgressJourney journey = optional.get();
            journey.setRecordStatus(RecordStatus.DELETED);
            repository.save(journey);
        }
    }

    @Override
    public Page<SignupProgressJourney> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<SignupProgressJourney> findFirstByUserId(Client userId) {
        return repository.findFirstByUserIdAndRecordStatusNot(userId, RecordStatus.DELETED);
    }
}
