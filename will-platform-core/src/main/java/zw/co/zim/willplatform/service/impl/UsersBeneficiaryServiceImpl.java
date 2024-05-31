package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersBeneficiary;
import zw.co.zim.willplatform.repository.UsersBeneficiaryRepository;
import zw.co.zim.willplatform.service.UsersBeneficiaryService;
import zw.co.zim.willplatform.utils.PageableHelper;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.util.List;
import java.util.Optional;

@Service
public class UsersBeneficiaryServiceImpl implements UsersBeneficiaryService {
    private final UsersBeneficiaryRepository repository;

    public UsersBeneficiaryServiceImpl(UsersBeneficiaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsersBeneficiary> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<UsersBeneficiary> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Optional<UsersBeneficiary> findFirstByClientIdAndEmail(Client clientId, String email) {
        return repository.findFirstByUserIdAndEmailAndRecordStatusNot(clientId, email, RecordStatus.DELETED);
    }

    @Override
    public Optional<UsersBeneficiary> findFirstByClientIdAndIDNumber(Client clientId, String idNumber) {
        return repository.findFirstByUserIdAndIdNumberAndRecordStatusNot(clientId, idNumber, RecordStatus.DELETED);
    }

    @Override
    public UsersBeneficiary save(UsersBeneficiary usersBeneficiary) {
        return repository.save(usersBeneficiary);
    }

    @Override
    public UsersBeneficiary update(Long id, UsersBeneficiary usersBeneficiary) {
        Optional<UsersBeneficiary> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersBeneficiary beneficiary = optional.get();
            beneficiary.setName(usersBeneficiary.getName());
            beneficiary.setMiddleName(usersBeneficiary.getMiddleName());
            beneficiary.setLastName(usersBeneficiary.getLastName());
            beneficiary.setEmail(usersBeneficiary.getEmail());
            beneficiary.setPassportNumber(usersBeneficiary.getPassportNumber());
            beneficiary.setIdNumber(usersBeneficiary.getIdNumber());
            beneficiary.setContactNumber(usersBeneficiary.getContactNumber());
            beneficiary.setTitle(usersBeneficiary.getTitle());
            beneficiary.setGender(usersBeneficiary.getGender());
            beneficiary.setDob(usersBeneficiary.getDob());
            beneficiary.setPercentage(usersBeneficiary.getPercentage());
            beneficiary.setAddress(usersBeneficiary.getAddress());
            beneficiary.setUserId(usersBeneficiary.getUserId());
            beneficiary.setInstructions(usersBeneficiary.getInstructions());
            beneficiary.setTrustName(usersBeneficiary.getTrustName());
            beneficiary.setTrustNumber(usersBeneficiary.getTrustNumber());
            beneficiary.setOverEighteen(usersBeneficiary.getOverEighteen());
            beneficiary.setRelationship(usersBeneficiary.getRelationship());
            beneficiary.setRecordStatus(usersBeneficiary.getRecordStatus());
            return repository.save(beneficiary);
        }
        return usersBeneficiary;
    }

    @Override
    public void deleteById(Long id) {
        Optional<UsersBeneficiary> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersBeneficiary beneficiary = optional.get();
            beneficiary.setRecordStatus(RecordStatus.DELETED);
            repository.save(beneficiary);
        }
    }

    @Override
    public Page<UsersBeneficiary> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<UsersBeneficiary> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
