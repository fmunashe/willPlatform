package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersGuardian;
import zw.co.zim.willplatform.repository.UsersGuardianRepository;
import zw.co.zim.willplatform.service.UsersGuardianService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UsersGuardianServiceImpl implements UsersGuardianService {

    private final UsersGuardianRepository repository;

    public UsersGuardianServiceImpl(UsersGuardianRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsersGuardian> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<UsersGuardian> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public UsersGuardian save(UsersGuardian usersGuardian) {
        return repository.save(usersGuardian);
    }

    @Override
    public UsersGuardian update(Long id, UsersGuardian usersGuardian) {
        Optional<UsersGuardian> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersGuardian guardian = optional.get();
            guardian.setName(usersGuardian.getName());
            guardian.setMiddleName(usersGuardian.getMiddleName());
            guardian.setSurname(usersGuardian.getSurname());
            guardian.setEmail(usersGuardian.getEmail());
            guardian.setContactNumber(usersGuardian.getContactNumber());
            guardian.setIdNumber(usersGuardian.getIdNumber());
            guardian.setPassportNumber(usersGuardian.getPassportNumber());
            guardian.setTitle(usersGuardian.getTitle());
            guardian.setRelationship(usersGuardian.getRelationship());
            guardian.setGender(usersGuardian.getGender());
            guardian.setDob(usersGuardian.getDob());
            guardian.setAddress(usersGuardian.getAddress());
            guardian.setUserId(usersGuardian.getUserId());
            guardian.setRecordStatus(usersGuardian.getRecordStatus());
            return repository.save(guardian);
        }
        return usersGuardian;
    }

    @Override
    public void deleteById(Long id) {
        Optional<UsersGuardian> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersGuardian guardian = optional.get();
            guardian.setRecordStatus(RecordStatus.DELETED);
            repository.save(guardian);
        }
    }

    @Override
    public Page<UsersGuardian> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<UsersGuardian> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
