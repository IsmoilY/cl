package uz.jfy.cl.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Author implements Serializable {

    private String firstName;
    private String lastName;

}
