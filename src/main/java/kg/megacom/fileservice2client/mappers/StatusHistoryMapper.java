package kg.megacom.fileservice2client.mappers;
import kg.megacom.fileservice2client.models.StatusHistory;
import kg.megacom.fileservice2client.models.User;
import kg.megacom.fileservice2client.models.dto.StatusHistoryDto;
import kg.megacom.fileservice2client.models.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatusHistoryMapper {

    StatusHistoryMapper INSTANCE = Mappers.getMapper(StatusHistoryMapper.class);

    StatusHistory toEntity(StatusHistoryDto statusHistoryDto);
    StatusHistoryDto toDto(StatusHistory user);

    List<StatusHistory> toEntities(List<StatusHistoryDto>statusHistoryDtos);

    List<StatusHistoryDto> toDtos(List<StatusHistory>statusHistories);



}

//E toEntity(D d);
//    D toDto(E e);
//
//    List<E> toEntities(List<D>d);
//    List<D> toDtos(List<E>e);