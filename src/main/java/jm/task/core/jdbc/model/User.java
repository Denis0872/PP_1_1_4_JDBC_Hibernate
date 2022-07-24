package jm.task.core.jdbc.model;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
//import org.hibernate.annotations.Entity;
import javax.persistence.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
    @NonNull
    private Byte age;

//    public User() {
//    }
//    @Override
//    public String toString() {
//        return
//                "User{" +
//                        "id=" + id +
//                        ", name='" + name + '\'' +
//                        ", lastName='" + lastName + '\'' +
//                        ", age=" + age +
//                        '}';
//    }

//    public User(String name, String lastName, Byte age) {
//        this.name = name;
//        this.lastName = lastName;
//        this.age = age;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Byte getAge() {
//        return age;
//    }
//
//    public void setAge(Byte age) {
//        this.age = age;
//    }


}