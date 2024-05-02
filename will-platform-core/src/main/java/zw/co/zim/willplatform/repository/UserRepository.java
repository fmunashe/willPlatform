package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Client, Long> {
    public Optional<Client> findFirstByNationalIdNumber(String nationalId);

    public Optional<Client> findFirstByPassportNumber(String passportNumber);
}
