package com.nttdata.BancaProdMoveActive.service;

import com.nttdata.BancaProdMoveActive.domain.MoveActive;
import com.nttdata.BancaProdMoveActive.repository.MoveActiveRepository;
import com.nttdata.BancaProdMoveActive.web.mapper.MoveActiveMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MoveActiveService {
    @Autowired
    private MoveActiveRepository moveActiveRepository;

    @Autowired
    private MoveActiveMapper moveActiveMapper;

    public Flux<MoveActive> findAll(){
        log.debug("findAll executed");
        return moveActiveRepository.findAll();
    }

    public Mono<MoveActive> findById(String moveactiveId){
        log.debug("findById executed {}", moveactiveId);
        return moveActiveRepository.findById(moveactiveId);
    }


    public Mono<MoveActive> create(MoveActive moveActive){
        log.debug("create executed {}", moveActive);
        return moveActiveRepository.save(moveActive);
    }


    public Mono<MoveActive> update(String moveactiveId,  MoveActive moveActive){
        log.debug("update executed {}:{}", moveactiveId, moveActive);
        return moveActiveRepository.findById(moveactiveId)
                .flatMap(dbMoveActive -> {
                    moveActiveMapper.update(dbMoveActive, moveActive);
                    return moveActiveRepository.save(dbMoveActive);
                });
    }


    public Mono<MoveActive> delete(String moveactiveId){
        log.debug("delete executed {}", moveactiveId);
        return moveActiveRepository.findById(moveactiveId)
                .flatMap(existingMoveActive -> moveActiveRepository.delete(existingMoveActive)
                        .then(Mono.just(existingMoveActive)));
    }
}
