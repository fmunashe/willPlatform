package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findFirstByNationalIdNumber(String nationalId);

    public Optional<User> findFirstByPassportNumber(String passportNumber);
}
