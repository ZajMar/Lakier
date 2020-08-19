package pl.lacquer.lacquerapp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import pl.lacquer.lacquerapp.dto.LacquerGroupDTO;
import pl.lacquer.lacquerapp.dto.LacquerGroupElementDTO;
import pl.lacquer.lacquerapp.model.LacquerGroupElementInternal;
import pl.lacquer.lacquerapp.model.LacquerGroupInternal;
import pl.lacquer.lacquerapp.repository.model.LacquerGroup;
import pl.lacquer.lacquerapp.repository.model.LacquerGroupElement;

/**
 * Created by Marcin Zając on 2020-08-10
 */
@UtilityClass
public class LacquerGroupMapper {

    public static LacquerGroupInternal mapTo(LacquerGroupDTO lacquerGroupDTO) {
        return LacquerGroupInternal.builder()
                   .groupId(lacquerGroupDTO.getGroupId())
                   .allowDuplicates(lacquerGroupDTO.isAllowDuplicates())
                   .groupName(lacquerGroupDTO.getGroupName())
                   .sortBy(lacquerGroupDTO.getSortBy())
                   .sortOrder(lacquerGroupDTO.getSortOrder())
                   .lacquerGroupElementInternals(mapGroupElements(lacquerGroupDTO))
                   .build();
    }

    private static List<LacquerGroupElementInternal> mapGroupElements(LacquerGroupDTO lacquerGroupDTO) {
        return lacquerGroupDTO.getLacquerGroupElements().stream()
                   .map(LacquerGroupMapper::mapTo)
                   .collect(Collectors.toList());
    }

    private static LacquerGroupElementInternal mapTo(LacquerGroupElementDTO lacquerGroupElementDTO) {
        return LacquerGroupElementInternal.builder()
                   .lacquerId(lacquerGroupElementDTO.getLacquerId())
                   .lacquerCode(lacquerGroupElementDTO.getLacquerCode())
                   .lacquerName(lacquerGroupElementDTO.getLacquerName())
                   .position(lacquerGroupElementDTO.getPosition())
                   .lacquerBrand(lacquerGroupElementDTO.getLacquerBrand())
                   .lacquerPopularity(lacquerGroupElementDTO.getLacquerPopularity())
                   .build();
    }

    public static LacquerGroupInternal mapTo(LacquerGroup lacquerGroup) {
        return LacquerGroupInternal.builder()
                   .groupId(lacquerGroup.getGroupId())
                   .sortOrder(lacquerGroup.getSortOrder())
                   .sortBy(lacquerGroup.getSortBy())
                   .groupName(lacquerGroup.getGroupName())
                   .allowDuplicates(lacquerGroup.isAllowDuplicates())
                   .lacquerGroupElementInternals(lacquerGroup.getRelations().stream()
                                                     .map(LacquerGroupMapper::mapTo)
                                                     .collect(Collectors.toList()))
                   .build();
    }

    private static LacquerGroupElementInternal mapTo(LacquerGroupElement lacquerGroupElement) {
        return LacquerGroupElementInternal.builder()
                   .position(lacquerGroupElement.getPosition())
                   .lacquerName(lacquerGroupElement.getLacquer().getLacquerName())
                   .lacquerCode(lacquerGroupElement.getLacquer().getLacquerCode())
                   .lacquerId(lacquerGroupElement.getLacquer().getInternalId())
                   .lacquerBrand(lacquerGroupElement.getLacquer().getLacquerBrand())
                   .lacquerPopularity(lacquerGroupElement.getLacquer().getLacquerPopularity())
                   .build();
    }

    public static LacquerGroupDTO mapTo(LacquerGroupInternal lacquerGroupInternal) {
        return LacquerGroupDTO.builder()
                   .groupId(lacquerGroupInternal.getGroupId())
                   .allowDuplicates(lacquerGroupInternal.isAllowDuplicates())
                   .groupName(lacquerGroupInternal.getGroupName())
                   .sortBy(lacquerGroupInternal.getSortBy())
                   .sortOrder(lacquerGroupInternal.getSortOrder())
                   .lacquerGroupElements(lacquerGroupInternal.getLacquerGroupElementInternals().stream()
                                                .map(LacquerGroupMapper::mapTo)
                                                .collect(Collectors.toList()))
                   .build();
    }

    private static LacquerGroupElementDTO mapTo(LacquerGroupElementInternal lacquerGroupElementInternal) {
        return LacquerGroupElementDTO.builder()
                   .position(lacquerGroupElementInternal.getPosition())
                   .lacquerName(lacquerGroupElementInternal.getLacquerName())
                   .lacquerCode(lacquerGroupElementInternal.getLacquerCode())
                   .lacquerId(lacquerGroupElementInternal.getLacquerId())
                   .lacquerBrand(lacquerGroupElementInternal.getLacquerBrand())
                   .lacquerPopularity(lacquerGroupElementInternal.getLacquerPopularity())
                   .build();
    }
}
