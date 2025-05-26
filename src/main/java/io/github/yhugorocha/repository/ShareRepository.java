package io.github.yhugorocha.repository;

import io.github.yhugorocha.entity.ShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<ShareEntity, Long> {
}
