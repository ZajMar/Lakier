package pl.lacquer.lacquerapp.repository.access;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lacquer.lacquerapp.mapper.LacquerGroupMapper;
import pl.lacquer.lacquerapp.model.LacquerGroupElementInternal;
import pl.lacquer.lacquerapp.model.LacquerGroupInternal;
import pl.lacquer.lacquerapp.repository.model.LacquerGroup;
import pl.lacquer.lacquerapp.repository.model.LacquerGroupElement;

import static pl.lacquer.lacquerapp.mapper.LacquerGroupMapper.mapTo;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Service
public class LacquerGroupRepositoryClient {

    private LacquerGroupRepository lacquerGroupRepository;

    private LacquerRepository lacquerRepository;

    @Autowired
    public LacquerGroupRepositoryClient(LacquerGroupRepository lacquerGroupRepository,
                                        LacquerRepository lacquerRepository) {
        this.lacquerGroupRepository = lacquerGroupRepository;
        this.lacquerRepository = lacquerRepository;
    }

    public UUID createGroup(LacquerGroupInternal lacquerGroupInternal) {
        LacquerGroup lacquerGroup = new LacquerGroup();
        lacquerGroup.setGroupId(UUID.randomUUID());
        lacquerGroup.setAllowDuplicates(lacquerGroupInternal.isAllowDuplicates());
        lacquerGroup.setGroupName(lacquerGroupInternal.getGroupName());
        lacquerGroup.setSortBy(lacquerGroupInternal.getSortBy());
        lacquerGroup.setSortOrder(lacquerGroupInternal.getSortOrder());
        List<LacquerGroupElement> lacquerRelations = createLacquerRelation(lacquerGroupInternal.getLacquerGroupElementInternals(), lacquerGroup);
        lacquerGroup.setRelations(lacquerRelations);
        LacquerGroup savedObject = lacquerGroupRepository.save(lacquerGroup);
        return savedObject.getGroupId();
    }

    private List<LacquerGroupElement> createLacquerRelation(List<LacquerGroupElementInternal> groupElements, LacquerGroup lacquerGroup) {
        return groupElements.stream()
                   .map(groupElement -> LacquerGroupElement.builder()
                                            .id(UUID.randomUUID())
                                            .lacquer(lacquerRepository.findById(groupElement.getLacquerId()).orElse(null))
                                            .lacquerGroup(lacquerGroup)
                                            .position(groupElement.getPosition())
                                            .build())
                   .collect(Collectors.toList());
    }

    public LacquerGroupInternal getGroup(UUID lacquerGroupId) {
        return lacquerGroupRepository.findById(lacquerGroupId)
                   .map(LacquerGroupMapper::mapTo)
                   .orElse(null);
    }

    public List<LacquerGroupInternal> getGroups() {
        return StreamSupport.stream(lacquerGroupRepository.findAll().spliterator(), false)
                   .map(LacquerGroupMapper::mapTo)
                   .collect(Collectors.toList());
    }
}
