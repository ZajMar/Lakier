package pl.lacquer.lacquerapp.mapper;

import pl.lacquer.lacquerapp.dto.LacquerDTO;
import pl.lacquer.lacquerapp.repository.model.Lacquer;

/**
 * Created by Marcin Zając on 2020-08-04
 */
public class LacquerMapper {

    private LacquerMapper(){}

    public static Lacquer toLacquer(LacquerDTO lacquerDTO) {
        Lacquer lacquer = new Lacquer();
        lacquer.setLacquerId(lacquerDTO.getLacquerId());
        lacquer.setLacquerName(lacquerDTO.getLacquerName());
        return lacquer;
    }

    public static LacquerDTO toLacquerDTO(Lacquer lacquer) {
        return LacquerDTO.builder()
                   .lacquerId(lacquer.getLacquerId())
                   .lacquerName(lacquer.getLacquerName())
                   .build();
    }
}
