package model.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EmployeeEntity {
    private String user_id;
    private String password;
    private String email;
    private String user_type;

}
