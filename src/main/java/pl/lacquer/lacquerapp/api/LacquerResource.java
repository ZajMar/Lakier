package pl.lacquer.lacquerapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lacquer.lacquerapp.common.QueryResult;
import pl.lacquer.lacquerapp.dao.lacquer.H2DbLacquerClient;
import pl.lacquer.lacquerapp.dao.lacquer.LacquerClient;
import pl.lacquer.lacquerapp.dto.LacquerDTO;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@RestController
@RequestMapping(LacquerResource.LACQUER_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class LacquerResource {

    public static final String LACQUER_PATH = "lacquer";
    public static final String NAME_QUERY_PARAM = "name";
    public static final String CODE_QUERY_PARAM = "code";
    public static final String POPULARITY_QUERY_PARAM = "popularity";
    public static final String BRAND_QUERY_PARAM = "brand";
    public static final String PAGE_QUERY_PARAM = "page";
    public static final String PAGE_SIZE_QUERY_PARAM = "pageSize";
    public static final String SORT_QUERY_PARAM = "sort";
    public static final String DEFAULT_SORT = "lacquerName";

    private final LacquerClient lacquerClient;

    @Autowired
    public LacquerResource(@Qualifier(H2DbLacquerClient.COMPONENT_NAME) LacquerClient lacquerClient) {
        this.lacquerClient = lacquerClient;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public QueryResult<LacquerDTO> getLocalityPage(@RequestParam(name = NAME_QUERY_PARAM) String lacquerName,
                                                   @RequestParam(name = CODE_QUERY_PARAM) String lacquerCode,
                                                   @RequestParam(name = POPULARITY_QUERY_PARAM) String lacquerPopularity,
                                                   @RequestParam(name = BRAND_QUERY_PARAM) String lacquerBrand,
                                                   @RequestParam(name = PAGE_QUERY_PARAM) int page,
                                                   @RequestParam(name = PAGE_SIZE_QUERY_PARAM) int pageSize,
                                                   @RequestParam(name = SORT_QUERY_PARAM, defaultValue = DEFAULT_SORT) String sort) {
        return lacquerClient.getLacquers(lacquerName, lacquerCode, lacquerBrand, lacquerPopularity, page, pageSize, sort);
    }
}
