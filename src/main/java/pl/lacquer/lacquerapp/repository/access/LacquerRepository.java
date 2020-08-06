package pl.lacquer.lacquerapp.repository.access;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.lacquer.lacquerapp.repository.model.Lacquer;

/**
 * Created by Marcin Zając on 2020-08-04
 */
@Repository
interface LacquerRepository extends PagingAndSortingRepository<Lacquer, UUID> {

    List<Lacquer> findAllByLacquerNameIsContainingIgnoringCaseAndLacquerIdIsContainingIgnoringCase(String lacquerName, String lacquerCode, Pageable pageable);

    long countAllByLacquerNameContainingIgnoringCaseAndLacquerIdIsContainingIgnoringCase(String lacquerName, String lacquerCode);

}
