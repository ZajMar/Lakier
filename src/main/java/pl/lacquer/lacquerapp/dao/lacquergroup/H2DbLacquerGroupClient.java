package pl.lacquer.lacquerapp.dao.lacquergroup;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.lacquer.lacquerapp.dto.LacquerGroupElementDTO;
import pl.lacquer.lacquerapp.model.LacquerGroupInternal;
import pl.lacquer.lacquerapp.model.LacquerInternal;
import pl.lacquer.lacquerapp.model.comparator.LacquerInternalComparatorFactory;
import pl.lacquer.lacquerapp.repository.access.LacquerGroupRepositoryClient;
import pl.lacquer.lacquerapp.repository.access.LacquerRepositoryClient;

import static pl.lacquer.lacquerapp.mapper.LacquerMapper.mapToLacquerGroupElementDTO;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Component(value = H2DbLacquerGroupClient.COMPONENT_NAME)
public class H2DbLacquerGroupClient implements LacquerGroupClient {

    public static final String COMPONENT_NAME = "H2DbLacquerGroupClient";

    private LacquerGroupRepositoryClient lacquerGroupRepositoryClient;
    private LacquerRepositoryClient lacquerRepositoryClient;
    private LacquerInternalComparatorFactory lacquerInternalComparatorFactory;

    @Autowired
    public H2DbLacquerGroupClient(LacquerGroupRepositoryClient lacquerGroupRepositoryClient, LacquerRepositoryClient lacquerRepositoryClient,
                                  LacquerInternalComparatorFactory lacquerInternalComparatorFactory) {
        this.lacquerGroupRepositoryClient = lacquerGroupRepositoryClient;
        this.lacquerRepositoryClient = lacquerRepositoryClient;
        this.lacquerInternalComparatorFactory = lacquerInternalComparatorFactory;
    }

    @Override
    public List<LacquerGroupElementDTO> sortProvidedLacquers(List<UUID> lacquersId, String sortBy, String sortOrder, boolean allowDuplicates) {
        List<LacquerInternal> sortedLacquers = getSortedLacquers(lacquersId, sortBy, sortOrder, allowDuplicates);
        return IntStream.range(0, sortedLacquers.size())
                   .mapToObj(index -> mapToLacquerGroupElementDTO(sortedLacquers.get(index), index))
                   .collect(Collectors.toList());
    }

    @Override
    public UUID createGroup(LacquerGroupInternal lacquerGroupInternal) {
        return lacquerGroupRepositoryClient.createGroup(lacquerGroupInternal);
    }

    @Override
    public LacquerGroupInternal getGroup(UUID lacquerGroupId) {
        return lacquerGroupRepositoryClient.getGroup(lacquerGroupId);
    }

    @Override
    public List<LacquerGroupInternal> getGroups() {
        return lacquerGroupRepositoryClient.getGroups();
    }

    private List<LacquerInternal> getSortedLacquers(List<UUID> lacquersId, String sortBy, String sortOrder, boolean allowDuplicates) {
        Comparator<LacquerInternal> comparator = lacquerInternalComparatorFactory.getComparator(sortBy, sortOrder);
        if (!allowDuplicates) {
            lacquersId = distinctIds(lacquersId);
        }
        return lacquerRepositoryClient.findAllByIdsAndSort(lacquersId).stream()
                   .sorted(comparator)
                   .collect(Collectors.toList());
    }

    private List<UUID> distinctIds(List<UUID> lacquersId) {
        return lacquersId.stream()
                   .distinct()
                   .collect(Collectors.toList());
    }
}
