package pl.lacquer.lacquerapp.repository.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Marcin Zając on 2020-08-09
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "LACQUER_GROUP_ELEMENT")
@AllArgsConstructor
@NoArgsConstructor
public class LacquerGroupElement implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "INTERNAL_ID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private LacquerGroup lacquerGroup;

    @ManyToOne
    @JoinColumn(name = "LACQUER_ID")
    private Lacquer lacquer;

    @Column(name="POSITION")
    private int position;
}
