package buymyhome.com.service;

import buymyhome.com.vojo.Result;
import buymyhome.com.pojo.UsedHouse;
import buymyhome.com.vojo.TransferUsedHouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 杨世博
 *
 * 主界面    分类展示房源
 */
public interface UsedHouseService extends IService<UsedHouse> {

    /**
     * 查找二手房
     * @param current
     * @param sort
     * @param transferHouse
     * @return
     */
    public Result getUsedHouse(Integer current, Integer sort, TransferUsedHouse transferHouse);
}
