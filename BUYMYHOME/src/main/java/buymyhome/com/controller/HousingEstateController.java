package buymyhome.com.controller;

import buymyhome.com.vojo.Result;
import buymyhome.com.dao.HousingEstateDao;
import buymyhome.com.pojo.HousingEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨世博
 */
@RestController
@RequestMapping("/housingEstate")
public class HousingEstateController{

    @Autowired
    private HousingEstateDao housingEstateDao;

    /**
     * 获取小区
     * @param area 小区所在位置
     * @param sort 是否排序     0-默认排序  1-均价排序
     * @param housingEstate
     * @return
     */
    @PostMapping("/{area}/{sort}")
    public Result getHousingEstate(@PathVariable String area, @PathVariable Integer sort, @RequestBody HousingEstate housingEstate){


        return null;
    }
}
