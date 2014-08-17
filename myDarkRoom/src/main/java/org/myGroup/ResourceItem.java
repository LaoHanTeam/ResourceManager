package org.myGroup;

/**
 * 用于作为资源公式的节点，也可以做实际资源存储的单元
 * @author baihui.lbh
 * @version $Id: ResourceItem.java, v 0.1 2014年8月16日 下午2:45:35 baihui.lbh Exp $
 */
public class ResourceItem {
    private String  ResourceName;
    private int     ResourceNum;
    private boolean available;

    public ResourceItem() {

    }

    public ResourceItem(String name, int num) {
        ResourceName = name;
        ResourceNum = num;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean val) {
        available = val;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public void setResourceName(String name) {
        ResourceName = name;
    }

    public int getResourceNum() {
        return ResourceNum;
    }

    public void setResourceNum(int num) {
        ResourceNum = num;
    }

    @Override
    public String toString() {
        return ResourceName + "×" + ResourceNum;
    }
}
