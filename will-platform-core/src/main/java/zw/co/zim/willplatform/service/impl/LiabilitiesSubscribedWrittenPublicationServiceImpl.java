package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesSubscribedWrittenPublication;
import zw.co.zim.willplatform.repository.LiabilitiesSubscribedWrittenPublicationRepository;
import zw.co.zim.willplatform.service.LiabilitiesSubscribedWrittenPublicationService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class LiabilitiesSubscribedWrittenPublicationServiceImpl implements LiabilitiesSubscribedWrittenPublicationService {

    private final LiabilitiesSubscribedWrittenPublicationRepository repository;

    public LiabilitiesSubscribedWrittenPublicationServiceImpl(LiabilitiesSubscribedWrittenPublicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LiabilitiesSubscribedWrittenPublication> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LiabilitiesSubscribedWrittenPublication> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public LiabilitiesSubscribedWrittenPublication save(LiabilitiesSubscribedWrittenPublication liabilitiesSubscribedWrittenPublication) {
        return repository.save(liabilitiesSubscribedWrittenPublication);
    }

    @Override
    public LiabilitiesSubscribedWrittenPublication update(Long id, LiabilitiesSubscribedWrittenPublication liabilitiesSubscribedWrittenPublication) {
        Optional<LiabilitiesSubscribedWrittenPublication> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesSubscribedWrittenPublication publication = optional.get();
            publication.setNameOfPublication(liabilitiesSubscribedWrittenPublication.getNameOfPublication());
            publication.setUserId(liabilitiesSubscribedWrittenPublication.getUserId());
            publication.setRecordStatus(liabilitiesSubscribedWrittenPublication.getRecordStatus());
            return repository.save(publication);
        }
        return liabilitiesSubscribedWrittenPublication;
    }

    @Override
    public void deleteById(Long id) {
        Optional<LiabilitiesSubscribedWrittenPublication> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesSubscribedWrittenPublication publication = optional.get();
            publication.setRecordStatus(RecordStatus.DELETED);
            repository.save(publication);
        }
    }

    @Override
    public Page<LiabilitiesSubscribedWrittenPublication> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<LiabilitiesSubscribedWrittenPublication> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }
}
