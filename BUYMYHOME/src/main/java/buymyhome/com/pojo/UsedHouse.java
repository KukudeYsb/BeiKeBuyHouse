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

public class UsedHouse {
    private Integer id;
    private String  name;
    private Double price;
    private String introduce;
    private Integer housingEstate;
    private Integer buildingTypes;
    private String houseType;
    private Integer level;
    private Double coveredArea;
    private String architecturalStructure;
    private Double insideSpace;
    private Integer orientation;
    private String buildingStructure;
    private Integer fitment;
    private String ratio;
    private Integer elevator;
    private Date listing;
    private Date deal;
    private Integer ownership;
    private Integer purpose;
    private Integer ageLimit;
    private String belongTo;
    private String pledge;
    private String remark;
    private String checkCode;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
