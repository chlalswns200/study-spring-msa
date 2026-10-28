package mj.board.common.outboxmessagerelay;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDateTime;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
    List<Outbox> findAllByShardKeyAndCreateAtLessThanEqualOrderByCreateAtAsc(
            Long shardKey,
            LocalDateTime from,
            Pageable pageable
    );
}
