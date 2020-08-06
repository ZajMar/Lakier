package pl.lacquer.lacquerapp.repository.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.lacquer.lacquerapp.repository.model.Lacquer;

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

     public List<Lacquer> findWithSortingAndPaging(String lacquerName, String lacquerCode, Pageable pageable) {
         return lacquerRepository.findAllByLacquerNameIsContainingIgnoringCaseAndLacquerIdIsContainingIgnoringCase(lacquerName, lacquerCode, pageable);
     }

    public long countAll(String lacquerName, String lacquerCode) {
         return lacquerRepository.countAllByLacquerNameContainingIgnoringCaseAndLacquerIdIsContainingIgnoringCase(lacquerName, lacquerCode);
    }
}
