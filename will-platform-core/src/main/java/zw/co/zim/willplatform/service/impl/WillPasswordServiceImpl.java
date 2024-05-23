package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillPasswordsInstructions;
import zw.co.zim.willplatform.repository.WillPasswordInstructionsRepository;
import zw.co.zim.willplatform.service.WillPasswordsService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class WillPasswordServiceImpl implements WillPasswordsService {
    private final WillPasswordInstructionsRepository repository;

    public WillPasswordServiceImpl(WillPasswordInstructionsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WillPasswordsInstructions> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<WillPasswordsInstructions> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public WillPasswordsInstructions save(WillPasswordsInstructions willPasswordsInstructions) {
        return repository.save(willPasswordsInstructions);
    }

    @Override
    public WillPasswordsInstructions update(Long id, WillPasswordsInstructions willPasswordsInstructions) {
        Optional<WillPasswordsInstructions> optional = this.findById(id);
        if (optional.isPresent()) {
            WillPasswordsInstructions wpi = optional.get();
            wpi.setUserId(willPasswordsInstructions.getUserId());
            wpi.setInstructions(willPasswordsInstructions.getInstructions());
            wpi.setRecordStatus(willPasswordsInstructions.getRecordStatus());
            return repository.save(wpi);
        }
        return willPasswordsInstructions;
    }

    @Override
    public void deleteById(Long id) {
        Optional<WillPasswordsInstructions> optional = this.findById(id);
        if (optional.isPresent()) {
            WillPasswordsInstructions wpi = optional.get();
            wpi.setRecordStatus(RecordStatus.DELETED);
            repository.save(wpi);
        }
    }

    @Override
    public Page<WillPasswordsInstructions> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<WillPasswordsInstructions> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
