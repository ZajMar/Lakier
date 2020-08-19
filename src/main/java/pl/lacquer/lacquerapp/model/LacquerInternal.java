package pl.lacquer.lacquerapp.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Data
@Builder
public class LacquerInternal {

    public static final String LACQUER_CODE = "lacquerCode";
    public static final String LACQUER_NAME = "lacquerName";
    public static final String LACQUER_BRAND = "lacquerBrand";
    public static final String LACQUER_POPULARITY = "lacquerPopularity";

    private UUID internalId;
    private String lacquerCode;
    private String lacquerName;
    private String lacquerBrand;
    private String lacquerPopularity;
}
