package pl.lacquer.lacquerapp.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@Data
@Builder
public class LacquerDTO {

    private UUID lacquerId;
    private String lacquerCode;
    private String lacquerName;
    private String lacquerPopularity;
    private String lacquerBrand;
}