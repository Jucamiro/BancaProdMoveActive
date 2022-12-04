package com.nttdata.BancaProdMoveActive.repository;

import com.nttdata.BancaProdMoveActive.domain.MoveActive;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MoveActiveRepository extends ReactiveMongoRepository<MoveActive, String> {
    Mono<MoveActive> findBynumberContract(String numberContract);
}
