package seungkyu.slicetest.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupUserRequest {
    private String name;
    private Integer age;
    private String password;
    private String profileImageId;
}