package pl.lacquer.lacquerapp.dao.lacquer;

import pl.lacquer.lacquerapp.common.QueryResult;
import pl.lacquer.lacquerapp.dto.LacquerDTO;

/**
 * Created by Marcin Zając on 2020-08-04
 */
public interface LacquerClient {

    QueryResult<LacquerDTO> getLacquers(String lacquerName, String lacquerCode, String lacquerBrand, String lacquerPopularity, int page, int pageSize, String sort);
}
