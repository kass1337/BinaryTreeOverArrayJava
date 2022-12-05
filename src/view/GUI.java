package view;

import model.factory.FactoryType;
import model.structure.BinaryTreeArray;
import model.usertype.prototype.ProtoType;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener {
    public FactoryType factoryType;
    public BinaryTreeArray btsArray;

    public ProtoType protoType;

    private JButton addElem;
    private JButton deleteElem;
    private JButton saveBinary;
    private JButton loadBinary;
    private JButton findByIndex;
    private JButton balance;
    private JTextArea mainText;
    private JTextField deleteTextField;
    private JTextField findTextField;
    private JComboBox factoryList;

    private String selectedType = "Integer";

    public GUI() {

        factoryType = new FactoryType();
        protoType = factoryType.getBuilderByName("Integer");
        btsArray = new BinaryTreeArray(protoType.getTypeComparator());

        //construct preComponent
        ArrayList<String> typeNameList = factoryType.getTypeNameList();
        String[] FactoryListItems = new String[typeNameList.size()];

        for (int i = 0; i < typeNameList.size(); i++) {
            FactoryListItems[i] = typeNameList.get(i);
        }

        //construct components
        addElem = new JButton ("Добавить элемент");
        deleteElem = new JButton ("Удалить элемент по индексу");
        saveBinary = new JButton ("Сохранить в двоичный");
        loadBinary = new JButton ("Загрузить из двоичного");
        findByIndex = new JButton ("Поиск по индексу");
        balance = new JButton ("Балансировка");
        mainText = new JTextArea (5, 5);
        deleteTextField = new JTextField (5);
        findTextField = new JTextField (5);
        factoryList = new JComboBox (FactoryListItems);

        //adjust size and set layout
        setPreferredSize (new Dimension (908, 577));
        setLayout (null);

        //add components
        add (addElem);
        add (deleteElem);
        add (saveBinary);
        add (loadBinary);
        add (findByIndex);
        add (balance);
        add (mainText);
        add (deleteTextField);
        add (findTextField);
        add (factoryList);

        //set component bounds (only needed by Absolute Positioning)
        addElem.setBounds (720, 55, 150, 45);
        deleteElem.setBounds (690, 110, 205, 25);
        deleteTextField.setBounds (745, 145, 100, 25);
        findByIndex.setBounds (710, 180, 170, 25);
        findTextField.setBounds (745, 215, 100, 25);
        balance.setBounds (705, 250, 180, 30);
        saveBinary.setBounds (690, 340, 205, 35);
        loadBinary.setBounds (690, 395, 205, 35);
        mainText.setBounds (25, 55, 645, 485);
        factoryList.setBounds (25, 15, 100, 25);

        addElem.addActionListener(this);
        deleteElem.addActionListener(this);
        saveBinary.addActionListener(this);
        loadBinary.addActionListener(this);
        findByIndex.addActionListener(this);
        factoryList.addActionListener(this);
        balance.addActionListener(this);

        mainText.setEnabled(true);
        mainText.setFont(new Font("Arial", Font.BOLD, 14));
    }

    public void showGui(){
        JFrame frame = new JFrame ("KiTPO. LR #1");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GUI());
        frame.pack();
        frame.setVisible (true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == factoryList) {
            JComboBox box = (JComboBox)e.getSource();
            String item = (String)box.getSelectedItem();
            if (selectedType != item)
            {
                selectedType = item;
                protoType = factoryType.getBuilderByName(selectedType);
                btsArray = new BinaryTreeArray(protoType.getTypeComparator());
                rewrite();
            }
        }
        // обработчик добавления элемента
        if (e.getSource() == addElem){
            btsArray.addValue(protoType.create());
            rewrite();
        }

        // обработчик удаления элемента
        if (e.getSource() == deleteElem){
            if (deleteTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введите значение!");
                return;
            }
            btsArray.removeNodeByIndex(Integer.parseInt(deleteTextField.getText()));
            rewrite();
        }

        // обработчик поиска элемента по индексу
        if (e.getSource() == findByIndex){
            if (findTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введите значение!");
                return;
            }
            String findValue = (btsArray.getDataAtIndex(Integer.parseInt(findTextField.getText()))).toString();
            JOptionPane.showMessageDialog(null, "Значение = " + findValue
                    + "\nпо индексу = " + findTextField.getText());
        }

        // обработчик балансировки
        if (e.getSource() == balance){
            btsArray = btsArray.balance();
            rewrite();
        }

        // обработчик сохранения
        if (e.getSource() == saveBinary){
            btsArray.save(protoType, "saved");
        }

        // обработчик загрузки
        if (e.getSource() == loadBinary){
            btsArray = btsArray.load(protoType, "saved");
            rewrite();
        }
    }

    private void rewrite() {
        mainText.setText(btsArray.toString());
    }
}