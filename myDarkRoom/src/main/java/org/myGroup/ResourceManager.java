package org.myGroup;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 资源管理器
 * @author baihui.lbh
 * @version $Id: ResourceManager.java, v 0.1 2014年8月15日 下午2:34:34 baihui.lbh Exp $
 */
public class ResourceManager {
    //private final List<ResourceItem>               resList    = new ArrayList<ResourceItem>();
    private final HashMap<String, ResourceFormula> formulaMap = new HashMap<String, ResourceFormula>();
    private final HashMap<String, Integer>         depository = new HashMap<String, Integer>();

    /**
     * 添加资源公式
     * 直接读csv文件
     * @param formula
     */
    public void RecordFormula(String csvFilePath) {
        if (csvFilePath != null && !csvFilePath.isEmpty()) {
            CsvParser csvParser = new CsvParser();
            File myFormula = csvParser.getCsvFile(csvFilePath);
            List<String[]> tableList = null;
            try {
                tableList = csvParser.readFromCsv(myFormula);
            } catch (Exception e) {
                System.err.println("读取csv文件出错");
            }

            for (int i = 1; i < tableList.size(); ++i) {
                //第一行文件头不读 
                ResourceFormula newFormula = new ResourceFormula();
                newFormula.setResourceFormula(tableList.get(i));
                formulaMap.put(newFormula.getIndexName(), newFormula);
            }
        }
    }

    public void displayFormula() {
        System.out.println(formulaMap);
    }

    public void displayDepository() {
        System.out.println(depository);
    }

    /**
     * 向ResourceManager注册某个资源
     * @param res
     * @return
     */
    public boolean Register(ResourceItem res) {
        boolean flag = false;
        if (res.isAvailable()) {
            if (depository.containsKey(res.getResourceName())) {
                Integer sum = depository.get(res.getResourceName()) + res.getResourceNum();
                depository.put(res.getResourceName(), sum);
            } else {
                depository.put(res.getResourceName(), res.getResourceNum());
            }
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 检查库存
     * @param res
     * @return
     */
    private boolean checkDepository(String res) {
        boolean flag = false;
        if (formulaMap.containsKey(res)) {
            //存在该目标物品的合成公式
            ResourceFormula rf = formulaMap.get(res);
            for (ResourceItem formulaItem : rf.getResourceFormula()) {
                if (!res.equals(formulaItem.getResourceName())) {
                    //只check待投入的资源
                    if (!depository.containsKey(formulaItem.getResourceName())
                        || depository.get(formulaItem.getResourceName()) < formulaItem
                            .getResourceNum()) {
                        //若仓库中不存在公式中需要的资源或资源数量不足时报错
                        System.err.println("资源不足，无法合成！");
                        return false;
                    }
                }
            }
            flag = true;
        }
        return flag;
    }

    /**
     * 同步消耗
     * @return
     */
    public boolean SynConsume() {
        boolean flag = false;
        return flag;
    }

    /**
     * 异步消耗
     * @return
     */
    public boolean AsynConsume(String res) {
        boolean flag = false;
        if (res != null && !res.isEmpty()) {
            if (checkDepository(res)) {
                //ResourceItem output = new ResourceItem(res, 1);//产出1单位物品
                ResourceFormula checkList = formulaMap.get(res);//取出该合成公式
                for (ResourceItem ri : checkList.getResourceFormula()) {
                    if (!res.equals(ri.getResourceName())) {
                        Integer num = depository.get(ri.getResourceName());//取出某资源对应数目
                        if (num == ri.getResourceNum()) {
                            depository.remove(ri.getResourceName());
                        } else
                            depository.put(ri.getResourceName(), num - ri.getResourceNum());//减掉应扣除的资源数目再放回去
                    }
                }
                if (depository.containsKey(res)) {
                    Integer num = depository.get(res);
                    depository.put(res, num + 1);
                } else
                    depository.put(res, 1);
                flag = true;
            }
        }

        return flag;
    }
}
