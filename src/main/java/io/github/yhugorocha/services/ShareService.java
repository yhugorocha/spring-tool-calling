package io.github.yhugorocha.services;

import io.github.yhugorocha.dto.ShareResponse;
import io.github.yhugorocha.repository.ShareRepository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class ShareService implements Supplier<ShareResponse> {

    private final ShareRepository shareRepository;

    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public ShareResponse get() {
        return new ShareResponse(shareRepository.findAll());
    }
}
