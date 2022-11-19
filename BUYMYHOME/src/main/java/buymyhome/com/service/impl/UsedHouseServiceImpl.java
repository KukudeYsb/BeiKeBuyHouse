package buymyhome.com.service.impl;

import buymyhome.com.common.Code;
import buymyhome.com.vojo.Result;
import buymyhome.com.dao.UsedHouseDao;
import buymyhome.com.pojo.UsedHouse;
import buymyhome.com.pojo.query.UsedHouseQuery;
import buymyhome.com.service.UsedHouseService;
import buymyhome.com.util.SplitString;
import buymyhome.com.vojo.TransferUsedHouse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨世博
 */
@Service
public class UsedHouseServiceImpl extends ServiceImpl<UsedHouseDao, UsedHouse> implements UsedHouseService {
    @Autowired
    private UsedHouseDao usedHouseDao;

    @Override
    public Result getUsedHouse(Integer current, Integer sort, TransferUsedHouse transferHouse) {
        QueryWrapper<UsedHouse> qw = new QueryWrapper<>();

        //判断排序
        if (sort==2){
            qw.orderByAsc("listing");
        }else if (sort==3){
            qw.orderByAsc("");
        }else if (sort==4){

        }else if (sort==5){

        }else if (sort==6){

        }else if (sort==7){

        }else if (sort==8){

        }


        //传输对象为空，判断为默认查询
        if (transferHouse.getId()==null){

            IPage<UsedHouse> houseIPage = new Page(current,10);
            usedHouseDao.selectPage(houseIPage,qw);

            return new Result(houseIPage, Code.INQUIRE_OK);
        }else {     //传输对象不为空，按照分类进行查询
            UsedHouseQuery houseQuery = new UsedHouseQuery();

            //判断用户是否进行了建筑面积的选择
            if(transferHouse.getTransCoveredArea()!=null){
                houseQuery.setCoveredArea(SplitString.splitString1(transferHouse.getTransCoveredArea()));
                houseQuery.setCoveredArea2(SplitString.splitString2(transferHouse.getTransCoveredArea()));
            }
            //判断用户是否进行了价格的选择
            if(transferHouse.getTransPrice()!=null){
                houseQuery.setPrice(SplitString.splitString1(transferHouse.getTransPrice()));
                houseQuery.setPrice2(SplitString.splitString2(transferHouse.getTransPrice()));
            }

            qw.eq(null!=transferHouse.getHouseType(),"house_type",transferHouse.getHouseType())
                    .eq(null!=transferHouse.getOrientation(),"orientation",transferHouse.getOrientation())
                    .eq(null!=transferHouse.getLevel(),"level",transferHouse.getLevel())
                    .eq(null!=transferHouse.getFitment(),"fitment",transferHouse.getFitment())
                    .eq(null!=transferHouse.getPurpose(),"purpose",transferHouse.getPurpose())
                    .eq(null!=transferHouse.getElevator(),"elevator",transferHouse.getElevator())
                    .eq(null!=transferHouse.getOwnership(),"ownership",transferHouse.getOwnership())
                    .eq(null!=transferHouse.getBuildingTypes(),"building_types",transferHouse.getBuildingTypes())
                    .lt("price",houseQuery.getPrice2()).gt("price",houseQuery.getPrice())
                    .lt("covered_area",houseQuery.getCoveredArea2()).gt("covered_area",houseQuery.getCoveredArea());
            IPage<UsedHouse> houseIPage = new Page(current,10);
            usedHouseDao.selectPage(houseIPage,qw);

            return new Result(houseIPage, Code.INQUIRE_OK);
        }
    }
}
