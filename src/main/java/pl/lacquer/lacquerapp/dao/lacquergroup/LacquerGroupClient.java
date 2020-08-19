package pl.lacquer.lacquerapp.dao.lacquergroup;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import pl.lacquer.lacquerapp.dto.LacquerGroupElementDTO;
import pl.lacquer.lacquerapp.model.LacquerGroupInternal;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Service
public interface LacquerGroupClient {

    List<LacquerGroupElementDTO> sortProvidedLacquers(List<UUID> lacquersId, String sortBy, String sortDirection, boolean allowDuplicates);

    UUID createGroup(LacquerGroupInternal lacquerGroupInternal);

    LacquerGroupInternal getGroup(UUID lacquerGroupId);

    List<LacquerGroupInternal> getGroups();
}
