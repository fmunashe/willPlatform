package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestData {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Override
    public String toString() {
        return "TestData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", website='" + website + '\'' +
            '}';
    }
}
