package zw.co.zim.willplatform;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.enums.RoleEnum;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SystemUser;
import zw.co.zim.willplatform.repository.ClientRepository;
import zw.co.zim.willplatform.repository.SystemUserRepository;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.SystemUserService;

import java.time.LocalDate;

@Component
@Builder
@Data
@Profile({"dev"})
public class TestDataSeeder {

    private final SystemUserService systemUserService;
    private final SystemUserRepository userRepository;
    private final ClientsService clientsService;
    private final ClientRepository clientRepository;

    public TestDataSeeder(SystemUserService systemUserService, SystemUserRepository userRepository, ClientsService clientsService, ClientRepository clientRepository) {
        this.systemUserService = systemUserService;
        this.userRepository = userRepository;
        this.clientsService = clientsService;
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    private void seedAgents() {
        userRepository.deleteAll();
        clientRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            SystemUser user = SystemUser.builder()
                .email("test" + i + "@gmail.com")
                .name("test" + i)
                .lastName("test" + i)
                .mobile("077823425" + i)
                .recordStatus(RecordStatus.ACTIVE)
                .role(RoleEnum.AGENT)
                .build();

            Client client = Client.builder()
                .email("client" + i + "@gmail.com")
                .firstName("client" + i)
                .lastName("client" + i)
                .dateOfBirth(LocalDate.now().minusYears(33))
                .recordStatus(RecordStatus.ACTIVE)
                .build();

            systemUserService.save(user);
            clientsService.save(client);
        }
    }
}
