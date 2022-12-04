package com.nttdata.BancaProdMoveActive.web;

import com.nttdata.BancaProdMoveActive.domain.MoveActive;
import com.nttdata.BancaProdMoveActive.service.MoveActiveService;
import com.nttdata.BancaProdMoveActive.web.mapper.MoveActiveMapper;
import com.nttdata.BancaProdMoveActive.web.model.MoveActiveModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/moveactive")
public class MoveActiveController {
    @Value("${spring.application.name}")
    String name;

    @Value("${server.port}")
    String port;

    @Autowired
    private MoveActiveService moveActiveService;

    @Autowired
    private MoveActiveMapper moveActiveMapper;

    @GetMapping()
    public Mono<ResponseEntity<Flux<MoveActiveModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(moveActiveService.findAll()
                        .map(movePassive -> moveActiveMapper.entityToModel(movePassive))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<MoveActiveModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        Mono<MoveActive> response = moveActiveService.findById(id);
        return response
                .map(moveActive -> moveActiveMapper.entityToModel(moveActive))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<MoveActiveModel>> create(@Valid @RequestBody MoveActiveModel request){
        log.info("create executed {}", request);
        return moveActiveService.create(moveActiveMapper.modelToEntity(request))
                .map(moveActive -> moveActiveMapper.entityToModel(moveActive))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "moveActive", c.getIdMoveActive())))
                        .body(c)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<MoveActiveModel>> updateById(@PathVariable String id, @Valid @RequestBody MoveActiveModel request){
        log.info("updateById executed {}:{}", id, request);
        return moveActiveService.update(id, moveActiveMapper.modelToEntity(request))
                .map(moveActive -> moveActiveMapper.entityToModel(moveActive))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "moveActive", c.getIdMoveActive())))
                        .body(c)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        log.info("deleteById executed {}", id);
        return moveActiveService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
