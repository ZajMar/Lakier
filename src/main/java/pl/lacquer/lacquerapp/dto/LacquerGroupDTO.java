package pl.lacquer.lacquerapp.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Marcin Zając on 2020-08-10
 */
@Data
@Builder
public class LacquerGroupDTO {

    private List<LacquerGroupElementDTO> lacquerGroupElements;
    private String groupName;
    private String sortBy;
    private String sortOrder;
    private boolean allowDuplicates;
    private UUID groupId;
}
