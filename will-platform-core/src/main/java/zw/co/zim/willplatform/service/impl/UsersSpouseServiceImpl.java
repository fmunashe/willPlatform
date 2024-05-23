package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersSpouse;
import zw.co.zim.willplatform.repository.UsersSpouseRepository;
import zw.co.zim.willplatform.service.UsersSpouseService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UsersSpouseServiceImpl implements UsersSpouseService {
    private final UsersSpouseRepository repository;

    public UsersSpouseServiceImpl(UsersSpouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsersSpouse> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<UsersSpouse> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public UsersSpouse save(UsersSpouse usersSpouse) {
        return repository.save(usersSpouse);
    }

    @Override
    public UsersSpouse update(Long id, UsersSpouse usersSpouse) {
        Optional<UsersSpouse> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersSpouse spouse = optional.get();
            spouse.setName(usersSpouse.getName());
            spouse.setSurname(usersSpouse.getSurname());
            spouse.setEmail(usersSpouse.getEmail());
            spouse.setMobile(usersSpouse.getMobile());
            spouse.setUserId(usersSpouse.getUserId());
            spouse.setMaritalStatus(usersSpouse.getMaritalStatus());
            spouse.setCivillyMarriedStatus(usersSpouse.getCivillyMarriedStatus());
            spouse.setIdNumber(usersSpouse.getIdNumber());
            spouse.setRecordStatus(usersSpouse.getRecordStatus());
            return repository.save(spouse);
        }
        return usersSpouse;
    }

    @Override
    public void deleteById(Long id) {
        Optional<UsersSpouse> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersSpouse spouse = optional.get();
            spouse.setRecordStatus(RecordStatus.DELETED);
            repository.save(spouse);
        }
    }

    @Override
    public Page<UsersSpouse> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<UsersSpouse> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
