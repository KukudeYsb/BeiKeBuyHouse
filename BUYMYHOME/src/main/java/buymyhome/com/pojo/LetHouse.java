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

public class LetHouse {
    private Integer id;
    private String name;
    private Double area;
    private Integer orientation;
    private Integer lease;
    private Double price;
    private Date maintain;
    private Date checkIn;
    private Integer level;
    private Integer allLevel;
    private Integer elevator;
    private Integer stall;
    private Integer fuelGas;
    private Integer heating;
    private Integer tenancyTerm;
    private Date seeTime;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
