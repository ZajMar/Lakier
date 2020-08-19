package pl.lacquer.lacquerapp.repository.access;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.lacquer.lacquerapp.mapper.LacquerMapper;
import pl.lacquer.lacquerapp.model.LacquerInternal;

/**
 * Created by Marcin Zając on 2020-08-05
 */
@Service
public class LacquerRepositoryClient {

    @Autowired
    public LacquerRepositoryClient(LacquerRepository lacquerRepository) {
        this.lacquerRepository = lacquerRepository;
    }

    private LacquerRepository lacquerRepository;

    public List<LacquerInternal> findWithSortingAndPaging(String lacquerName, String lacquerCode, String lacquerBrand, String lacquerPopularity, Pageable pageable) {
        return lacquerRepository.findAllByLacquerNameIsContainingIgnoreCaseAndLacquerCodeIsContainingIgnoreCaseAndLacquerBrandIsContainingIgnoreCaseAndLacquerPopularityIsContainingIgnoreCase(lacquerName, lacquerCode, lacquerBrand, lacquerPopularity, pageable).stream()
                   .map(LacquerMapper::mapToLacquerInternal)
                   .collect(Collectors.toList());
    }

    public long countAll(String lacquerName, String lacquerCode, String lacquerBrand, String lacquerPopularity) {
        return lacquerRepository.countAllByLacquerNameIsContainingIgnoreCaseAndLacquerCodeIsContainingIgnoreCaseAndLacquerBrandIsContainingIgnoreCaseAndLacquerPopularityIsContainingIgnoreCase(lacquerName, lacquerCode, lacquerBrand, lacquerPopularity);
    }

    public List<LacquerInternal> findAllByIdsAndSort(List<UUID> ids) {
        return ids.stream()
                   .map(id -> lacquerRepository.findById(id))
                   .filter(Optional::isPresent)
                   .map(Optional::get)
                   .map(LacquerMapper::mapToLacquerInternal)
                   .collect(Collectors.toList());
    }
}
