package zw.co.zim.willplatform.processor;

public interface WillProcessor {
    byte[] generateLivingWill(Long client);

    byte[] generateWill(Long client);
}
