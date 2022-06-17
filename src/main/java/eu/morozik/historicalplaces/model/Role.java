package eu.morozik.historicalplaces.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"role\"")
public class Role extends BaseEntity{
    @Column(name = "name_role")
    private String nameRole;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
}
