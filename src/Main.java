import model.factory.FactoryType;
import model.structure.BinaryTreeArray;
import model.usertype.prototype.ProtoType;
import view.GUI;


public class Main {
    public static void main(String[] args) {

        // Здесь выполняются все операции одним потоком
        FactoryType factoryType = new FactoryType();
        ProtoType protoType;
        BinaryTreeArray btsArray;

        //СД для ТД Integer
        System.out.println("--------------TEST FOR INTEGER-------------");
        protoType = FactoryType.getBuilderByName("Integer");
        btsArray = new BinaryTreeArray(protoType.getTypeComparator());

        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());

        System.out.println("---------PRINT TREE---------");
        btsArray.printTree();

        System.out.println("---------PRINT ARRAY--------");
        btsArray.printArray();

        System.out.println("\n----GET VALUE BY INDEX 2----");
        System.out.println("value = " + btsArray.getDataAtIndex(2).toString());

        System.out.println("---DELETE VALUE BY INDEX 2--");
        btsArray.removeNodeByIndex(2);
        btsArray.printTree();

        System.out.println("-----SAVE IN BINARY FILE----");
        btsArray.save(protoType, "saved");

        System.out.println("-----------BALANCE----------");
        btsArray = btsArray.balance();
        btsArray.printTree();

        System.out.println("---LOAD FROM BINARY FILE----");
        btsArray = btsArray.load(protoType, "saved");
        btsArray.printTree();

        System.out.println("---------FOR EACH-----------");
        btsArray.forEach(System.out::println);

        //СД для ТД DateTime
        System.out.println("----------TEST FOR DATETIME-----------");
        protoType = factoryType.getBuilderByName("DateTime");
        btsArray = new BinaryTreeArray(protoType.getTypeComparator());

        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());
        btsArray.addValue(protoType.create());

        System.out.println("---------PRINT TREE---------");
        btsArray.printTree();

        System.out.println("---------PRINT ARRAY--------");
        btsArray.printArray();

        System.out.println("\n----GET VALUE BY INDEX 2----");
        System.out.println("value = " + btsArray.getDataAtIndex(2).toString());

        System.out.println("---DELETE VALUE BY INDEX 2--");
        btsArray.removeNodeByIndex(2);
        btsArray.printTree();

        System.out.println("-----SAVE IN BINARY FILE----");
        btsArray.save(protoType, "saved");

        System.out.println("-----------BALANCE----------");
        btsArray = btsArray.balance();
        btsArray.printTree();

        System.out.println("---LOAD FROM BINARY FILE----");
        btsArray = btsArray.load(protoType, "saved");
        btsArray.printTree();

        System.out.println("---------FOR EACH-----------");
        btsArray.forEach(System.out::println);

        // GUI
        GUI gui = new GUI();
        gui.showGui();

    }
}
