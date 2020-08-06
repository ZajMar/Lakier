package pl.lacquer.lacquerapp.dao;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.common.QueryResult;
import pl.lacquer.lacquerapp.dto.LacquerDTO;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@Component(value = MockLacquerClient.COMPONENT_NAME)
public class MockLacquerClient implements LacquerClient {

    public static final String COMPONENT_NAME = "MockLacquerClient";

    @Override
    public QueryResult<LacquerDTO> getLacquers(String lacquerName, String lacquerCode, int page, int pageSize, String sort) {
        QueryResult<LacquerDTO> lacquerQueryResult = new QueryResult<>();
        lacquerQueryResult.setPage(1);
        lacquerQueryResult.setPageSize(2);
        lacquerQueryResult.setResults(Arrays.asList(
            LacquerDTO.builder()
                .lacquerId("jeden")
                .lacquerName("lakier1")
                .build(),
            LacquerDTO.builder()
                .lacquerId("dwa")
                .lacquerName("dwa1")
                .build())
        );
        lacquerQueryResult.setTotalRecords(2);
        return lacquerQueryResult;
    }
}
