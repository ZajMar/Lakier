package pl.lacquer.lacquerapp.repository.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Marcin Zając on 2020-06-04
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "LACQUER")
@AllArgsConstructor
@NoArgsConstructor
public class Lacquer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "INTERNAL_ID")
    private UUID internalId;

    @Column(name = "LACQUER_CODE")
    private String lacquerCode;

    @Column(name = "LACQUER_NAME")
    private String lacquerName;

    @Column(name = "LACQUER_POPULARITY")
    private String lacquerPopularity;

    @Column(name = "LACQUER_BRAND")
    private String lacquerBrand;
}