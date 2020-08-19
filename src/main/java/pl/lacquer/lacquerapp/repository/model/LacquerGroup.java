package pl.lacquer.lacquerapp.repository.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Getter
@Setter
@Entity
@Table(name = "LACQUER_GROUP")
@AllArgsConstructor
@NoArgsConstructor

public class LacquerGroup implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private UUID groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(mappedBy = "lacquerGroup", cascade = { CascadeType.ALL})
    private List<LacquerGroupElement> relations;

    @Column(name = "ALLOW_DUPLICATES")
    private boolean allowDuplicates;

    @Column(name = "SORT_BY")
    private String sortBy;

    @Column(name = "SORT_ORDER")
    private String sortOrder;
}
