package pl.lacquer.lacquerapp.repository.access;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.lacquer.lacquerapp.repository.model.LacquerGroup;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Repository
interface LacquerGroupRepository extends PagingAndSortingRepository<LacquerGroup, UUID> {


}
