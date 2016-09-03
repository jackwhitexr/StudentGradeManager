package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


/*public class GUI {
	public static void main(String args[]){   //主函数测试
		SearchGUI my=new SearchGUI();
		//SingleStudentFrame single=new SingleStudentFrame();
		//System.out.print(Constant.INNER_FRAME_WIDTH*1/3);
	}
}*/

class SearchGUI extends JFrame{
	private JPanel panel=new JPanel();
	private JLabel welcome;
	private JButton search_button;
	private static Student mystudent;
	
	SearchGUI(){  
		initFrame();
		initPanel();
		setVisible(true);
	}
	/****窗体初始化，负责整个窗体的初始化，添加Panel****/
	void initFrame(){    
		setTitle("检索对话框");
		setSize(400, 300);
		setLayout(new FlowLayout());
		add(panel);
	}
	/*****Panel初始化，初始化内部的组件和逻辑*****/
	void initPanel(){  
		search_button=new JButton("搜索");
		search_button.addActionListener(new SearchDialog());  
		welcome=new JLabel("点击搜索");
		add(search_button);
		panel.add(search_button);
		panel.add(welcome);
		panel.setVisible(true);
	}
	/****得到搜索结果****/
	public static Student tranStudent(){
		return mystudent;
	}
	/****搜索监听器****/
	class SearchDialog implements ActionListener{  //检索监听
		private StudentManager sm=new StudentManager();
		public void actionPerformed(ActionEvent e) {
			sm.init();
			String str=JOptionPane.showInputDialog(SearchGUI.this,"输入","输入对话框",JOptionPane.PLAIN_MESSAGE);
			if(str.length()!=0){
				mystudent=sm.getStudent(str);
				//System.out.println(mystudent.getSnumber());
				if(mystudent==null){
					JOptionPane.showConfirmDialog(SearchGUI.this, "查无此人！", "警告", JOptionPane.WARNING_MESSAGE);
				}
				else{
					SingleStudentFrame single=new SingleStudentFrame();
					SearchGUI.this.dispose();
				}
			}
			else{
				JOptionPane.showConfirmDialog(SearchGUI.this, "请输入正确的学号信息！", "警告", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
/**
 * 单个学生成绩的可编辑窗口，完成一个学生的成绩编辑，并且保存到内存
 * @author Mr.Bubbles
 *
 */
class SingleStudentFrame extends JFrame{
	
	private StudentManager sm=new StudentManager();  //sm中共享变量保存区
	private Student student;    //接受传递过来的搜索对象
	private JPanel StudentInformation;
	private JPanel StudentGrade;
	private JPanel ButtonPanel;
	
	/*private String myattgrade=null;
	private String myhomegrade=null;
	private String myexpgrage=null;
	private String mytestgrade=null;
	private String myprograde=null;*/
	
	JTextField attgrade;
	JTextField homegrade;
	JTextField expgrade;
	JTextField prograde;
	JTextField testgrade;
	
	SingleStudentFrame(){
		initFrame();  //构建Frame 
		initPanel();  //构建Panel 
		setVisible(true); //显示窗口
	}
	void initFrame(){
		Container c=getContentPane();
		StudentInformation=new JPanel();
		StudentGrade=new JPanel();
		ButtonPanel=new JPanel();
		setTitle("学生成绩编辑");
		setSize(Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH);
		setLayout(null);
		setResizable(false);
		c.setBackground(Color.CYAN);
		c.add(StudentInformation);
		c.add(StudentGrade);
		c.add(ButtonPanel);
		
	}
	void initPanel(){
		student=myJFrame.tranStudent();
		//标签初始化
		JLabel name=new JLabel("学生姓名",JLabel.CENTER);	
		JLabel number=new JLabel("学生学号",JLabel.CENTER);	
		JLabel major=new JLabel("学生专业",JLabel.CENTER);
		JLabel klass=new JLabel("学生班级",JLabel.CENTER);
		
		JLabel attendence=new JLabel("课堂出勤",JLabel.CENTER);
		JLabel homework=new JLabel("平时作业",JLabel.CENTER);
		JLabel experiment=new JLabel("实验成绩",JLabel.CENTER);
		JLabel project=new JLabel("Project成绩",JLabel.CENTER);
		JLabel test=new JLabel("考试成绩");
		//JLabel fin=new JLabel("总成绩");
		//学生信息显示
		JLabel sname=new JLabel(student.getSname(),JLabel.CENTER);
		JLabel snumber=new JLabel(student.getSnumber(),JLabel.CENTER);
		JLabel sclass=new JLabel(student.getSclass(),JLabel.CENTER);
		JLabel smajor=new JLabel(student.getSmajor(),JLabel.CENTER);
		//学生成绩编辑
		attgrade=new JTextField(8);attgrade.setText(student.getAttendence_grade());
		homegrade=new JTextField(8);homegrade.setText(student.getHomework_grade());
		expgrade=new JTextField(8);expgrade.setText(student.getExperiment_grade());
		prograde=new JTextField(8);prograde.setText(student.getProject_grade());
		testgrade=new JTextField(8);testgrade.setText(student.getExam_grade());
		

		//按钮初始化
		String str_save=new String("保存");
		String str_cancel=new String("取消");
		JButton save=new JButton(str_save);
		JButton cancel=new JButton(str_cancel);
		save.addActionListener(new Save());
		cancel.addActionListener(new Cancel());

		//设置字体字号
		number.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		name.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		major.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		klass.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		
		attendence.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		homework.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		experiment.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		project.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		test.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		sname.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		snumber.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		sclass.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		smajor.setFont(new java.awt.Font("Dialog", 1, Constant.FONTSIZE));
		
		/*StudentInformation.setBackground(Color.gray);
		StudentGrade.setBackground(Color.blue);
		ButtonPanel.setBackground(Color.RED);*/
		
		//设置Panel
		StudentInformation.setBounds(0,0,Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH*1/3);
		StudentGrade.setBounds(0,Constant.INNER_FRAME_WIDTH*1/3,Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH*1/2);
		ButtonPanel.setBounds(0,Constant.INNER_FRAME_WIDTH*5/6,Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH*1/6);
		StudentInformation.setLayout(new GridLayout(2,4));
		StudentGrade.setLayout(new GridLayout(2,5));
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,90,10));
		StudentInformation.setBorder(new LineBorder(new Color(0,0,0)));
		StudentGrade.setBorder(new LineBorder(new Color(0,0,0)));
		//向学生信息Panel中添加内容
		StudentInformation.add(name);
		StudentInformation.add(number);
		StudentInformation.add(major);
		StudentInformation.add(klass);
		
		StudentInformation.add(sname);
		StudentInformation.add(snumber);
		StudentInformation.add(smajor);
		StudentInformation.add(sclass);
		//向学生成绩Panel中添加内容
		StudentGrade.add(attendence);
		StudentGrade.add(homework);
		StudentGrade.add(experiment);
		StudentGrade.add(project);
		StudentGrade.add(test);
		//向学生成绩Panel中添加可编辑的文本框
		StudentGrade.add(attgrade);
		StudentGrade.add(homegrade);
		StudentGrade.add(expgrade);
		StudentGrade.add(prograde);
		StudentGrade.add(testgrade);
		//按钮Panel的添加
		ButtonPanel.add(save);
		ButtonPanel.add(cancel);
	}
	//点击保存按钮事件
	class Save implements ActionListener{
		public void actionPerformed(ActionEvent e) {	
			int n=0;
			System.out.println(student.getSnumber());
			n=JOptionPane.showConfirmDialog(SingleStudentFrame.this, "确定保存编辑吗？");
			if(n==JOptionPane.YES_OPTION){
				student.setAttendence_grade(attgrade.getText());
				student.setHomework_grade(homegrade.getText());
				student.setExam_grade(testgrade.getText());
				student.setProject_grade(prograde.getText());
				student.setExperiment_grade(expgrade.getText());
				//sm.setStudent(student.getSnumber(),student);
				sm.setStudent("2013217160", student);
				JOptionPane.showMessageDialog(SingleStudentFrame.this, "已经保存！");
			}
		}
		
	}
	//点击取消按钮的事件
	class Cancel implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int n=0;
			n=JOptionPane.showConfirmDialog(SingleStudentFrame.this, "确定取消编辑吗？");
			if(n==JOptionPane.YES_OPTION){
				SingleStudentFrame.this.dispose();
			}
		}
	}
}

/**
 * 常量类
 * @author Mr.Bubbles
 *
 */
class Constant{
	public static int MAIN_FRAME_LENGTH=1000;  //主Frame的大小
	public static int MAIN_FRAME_WIDTH=800;
	public static int INNER_FRAME_LENGTH=900;  //通用Frame的大小
	public static int INNER_FRAME_WIDTH=500;
	public static int FONTSIZE=20;    //字体大小
}