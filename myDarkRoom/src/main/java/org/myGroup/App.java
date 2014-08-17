package org.myGroup;

/**
 * test
 *
 */
public class App {

    public static void main(String[] args) {
        ResourceManager resManager = new ResourceManager();
        System.out.println("=======记录合成公式=======");
        resManager.RecordFormula("formulaSettings/formula.csv");
        resManager.displayFormula();
        System.out.println("=======输入初始资源=======");
        ResourceItem woods = new ResourceItem("木材", 3);
        woods.setAvailable(true);//让资源处于可用状态
        resManager.Register(woods);
        resManager.displayDepository();
        System.out.println("=======生产火炬=======");
        resManager.AsynConsume("木炭");
        System.out.print("库存：");
        resManager.displayDepository();
        resManager.AsynConsume("火炬");
        System.out.print("库存：");
        resManager.displayDepository();

    }
}
