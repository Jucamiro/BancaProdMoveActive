package com.nttdata.BancaProdMoveActive.web.mapper;

import com.nttdata.BancaProdMoveActive.domain.MoveActive;
import com.nttdata.BancaProdMoveActive.web.model.MoveActiveModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MoveActiveMapper {
    MoveActive modelToEntity (MoveActiveModel model);

    MoveActiveModel entityToModel (MoveActive event);

    @Mapping(target="idMoveActive", ignore = true)
    void update(@MappingTarget MoveActive entity, MoveActive updateEntity);
}
