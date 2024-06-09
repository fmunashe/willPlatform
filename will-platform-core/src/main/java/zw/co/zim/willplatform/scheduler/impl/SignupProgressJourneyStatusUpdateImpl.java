package zw.co.zim.willplatform.scheduler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Questionnaire;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.scheduler.api.*;
import zw.co.zim.willplatform.service.QuestionnaireService;
import zw.co.zim.willplatform.service.SignupProgressJourneyService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SignupProgressJourneyStatusUpdateImpl implements SignupProgressJourneyStatusUpdate {
    private final SignupProgressJourneyService progressJourneyService;
    private final QuestionnaireService questionnaireService;
    private final GuardianSection guardianSection;
    private final ChildrenSection childrenSection;
    private final BeneficiarySection beneficiarySection;
    private final SpouseSection spouseSection;
    private final PersonalSection personalSection;
    private final AssetsSection assetsSection;

    public SignupProgressJourneyStatusUpdateImpl(
        SignupProgressJourneyService progressJourneyService,
        QuestionnaireService questionnaireService,
        GuardianSection guardianSection,
        ChildrenSection childrenSection,
        BeneficiarySection beneficiarySection,
        SpouseSection spouseSection,
        PersonalSection personalSection, AssetsSection assetsSection) {
        this.progressJourneyService = progressJourneyService;
        this.questionnaireService = questionnaireService;
        this.guardianSection = guardianSection;
        this.childrenSection = childrenSection;
        this.beneficiarySection = beneficiarySection;
        this.spouseSection = spouseSection;
        this.personalSection = personalSection;
        this.assetsSection = assetsSection;
    }

    @Override
    public void assetsSection() {
        log.info("===== Checking Assets Section Signup Progress Journey =====");
        var completedAssets = new AtomicInteger(0);

        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream()
            .filter(progress -> !progress.getCompletedAssetsSection() && progress.getCompletedQuestionnaire())
            .toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            boolean vehicle = assetsSection.isAssetVehicleComplete(progressJourney);
            boolean property = assetsSection.isAssetPropertyComplete(progressJourney);
            boolean trust = assetsSection.isAssetTrustComplete(progressJourney);
            boolean investment = assetsSection.isAssetInvestmentComplete(progressJourney);
            boolean bank = assetsSection.isAssetBankComplete(progressJourney);
            boolean policy = assetsSection.isAssetPolicyComplete(progressJourney);
            boolean other = assetsSection.isAssetOtherComplete(progressJourney);
            boolean offshore = assetsSection.isAssetOffshoreComplete(progressJourney);
            boolean timeshare = assetsSection.isAssetTimeshareComplete(progressJourney);
            boolean personOwingMoney = assetsSection.isAssetPersonOwingMoneyComplete(progressJourney);
            if (vehicle
                && property
                && trust
                && investment
                && bank
                && policy
                && other
                && offshore
                && timeshare
                && personOwingMoney) {
                progressJourney.setCompletedPersonalSection(true);
                progressJourneyService.save(progressJourney);
                completedAssets.incrementAndGet();
            }
        });

        log.info("===== {} Completed assets section =====", completedAssets);
    }

    @Override
    public void liabilitiesSection() {

    }

    @Override
    @Scheduled(cron = "* */30 * * * *")
    public void personalSection() {
        log.info("===== Checking Personal Section Signup Progress Journey =====");
        var completed = new AtomicInteger(0);

        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream()
            .filter(progress -> !progress.getCompletedPersonalSection())
            .toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            boolean isPersonalSectionComplete = personalSection.isPersonalSectionComplete(progressJourney);
            if (isPersonalSectionComplete) {
                progressJourney.setCompletedPersonalSection(true);
                progressJourneyService.save(progressJourney);
                completed.incrementAndGet();
            }
        });

        log.info("===== {} Completed personal section =====", completed);
    }

    @Override
    @Scheduled(cron = "* */30 * * * *")
    public void questionnaireSection() {
        log.info("===== Checking Questionnaire Section Signup Progress Journey =====");
        var total = new AtomicInteger(0);
        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream().filter(progress -> progress.getCompletedQuestionnaire().equals(false)).toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            Client client = progressJourney.getUserId();

            Optional<Questionnaire> optionalQuestionnaire = questionnaireService.findFirstByUserId(client);

            if (optionalQuestionnaire.isPresent()) {
                progressJourney.setCompletedQuestionnaire(true);
                progressJourneyService.save(progressJourney);
                total.incrementAndGet();
            }
        });
        log.info("===== {} Completed Questionnaire section =====", total);
    }

    @Override
    @Scheduled(cron = "* */30 * * * *")
    public void spouseSection() {
        log.info("===== Checking Spouse Section Signup Progress Journey =====");
        var totalSpouse = new AtomicInteger(0);

        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream()
            .filter(progress -> !progress.getCompletedSpouseSection() && progress.getCompletedQuestionnaire())
            .toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            boolean shouldCompleteSpouseSection = spouseSection.completeSpouseSection(progressJourney);
            if (shouldCompleteSpouseSection) {
                progressJourney.setCompletedSpouseSection(true);
                progressJourneyService.save(progressJourney);
                totalSpouse.incrementAndGet();
            }
        });

        log.info("===== {} Completed Spouse Section =====", totalSpouse);
    }

    @Override
    @Scheduled(cron = "* */30 * * * *")
    public void beneficiarySection() {
        log.info("===== Checking Beneficiaries Section Signup Progress Journey =====");
        var totalBeneficiaries = new AtomicInteger(0);

        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream()
            .filter(progress -> !progress.getCompletedBeneficiarySection() && progress.getCompletedQuestionnaire())
            .toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            boolean shouldCompleteBeneficiarySection = beneficiarySection.completeBeneficiarySection(progressJourney);
            if (shouldCompleteBeneficiarySection) {
                progressJourney.setCompletedBeneficiarySection(true);
                progressJourneyService.save(progressJourney);
                totalBeneficiaries.incrementAndGet();
            }
        });

        log.info("===== {} Completed Beneficiaries Section =====", totalBeneficiaries);

    }

    @Override
    @Scheduled(cron = "* */30 * * * *")
    public void childrenSection() {
        log.info("===== Checking Children Section Signup Progress Journey =====");
        var totalChildren = new AtomicInteger(0);

        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream()
            .filter(progress -> !progress.getCompletedChildrenSection() && progress.getCompletedQuestionnaire())
            .toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            boolean shouldCompleteChildrenSection = childrenSection.completeChildrenSection(progressJourney);
            if (shouldCompleteChildrenSection) {
                progressJourney.setCompletedChildrenSection(true);
                progressJourneyService.save(progressJourney);
                totalChildren.incrementAndGet();
            }
        });

        log.info("===== {} Completed Children Section =====", totalChildren);
    }

    @Override
    @Scheduled(cron = "* */30 * * * *")
    public void guardiansSection() {
        log.info("===== Checking Guardian Section Signup Progress Journey =====");
        var totalGuardian = new AtomicInteger(0);

        List<SignupProgressJourney> progressJourneyList = progressJourneyService.findAll().stream()
            .filter(progress -> !progress.getCompletedGuardianSection() && progress.getCompletedQuestionnaire())
            .toList();

        progressJourneyList.parallelStream().forEach(progressJourney -> {
            boolean shouldCompleteGuardianSection = guardianSection.completeGuardianSection(progressJourney);
            if (shouldCompleteGuardianSection) {
                progressJourney.setCompletedGuardianSection(true);
                progressJourneyService.save(progressJourney);
                totalGuardian.incrementAndGet();
            }
        });

        log.info("===== {} Completed Guardian Section =====", totalGuardian);
    }

    @Override
    public void willSection() {

    }

    @Override
    public void finalisedSection() {

    }

    @Override
    public void WrittenSubscriptionSection() {

    }

}
