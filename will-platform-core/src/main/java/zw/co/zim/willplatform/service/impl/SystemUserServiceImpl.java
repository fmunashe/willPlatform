package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.enums.RoleEnum;
import zw.co.zim.willplatform.model.SystemUser;
import zw.co.zim.willplatform.repository.SystemUserRepository;
import zw.co.zim.willplatform.service.SystemUserService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository repository;

    public SystemUserServiceImpl(SystemUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SystemUser> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<SystemUser> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public SystemUser save(SystemUser systemUser) {
        return repository.save(systemUser);
    }

    @Override
    public SystemUser update(Long id, SystemUser systemUser) {
        Optional<SystemUser> optional = this.findById(id);
        if (optional.isPresent()) {
            SystemUser currentUser = optional.get();
            currentUser.setEmail(systemUser.getEmail());
            currentUser.setName(systemUser.getName());
            currentUser.setMobile(systemUser.getMobile());
            currentUser.setRole(systemUser.getRole());
            currentUser.setLastName(systemUser.getLastName());
            currentUser.setMiddleName(systemUser.getMiddleName());
            currentUser.setRecordStatus(systemUser.getRecordStatus());
            return repository.save(currentUser);
        }
        return systemUser;
    }

    @Override
    public void deleteById(Long id) {
        Optional<SystemUser> optional = this.findById(id);
        if (optional.isPresent()) {
            SystemUser currentUser = optional.get();
            currentUser.setRecordStatus(RecordStatus.DELETED);
            repository.save(currentUser);
        }
    }

    @Override
    public Page<SystemUser> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<SystemUser> findAllByRole(RoleEnum role, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRoleAndRecordStatusNot(pageable, role, RecordStatus.DELETED);
    }

    @Override
    public Optional<SystemUser> findFirstByEmail(String email) {
        return repository.findFirstByEmailAndRecordStatusNot(email, RecordStatus.DELETED);
    }
}
