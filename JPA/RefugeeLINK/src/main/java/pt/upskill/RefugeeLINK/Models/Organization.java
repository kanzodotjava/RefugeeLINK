package pt.upskill.RefugeeLINK.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

import java.util.List;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "organization")
    @Nullable
    private List<Formation> formations;

}
