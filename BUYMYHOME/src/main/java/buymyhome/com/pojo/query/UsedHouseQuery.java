package buymyhome.com.pojo.query;

import buymyhome.com.vojo.TransferUsedHouse;
import lombok.Data;

/**
 * @author 杨世博
 */
@Data
public class UsedHouseQuery extends TransferUsedHouse {
    private Double price2;
    private Double coveredArea2;
    private Double insideSpace2;

    public UsedHouseQuery() {
        super.setPrice(0.00);
        super.setCoveredArea(0.00);
        super.setInsideSpace(0.00);
        this.price2 = 99999.99;
        this.coveredArea2 = 99999.99;
        this.insideSpace2 = 99999.99;
    }
}
