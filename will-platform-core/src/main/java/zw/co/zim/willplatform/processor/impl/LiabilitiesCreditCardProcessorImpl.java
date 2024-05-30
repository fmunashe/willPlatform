package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesCreditCardDto;
import zw.co.zim.willplatform.dto.mapper.LiabilitiesCreditCardDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesCreditCard;
import zw.co.zim.willplatform.processor.LiabilitiesCreditCardProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.LiabilitiesCreditCardService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.CreditCardRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class LiabilitiesCreditCardProcessorImpl implements LiabilitiesCreditCardProcessor {
    private final LiabilitiesCreditCardService creditCardService;
    private final ClientsService clientsService;
    private final LiabilitiesCreditCardDtoMapper mapper;
    private final ModelMapper modelMapper;

    public LiabilitiesCreditCardProcessorImpl(LiabilitiesCreditCardService creditCardService, ClientsService clientsService, LiabilitiesCreditCardDtoMapper mapper, ModelMapper modelMapper) {
        this.creditCardService = creditCardService;
        this.clientsService = clientsService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<LiabilitiesCreditCardDto> findAll(Integer pageNo, Integer pageSize) {
        Page<LiabilitiesCreditCard> creditCards = creditCardService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(creditCards, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesCreditCardDto> findById(Long id) {
        Optional<LiabilitiesCreditCard> optional = creditCardService.findById(id);

        return optional.map(card -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(card)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find credit card record with Id of " + id));

    }

    @Override
    public ApiResponse<LiabilitiesCreditCardDto> save(CreditCardRequest creditCardRequest) {

        Optional<Client> optionalClient = clientsService.findById(creditCardRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + creditCardRequest.getClientId());
        }
        LiabilitiesCreditCardDto recordDto = LiabilitiesCreditCardDto.builder()
            .userId(optionalClient.get())
            .nameOfInstitution(creditCardRequest.getNameOfInstitution())
            .cardValue(creditCardRequest.getCardValue())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        LiabilitiesCreditCard card = modelMapper.map(recordDto, LiabilitiesCreditCard.class);
        card = creditCardService.save(card);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(card));
    }

    @Override
    public ApiResponse<LiabilitiesCreditCardDto> update(Long id, LiabilitiesCreditCardDto liabilitiesCreditCardDto) {

        Optional<LiabilitiesCreditCard> card = creditCardService.findById(id);

        if (card.isEmpty() || !Objects.equals(card.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find credit card record with Id " + id);
        }
        LiabilitiesCreditCard updatedRecord = creditCardService.update(id, modelMapper.map(liabilitiesCreditCardDto, LiabilitiesCreditCard.class));
        LiabilitiesCreditCardDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<LiabilitiesCreditCardDto> deleteById(Long id) {
        Optional<LiabilitiesCreditCard> creditCard = creditCardService.findById(id);

        if (creditCard.isEmpty() || !creditCard.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find credit card record with Id " + id);
        }
        creditCardService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesCreditCardDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<LiabilitiesCreditCard> cardPage = creditCardService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(cardPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }
}
