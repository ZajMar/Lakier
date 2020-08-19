package pl.lacquer.lacquerapp.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Data
@Builder
public class LacquerGroupElementDTO {

    private UUID lacquerId;
    private String lacquerCode;
    private String lacquerName;
    private int position;
    private String lacquerBrand;
    private String lacquerPopularity;
}
