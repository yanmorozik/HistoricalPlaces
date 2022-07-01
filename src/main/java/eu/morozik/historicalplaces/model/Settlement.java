package eu.morozik.historicalplaces.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"settlement\"",schema = "public")
public class Settlement extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL)
    private Set<Address> addresses= new HashSet<>();
}
