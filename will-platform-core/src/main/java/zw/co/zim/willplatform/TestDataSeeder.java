package zw.co.zim.willplatform;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.model.SystemUser;
import zw.co.zim.willplatform.repository.ClientRepository;
import zw.co.zim.willplatform.repository.CurrencyRepository;
import zw.co.zim.willplatform.repository.SystemUserRepository;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.CurrencyService;
import zw.co.zim.willplatform.service.SystemUserService;
import zw.co.zim.willplatform.utils.enums.OTPDeliveryChannel;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.enums.RoleEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@Builder
@Data
@Profile({"dev"})
public class TestDataSeeder {

    private final SystemUserService systemUserService;
    private final SystemUserRepository userRepository;
    private final ClientsService clientsService;
    private final ClientRepository clientRepository;
    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    public TestDataSeeder(SystemUserService systemUserService, SystemUserRepository userRepository, ClientsService clientsService, ClientRepository clientRepository, CurrencyService currencyService, CurrencyRepository currencyRepository) {
        this.systemUserService = systemUserService;
        this.userRepository = userRepository;
        this.clientsService = clientsService;
        this.clientRepository = clientRepository;
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
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
                    .dateOfBirth(LocalDate.now().minusYears(new Random().nextInt(20,33)))
                    .recordStatus(RecordStatus.ACTIVE)
                    .sendOtpVia(OTPDeliveryChannel.SMS)
                    .acceptedTermsAndConditions(true)
                    .firstLanguage("English")
                    .build();

            systemUserService.save(user);
            clientsService.save(client);

        }
        seedCurrency();
    }

    private void seedCurrency() {
        currencyRepository.deleteAll();
        List<Currency> currencies = List.of(Currency.builder()
                        .conversionRate(1D)
                        .iso("USD")
                        .symbol("$")
                        .name("USD")
                        .recordStatus(RecordStatus.ACTIVE)
                        .build(),

                Currency.builder()
                        .conversionRate(13.56D)
                        .iso("ZiG")
                        .symbol("ZiG")
                        .name("ZiG")
                        .recordStatus(RecordStatus.ACTIVE)
                        .build());

        for (Currency currency : currencies) {
            currencyService.save(currency);
        }
    }
}
