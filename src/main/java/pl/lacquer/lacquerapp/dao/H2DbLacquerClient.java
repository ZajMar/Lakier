package pl.lacquer.lacquerapp.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.common.QueryResult;
import pl.lacquer.lacquerapp.dto.LacquerDTO;
import pl.lacquer.lacquerapp.mapper.LacquerMapper;
import pl.lacquer.lacquerapp.repository.access.LacquerRepositoryClient;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@Component(value = H2DbLacquerClient.COMPONENT_NAME)
public class H2DbLacquerClient implements LacquerClient {

    public static final String COMPONENT_NAME = "H2DbLacquerClient";

    private LacquerRepositoryClient lacquerRepositoryClient;

    @Autowired
    public H2DbLacquerClient(LacquerRepositoryClient lacquerRepositoryClient) {
        this.lacquerRepositoryClient = lacquerRepositoryClient;
    }

    @Override
    public QueryResult<LacquerDTO> getLacquers(String lacquerName, String lacquerCode, int page, int pageSize, String sort) {
        Pageable pageOptions = buildPageOptions(page, pageSize, sort);
        List<LacquerDTO> sortedAndPagedLacquers = extractLacquers(lacquerName, lacquerCode, pageOptions);
        return new QueryResult<>(sortedAndPagedLacquers, lacquerRepositoryClient.countAll(lacquerName, lacquerCode), page);
    }

    private Pageable buildPageOptions(int page, int pageSize, String sort) {
        Sort sortOptions = buildSortOptions(sort);
        return PageRequest.of(page, pageSize, sortOptions);
    }

    private Sort buildSortOptions(String sort) {
        if (sort.contains("-")) {
            return Sort.by(Direction.DESC, sort.replace("-", ""));
        }
        return Sort.by(Direction.ASC, sort);
    }

    private List<LacquerDTO> extractLacquers(String lacquerName, String lacquerCode, Pageable pageOptions) {
        return lacquerRepositoryClient.findWithSortingAndPaging(lacquerName, lacquerCode, pageOptions).stream()
                   .map(LacquerMapper::toLacquerDTO)
                   .collect(Collectors.toList());
    }
}
