package io.jzheaux.springsecurity.resolutions;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="authorities")
public class UserAuthority {
    @Id
    UUID id;

    @Column
    String authority;

    @JoinColumn(name="username", referencedColumnName="username")
    @ManyToOne
    User user;

    UserAuthority() {}

    public UserAuthority(User user, String authority) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
