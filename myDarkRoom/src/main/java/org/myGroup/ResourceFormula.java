package org.myGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 资源公式类型
 * @author baihui.lbh
 * @version $Id: ResourceFormula.java, v 0.1 2014年8月16日 下午2:44:22 baihui.lbh Exp $
 */
public class ResourceFormula {
    private final List<ResourceItem> itemList = new ArrayList<ResourceItem>();

    //private ResourceItem             outputItem;

    /**
     * 构造一条公式
     * 输入的公式形如：熏肉×1,木材×1,生肉×1  表示 1单位熏肉由1单位木材和1单位生肉合成
     * 改成读入String 数组
     * 强制每个公式只产生一种资源，这样itemList里最后一个item就是产出的资源
     * @param str
     */
    public void setResourceFormula(String[] str) {
        List<String> rawList = Arrays.asList(str);
        for (String item : rawList) {
            if (!item.isEmpty()) {
                //再按×隔开
                List<String> midList = Arrays.asList(item.split("\\*", -1));

                ResourceItem resItem = new ResourceItem();
                resItem.setResourceName(midList.get(0));
                resItem.setResourceNum(Integer.valueOf(midList.get(1)).intValue());
                itemList.add(resItem);
            }

        }

    }

    public String getIndexName() {
        return itemList.get(0).getResourceName();
    }

    public List<ResourceItem> getResourceFormula() {
        return itemList;
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
