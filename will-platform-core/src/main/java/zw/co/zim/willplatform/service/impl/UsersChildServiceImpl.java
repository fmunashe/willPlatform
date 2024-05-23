package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersChild;
import zw.co.zim.willplatform.repository.UsersChildRepository;
import zw.co.zim.willplatform.service.UsersChildService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class UsersChildServiceImpl implements UsersChildService {
    private final UsersChildRepository repository;

    public UsersChildServiceImpl(UsersChildRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsersChild> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<UsersChild> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public UsersChild save(UsersChild usersChild) {
        return repository.save(usersChild);
    }

    @Override
    public UsersChild update(Long id, UsersChild usersChild) {
        Optional<UsersChild> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersChild child = optional.get();
            child.setName(usersChild.getName());
            child.setSurname(usersChild.getSurname());
            child.setUserId(usersChild.getUserId());
            child.setDob(usersChild.getDob());
            child.setTrustAge(usersChild.getTrustAge());
            child.setRecordStatus(usersChild.getRecordStatus());
            return repository.save(child);
        }
        return usersChild;
    }

    @Override
    public void deleteById(Long id) {
        Optional<UsersChild> optional = this.findById(id);
        if (optional.isPresent()) {
            UsersChild child = optional.get();
            child.setRecordStatus(RecordStatus.DELETED);
            repository.save(child);
        }
    }

    @Override
    public Page<UsersChild> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<UsersChild> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
