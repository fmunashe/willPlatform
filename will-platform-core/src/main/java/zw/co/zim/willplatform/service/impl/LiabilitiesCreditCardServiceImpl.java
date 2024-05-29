package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesCreditCard;
import zw.co.zim.willplatform.repository.LiabilitiesCreditCardRepository;
import zw.co.zim.willplatform.service.LiabilitiesCreditCardService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class LiabilitiesCreditCardServiceImpl implements LiabilitiesCreditCardService {
    private final LiabilitiesCreditCardRepository repository;

    public LiabilitiesCreditCardServiceImpl(LiabilitiesCreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LiabilitiesCreditCard> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LiabilitiesCreditCard> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public LiabilitiesCreditCard save(LiabilitiesCreditCard liabilitiesCreditCard) {
        return repository.save(liabilitiesCreditCard);
    }

    @Override
    public LiabilitiesCreditCard update(Long id, LiabilitiesCreditCard liabilitiesCreditCard) {
        Optional<LiabilitiesCreditCard> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesCreditCard creditCard = optional.get();
            creditCard.setUserId(liabilitiesCreditCard.getUserId());
            creditCard.setCardValue(liabilitiesCreditCard.getCardValue());
            creditCard.setNameOfInstitution(liabilitiesCreditCard.getNameOfInstitution());
            creditCard.setRecordStatus(liabilitiesCreditCard.getRecordStatus());
            return repository.save(creditCard);
        }
        return liabilitiesCreditCard;
    }

    @Override
    public void deleteById(Long id) {
        Optional<LiabilitiesCreditCard> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesCreditCard creditCard = optional.get();
            creditCard.setRecordStatus(RecordStatus.DELETED);
            repository.save(creditCard);
        }
    }

    @Override
    public Page<LiabilitiesCreditCard> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public List<LiabilitiesCreditCard> findAllByUserId(Client clientId) {
        return repository.findAllByUserIdAndRecordStatusNot(clientId, RecordStatus.DELETED);
    }
}
