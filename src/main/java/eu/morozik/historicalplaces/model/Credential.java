package eu.morozik.historicalplaces.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credentials")
public class Credential extends BaseEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "credential", cascade = CascadeType.ALL)
    private User user;
}
