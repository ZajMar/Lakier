package pl.lacquer.lacquerapp.repository.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Marcin Zając on 2020-06-04
 */
@Data
@Builder
@Entity
@Table(name = "LACQUER")
@AllArgsConstructor
@NoArgsConstructor
public class Lacquer {

    @Id
    @GeneratedValue
    @Column(name = "INTERNAL_ID")
    private UUID internalId;

    @Column(name = "LACQUER_ID")
    private String lacquerId;

    @Column(name = "LACQUER_NAME")
    private String lacquerName;
}
