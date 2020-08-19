package pl.lacquer.lacquerapp.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Data
public class SortLacquersRequestDTO {

    private List<UUID> ids;
    private String sortBy;
    private String sortOrder;
    private boolean allowDuplicates;
}
