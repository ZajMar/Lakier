package pl.lacquer.lacquerapp.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lacquer.lacquerapp.dao.lacquergroup.LacquerGroupClient;
import pl.lacquer.lacquerapp.dto.LacquerGroupDTO;
import pl.lacquer.lacquerapp.dto.LacquerGroupElementDTO;
import pl.lacquer.lacquerapp.dto.SortLacquersRequestDTO;
import pl.lacquer.lacquerapp.mapper.LacquerGroupMapper;
import pl.lacquer.lacquerapp.model.LacquerGroupInternal;

import static pl.lacquer.lacquerapp.mapper.LacquerGroupMapper.mapTo;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@RestController
@RequestMapping(LacquerGroupResource.LACQUER_GROUP_PATH)
@CrossOrigin(maxAge = 3600)
public class LacquerGroupResource {

    public static final String LACQUER_GROUP_PATH = "lacquer-group";
    public static final String SORT_LACQUERS_PATH = "sort-lacquers-in-group";
    public static final String CREATE_GROUP_PATH = "create-group";
    public static final String GET_GROUP_PATH = "get-group";
    public static final String GET_ALL_GROUPS_PATH = "all-groups";
    public static final String LACQUER_GROUP_QUERY_PARAM = "lacquerGroupId";

    private LacquerGroupClient lacquerGroupClient;

    @Autowired
    public LacquerGroupResource(LacquerGroupClient lacquerGroupClient) {
        this.lacquerGroupClient = lacquerGroupClient;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = SORT_LACQUERS_PATH)
    public List<LacquerGroupElementDTO> getSortedLacquers(@RequestBody SortLacquersRequestDTO sortLacquersRequestDTO) {
        return lacquerGroupClient.sortProvidedLacquers(sortLacquersRequestDTO.getIds(), sortLacquersRequestDTO.getSortBy(),
            sortLacquersRequestDTO.getSortOrder(), sortLacquersRequestDTO.isAllowDuplicates());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = CREATE_GROUP_PATH)
    public UUID createGroup(@RequestBody LacquerGroupDTO lacquerGroupDTO) {
        LacquerGroupInternal lacquerGroupInternal = mapTo(lacquerGroupDTO);
        return lacquerGroupClient.createGroup(lacquerGroupInternal);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = GET_GROUP_PATH)
    public LacquerGroupDTO getGroup(@RequestParam(name = LACQUER_GROUP_QUERY_PARAM) UUID lacquerGroupId) {
        return mapTo(lacquerGroupClient.getGroup(lacquerGroupId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = GET_ALL_GROUPS_PATH)
    public List<LacquerGroupDTO> getAllGroups() {
        return lacquerGroupClient.getGroups().stream()
                   .map(LacquerGroupMapper::mapTo)
                   .collect(Collectors.toList());
    }
}
