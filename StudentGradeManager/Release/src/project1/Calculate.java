package project1;
import java.awt.Color;
//import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

//import jxl.*;
//import java.io.*;
//import jxl.write.*;

/**
 * 完成学生各项成绩权重输入、最终成绩的计算以及表格的展示
 * @author Quan Li
 *
 */

public class Calculate {

	public static void main(String args[])
	{
		MyFrame1 window1=new MyFrame1();
		window1.init();
	}
}

class MyFrame1 extends JFrame{

	JButton button1;
	
	public MyFrame1()
	{
		setTitle("计算学生成绩");
		setSize(400,300);
		setLocation(400,200);
		setVisible(true);         //设置窗口可见
		setResizable(true);       //设置窗口大小可变
		setLayout(new FlowLayout());
	}
	void init(){
		
		button1 = new JButton("计算");
		add(button1);
		button1.addActionListener(new listener1());
		
	}
}
class listener1 implements ActionListener {   

//定义listener类并实现监听程序
	public void actionPerformed(ActionEvent e) {
	 
		 MyFrame2 window2=new MyFrame2(); 
		 window2.init();
	 }
}

class MyFrame2 extends JFrame{
	
	JTextField tip;
	JLabel dailyLabel1;
	JLabel dailyLabel2;
	JLabel dailyLabel3;
	JLabel experimentLabel;
	JTextField dailyText1;
	JTextField dailyText2;
	JTextField dailyText3;
	JTextField experimentText;
	JLabel examinationLabel;
	JTextField examinationText;
	JButton button1;
	//Container con;
	
	public MyFrame2(){
		
		setTitle("输入成绩权重");
		setSize(400,300);
		setLocation(400,200);
		setVisible(true);         //设置窗口可见
		setResizable(false);       //设置窗口大小可变
		//setDefaultCloseOperation(EXIT_ON_CLOSE);  //右上角关闭按钮
		setBackground(Color.blue);
		setLayout(new FlowLayout(0,70,10));
	}
	void init(){
		
		tip = new JTextField(25); 
		tip.setText("请输入学生各项成绩的权重：\n"); 
		tip.setEditable(false);   //设置文本区域不可编辑 
		add(tip);
		
		dailyLabel1 = new JLabel("课堂出勤："); 
		dailyText1 = new JTextField(10); 
		add(dailyLabel1); 
		add(dailyText1);
		
		dailyLabel2 = new JLabel("平时作业："); 
		dailyText2 = new JTextField(10); 
		add(dailyLabel2); 
		add(dailyText2); 
		
		dailyLabel3 = new JLabel("Project：    "); 
		dailyText3 = new JTextField(10); 
		add(dailyLabel3); 
		add(dailyText3);
		
		experimentLabel = new JLabel("实验成绩："); 
		experimentText = new JTextField(10); 
		add(experimentLabel); 
		add(experimentText);
		
		examinationLabel = new JLabel("考试成绩："); 
		examinationText = new JTextField(10); 
		add(examinationLabel); 
		add(examinationText);
		
		button1 = new JButton("确定");
		add(button1);
		button1.addActionListener(new listener2());
		
	}
	class listener2 implements ActionListener {      //定义listener类并实现监听程序

