package buymyhome.com.vojo;

import buymyhome.com.pojo.UsedHouse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author 杨世博
 *
 * 用于传输的房源实体类
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class TransferUsedHouse extends UsedHouse {
    @JsonProperty(value = "transCoveredArea")
    private String transCoveredArea;

    @JsonProperty(value = "transPrice")
    private String transPrice;

    @JsonProperty(value = "transInsideSpace")
    private String transInsideSpace;
}
