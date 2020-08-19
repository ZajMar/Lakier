package pl.lacquer.lacquerapp.mapper;

import lombok.experimental.UtilityClass;
import pl.lacquer.lacquerapp.dto.LacquerDTO;
import pl.lacquer.lacquerapp.dto.LacquerGroupElementDTO;
import pl.lacquer.lacquerapp.model.LacquerInternal;
import pl.lacquer.lacquerapp.repository.model.Lacquer;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@UtilityClass
public class LacquerMapper {

    public static Lacquer toLacquer(LacquerDTO lacquerDTO) {
        Lacquer lacquer = new Lacquer();
        lacquer.setLacquerCode(lacquerDTO.getLacquerCode());
        lacquer.setLacquerName(lacquerDTO.getLacquerName());
        lacquer.setLacquerBrand(lacquerDTO.getLacquerBrand());
        lacquer.setLacquerPopularity(lacquerDTO.getLacquerPopularity());
        return lacquer;
    }

    public static LacquerDTO toLacquerDTO(Lacquer lacquer) {
        return LacquerDTO.builder()
                   .lacquerCode(lacquer.getLacquerCode())
                   .lacquerName(lacquer.getLacquerName())
                   .lacquerId(lacquer.getInternalId())
                   .lacquerBrand(lacquer.getLacquerBrand())
                   .lacquerPopularity(lacquer.getLacquerPopularity())
                   .build();
    }

    public static LacquerDTO toLacquerDTO(LacquerInternal lacquerInternal) {
        return LacquerDTO.builder()
                   .lacquerCode(lacquerInternal.getLacquerCode())
                   .lacquerName(lacquerInternal.getLacquerName())
                   .lacquerPopularity(lacquerInternal.getLacquerPopularity())
                   .lacquerBrand(lacquerInternal.getLacquerBrand())
                   .lacquerId(lacquerInternal.getInternalId())
                   .build();
    }

    public static LacquerGroupElementDTO mapToLacquerGroupElementDTO(LacquerInternal lacquerInternal, int position) {
        return LacquerGroupElementDTO.builder()
                   .lacquerId(lacquerInternal.getInternalId())
                   .lacquerName(lacquerInternal.getLacquerName())
                   .lacquerCode(lacquerInternal.getLacquerCode())
                   .position(position)
                   .lacquerPopularity(lacquerInternal.getLacquerPopularity())
                   .lacquerBrand(lacquerInternal.getLacquerBrand())
                   .build();
    }

    public static LacquerInternal mapToLacquerInternal(Lacquer lacquer) {
        return LacquerInternal.builder()
                   .internalId(lacquer.getInternalId())
                   .lacquerCode(lacquer.getLacquerCode())
                   .lacquerName(lacquer.getLacquerName())
                   .lacquerBrand(lacquer.getLacquerBrand())
                   .lacquerPopularity(lacquer.getLacquerPopularity())
                   .build();
    }
}