		public void actionPerformed(ActionEvent e) {
		 
			 int percentage[]=new int[5];
			 percentage[0] =Integer.parseInt(dailyText1.getText());
			 percentage[1] =Integer.parseInt(dailyText2.getText());
			 percentage[2] =Integer.parseInt(dailyText3.getText());
			 percentage[3] =Integer.parseInt(experimentText.getText());   
			 percentage[4] =Integer.parseInt(examinationText.getText());  
			 int finalGrade;
			 //计算最终成绩
			 try{
             /*
			 Workbook wb=Workbook.getWorkbook(new File("sgrade.xls"));
			 WritableWorkbook book=Workbook.createWorkbook(new File("sgrade.xls"),wb);
			 WritableSheet sheet=book.getSheet(0);
			 Cell cell;
			 Label label;
			 //jxl.write.Number num;
			 int i,j;
			 for( i=1;i<sheet.getRows();i++)
				{
				    finalGrade = 0;
					for( j=2;j<sheet.getColumns()-1;j++)
					{
					cell=sheet.getCell(j, i);
					finalGrade+=Float.parseFloat(cell.getContents())*percentage[j-2]/100;
					}
					label=new Label(j,i,String.valueOf(finalGrade));
					sheet.addCell(label);
					//num=new jxl.write.Number(j,i,finalGrade);
					//sheet.addCell(num);
				}
			 book.write();
			 book.close();
			 wb.close();
*/
				 StudentManager mydata = new StudentManager();
				 for( int i=0;i<mydata.getStudentNumber();i++)
					{
					    finalGrade = 0;
					    for(int j=4;j<9;j++)
					    {
					    	finalGrade+=Integer.parseInt(mydata.getColumData(i, j))*percentage[j-4];
					    }
					    finalGrade/=100;
					    mydata.getStudentByNumber(i).setFinal_grade(String.valueOf(finalGrade));
					}
		     }
			 catch(Exception e1){
					System.out.println(e1);
				}
			 
			 JTableFrame MyFrame3=new JTableFrame();
			 MyFrame3.init();
		 }
	}
	class JTableFrame extends JFrame {
		
	     JTable table;            //定义表格 
	     String[] columnNames={"学号","姓名","专业","班级","课堂出勤","平时作业","实验成绩","Project","考试成绩","总成绩"};
	     String[][] data;
	     JScrollPane scrollPane;  //定义滚动条面板(用以使表格可以滚动)
	     JPanel panel;
	     public JTableFrame()
	     {
	    	setTitle("学生成绩表格最终版");
	 		setSize(1000,500);
	 		setLocation(400,200);
	 		setVisible(true);         //设置窗口可见
	 		setResizable(true);       //设置窗口大小可变
	 		setLayout(new FlowLayout());
	     }
	     void init()
	     {
	    	 try{
	    		 /*
	    		 Workbook rbook=Workbook.getWorkbook(new File("sgrade.xls"));
	    		 Sheet rsheet=rbook.getSheet(0);
	    		 columnNames=new String[rsheet.getColumns()];
	    		 data=new String[rsheet.getRows()-1][rsheet.getColumns()];
	    		 Cell rcell;
	    		 int i ,j;
	    		 for( j=0;j<rsheet.getColumns();j++)
	    		 {
	    			 rcell=rsheet.getCell(j, 0);
	    			 columnNames[j]=rcell.getContents();
	    		 }
	    		 for( i=1;i<rsheet.getRows();i++)
	    		 {
	    			 for( j=0;j<rsheet.getColumns();j++)
	    			 {
	    				 rcell=rsheet.getCell(j, i);
	    				 data[i-1][j]=rcell.getContents();
	    			 }
	    		 }
	    		 rbook.close();
	             */
	    		 StudentManager mydata = new StudentManager();
	    		 //mydata.init();
	    		 data=new String[mydata.getStudentNumber()][10];
	    		 for( int i=0;i<mydata.getStudentNumber();i++)
	    		 {
	    			 for(int j=0;j<10;j++)
	    			 {
	    				 data[i][j]=mydata.getColumData(i, j);
	    			 }
	    		 }
	 			}
			 	catch(Exception e1){
					System.out.println(e1);
			 	}
	    	  table=new JTable(data,columnNames);
	    	  scrollPane=new JScrollPane();
	    	  scrollPane.setBounds(0, 0, 1000, 500);
	    	  
	    	  table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    	  for(int i=4;i<10;i++)
	    	  {
	    		  TableColumn column = table.getColumnModel().getColumn(i);
	    		  column.setPreferredWidth(55);
	    		  column.setMaxWidth(60);
	    		  column.setMinWidth(55);
	    	  }
	    	  panel=new JPanel();
	    	  panel.add(table);
	    	  table.setFillsViewportHeight(true);  //表格大到足以填充封闭视图的全部高度
	    	  scrollPane.setViewportView(table);
	    	  add(scrollPane);
	     }  
	}
}

