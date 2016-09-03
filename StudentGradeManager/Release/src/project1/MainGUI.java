package project1;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.management.openmbean.OpenDataException;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



class  myJFrame extends JFrame{//swing窗体JFrame

private JButton  but1;//swing按钮JButton  
private JButton but2;
private JButton but3;
private JButton but4;
private JTextArea jt;
private StudentManager dataimport;
private static Student mystudent;

public static Student tranStudent(){
	return mystudent;
}

public myJFrame(){
    super("学生成绩管理系统"); //引用父类构造方法
    setSize(500,600);
    setResizable(false);
    setLocation(700,250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c=getContentPane(); 				
    c.setLayout(null);  			
    
    JPanel jp1=new JPanel();
    jp1.setBounds(0,0,500,100);
    jp1.setVisible(true);
    jp1.setLayout(new FlowLayout(1,0,50));
    c.add(jp1);
    JPanel jp2=new JPanel();
    jp2.setBounds(0,100,500,300);
    jp2.setVisible(true);
    jp2.setLayout(new FlowLayout(1,200,30));
    c.add(jp2);
    JPanel jp3=new JPanel();
    jp3.setBounds(0,450,500,100);
    jp3.setVisible(true);
    jp3.setLayout(new BorderLayout());
    jp3.setBackground(Color.white);
    c.add(jp3);
    
    JLabel jl=new JLabel("欢迎使用学生成绩管理系统！");
    jl.setFont(new Font("楷体",Font.BOLD, 30));
    jp1.add(jl);
    but1=new JButton("导入名单");
    but1.setFont(new Font( "楷体",Font.BOLD, 20));
    //but1.setBackground(Color.MAGENTA);
    jp2.add(but1); 
    but2=new JButton("检索");
    but2.setFont(new Font( "楷体",Font.BOLD, 20));
    //but2.setBackground(Color.pink);
    but2.addActionListener(new SearchDialog());
    jp2.add(but2);
    but3=new JButton("计算");
    but3.setFont(new Font( "楷体",Font.BOLD, 20));
    //but3.setBackground(Color.orange);
    but3.addActionListener(new listener1());
    jp2.add(but3);
    but4=new JButton("导出");
    but4.setFont(new Font( "楷体",Font.BOLD, 20));
    //but4.setBackground(Color.yellow);
    jp2.add(but4);
    
    //导入和导出
    but1.addActionListener(new listener_import());
    but4.addActionListener(new listener_export());
    
    jt=new JTextArea(1,20);
    jt.setFont(new Font( "宋体",Font.BOLD, 16));
    jt.setBackground(Color.white);
    jt.setEditable(false);
    jp3.add(jt,BorderLayout.CENTER);
    
     
   
    this.setVisible(true);   
    }  
public void open(){
	JFileChooser chooser = new JFileChooser();
    chooser.setMultiSelectionEnabled(false);
    String  []s = {"xls"};
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls", s);
    chooser.setFileFilter(filter);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setDialogTitle("选择学生名单");
    
    int result = chooser.showOpenDialog(this);
    if(result == JFileChooser.APPROVE_OPTION){
     File file1 = chooser.getCurrentDirectory();
     String filepath = file1.getPath();
     String filename=chooser.getSelectedFile().getName();
     jt.setText("文件路径:"+filepath+"\\"+filename+"已导入。"); //将文件路径设到JTextField 
     
     //导入数据
     dataimport = new StudentManager(filepath + "\\" + filename);
     boolean import_success = dataimport.init();
     if (import_success){
		jt.setText("数据导入成功！");
		}
     }
    }
//事件监听器
private class listener_import implements ActionListener{//定义listener类并实现监听程序
  public void actionPerformed(ActionEvent e) {
      open();  
      }
  }

private class listener_export implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		//导出数据
		dataimport.export();
		jt.setText("导出成功");
	}	
}

/****搜索监听器****/
class SearchDialog implements ActionListener{  //检索监听
	//private StudentManager dataimport=new StudentManager();
	public void actionPerformed(ActionEvent e) {	
		String str=JOptionPane.showInputDialog(myJFrame.this,"输入","输入对话框",JOptionPane.PLAIN_MESSAGE);
		if(str.length()!=0){
			mystudent=dataimport.getStudent(str);
			//System.out.println(mystudent.getSnumber());
			if(mystudent==null){
				JOptionPane.showConfirmDialog(myJFrame.this, "查无此人！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			else{
				SingleStudentFrame single=new SingleStudentFrame();
				//myJFrame.this.dispose();
			}
		}
		else{
			JOptionPane.showConfirmDialog(myJFrame.this, "请输入正确的学号信息！", "警告", JOptionPane.WARNING_MESSAGE);
		}
	}
}
/****计算按钮监听器****/
class listener1 implements ActionListener {   

	//定义listener类并实现监听程序
		public void actionPerformed(ActionEvent e) {
			 MyFrame2 window2=new MyFrame2(); 
			 window2.init();
		 }
	}
}

//主类
public class MainGUI{
	public static void main(String args[]){
 	myJFrame frm=new myJFrame(); 
 	//SingleStudentFrame single=new SingleStudentFrame();
 
 }
}