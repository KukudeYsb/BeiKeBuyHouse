package buymyhome.com.pojo;

import lombok.*;

import java.sql.Date;

/**
 * @author 杨世博
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class HousingEstate {
    private Integer id;
    private String name;
    private String area;
    private Double price;
    private String propertyFee;
    private String company;
    private String developers;
    private Integer buildingAmount;
    private Integer households;
    private String location;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
