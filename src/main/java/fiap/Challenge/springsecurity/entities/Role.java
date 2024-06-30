package fiap.Challenge.springsecurity.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "UsersRoles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;
    private String name;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values{
        ADMIN(1),
        DEFAULT(2);

        Integer id;

        Values(Integer id){
            this.id = id;
        }

        public Integer getId(){
            return this.id;
        }

    }


}
