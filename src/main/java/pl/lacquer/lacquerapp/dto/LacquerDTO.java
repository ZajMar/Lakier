package pl.lacquer.lacquerapp.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@Data
@Builder
public class LacquerDTO {

    private String lacquerId;
    private String lacquerName;
}
