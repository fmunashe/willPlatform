package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;

import java.util.Optional;

public interface ClientsService extends BaseService<Client> {
    Page<Client> findAll(Integer pageNo, Integer pageSize);

    Optional<Client> findFirstByEmailOrNationalIdNumber(String email, String nationalId);

    public Optional<Client> findFirstByNationalIdNumber(String nationalId);

    public Optional<Client> findFirstByPassportNumber(String passportNumber);
}
