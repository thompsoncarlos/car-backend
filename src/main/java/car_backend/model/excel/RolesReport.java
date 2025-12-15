package car_backend.model.excel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RolesReport extends BaseFields {

    private String roleId;
    private String roleName;
    private String department;
    private String criticality;
}
