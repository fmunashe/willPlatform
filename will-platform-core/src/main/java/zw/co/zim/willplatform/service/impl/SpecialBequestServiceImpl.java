package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SpecialBequest;
import zw.co.zim.willplatform.repository.SpecialBequestRepository;
import zw.co.zim.willplatform.service.SpecialBequestService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialBequestServiceImpl implements SpecialBequestService {
    private final SpecialBequestRepository repository;

    public SpecialBequestServiceImpl(SpecialBequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SpecialBequest> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<SpecialBequest> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public SpecialBequest save(SpecialBequest specialBequest) {
        return repository.save(specialBequest);
    }

    @Override
    public SpecialBequest update(Long id, SpecialBequest specialBequest) {
        Optional<SpecialBequest> optional = this.findById(id);
        if (optional.isPresent()) {
            SpecialBequest bequest = optional.get();
            bequest.setSpecialBequestInformation(specialBequest.getSpecialBequestInformation());
            bequest.setUserId(specialBequest.getUserId());
            bequest.setPersonDetails(specialBequest.getPersonDetails());
            bequest.setRecordStatus(specialBequest.getRecordStatus());
            return repository.save(bequest);
        }
        return specialBequest;
    }

    @Override
    public void deleteById(Long id) {
        Optional<SpecialBequest> optional = this.findById(id);
        if (optional.isPresent()) {
            SpecialBequest bequest = optional.get();
            bequest.setRecordStatus(RecordStatus.DELETED);
            repository.save(bequest);
        }
    }

    @Override
    public Page<SpecialBequest> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<SpecialBequest> findFirstByUserId(Client userId) {
        return repository.findFirstByUserIdAndRecordStatusNot(userId, RecordStatus.DELETED);
    }
}
