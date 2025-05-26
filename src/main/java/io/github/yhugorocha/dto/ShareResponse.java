package io.github.yhugorocha.dto;

import io.github.yhugorocha.entity.ShareEntity;

import java.util.List;

public record ShareResponse(List<ShareEntity> shares) {
}
