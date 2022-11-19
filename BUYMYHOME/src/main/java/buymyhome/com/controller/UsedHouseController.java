package buymyhome.com.controller;

import buymyhome.com.vojo.Result;
import buymyhome.com.service.UsedHouseService;
import buymyhome.com.vojo.TransferUsedHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨世博
 *
 * 房源
 */
@RestController
@RequestMapping("/house")
public class UsedHouseController {

    @Autowired
    private UsedHouseService usedHouseService;

    /**
     * 通过 type 获取不同房型       分页查询
     * @param current 当前页码值
     * @param sort 排序 1-默认排序 2-最新发布 3-总价升序 4-总价降序 5-房屋单价升序 6-房屋单价降序 7-面积升序 8-面积降序
     * @param transferHouse 分类查找
     * @return
     */
    @PostMapping("/{current}/{sort}")
    public Result getHouseMessage(@PathVariable Integer current, @PathVariable Integer sort, @RequestBody TransferUsedHouse transferHouse){
        return usedHouseService.getUsedHouse(current,sort,transferHouse);
    }
}
