package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;


/*public class GUI {
	public static void main(String args[]){   //����������
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
	/****�����ʼ����������������ĳ�ʼ�������Panel****/
	void initFrame(){    
		setTitle("�����Ի���");
		setSize(400, 300);
		setLayout(new FlowLayout());
		add(panel);
	}
	/*****Panel��ʼ������ʼ���ڲ���������߼�*****/
	void initPanel(){  
		search_button=new JButton("����");
		search_button.addActionListener(new SearchDialog());  
		welcome=new JLabel("�������");
		add(search_button);
		panel.add(search_button);
		panel.add(welcome);
		panel.setVisible(true);
	}
	/****�õ��������****/
	public static Student tranStudent(){
		return mystudent;
	}
	/****����������****/
	class SearchDialog implements ActionListener{  //��������
		private StudentManager sm=new StudentManager();
		public void actionPerformed(ActionEvent e) {
			sm.init();
			String str=JOptionPane.showInputDialog(SearchGUI.this,"����","����Ի���",JOptionPane.PLAIN_MESSAGE);
			if(str.length()!=0){
				mystudent=sm.getStudent(str);
				//System.out.println(mystudent.getSnumber());
				if(mystudent==null){
					JOptionPane.showConfirmDialog(SearchGUI.this, "���޴��ˣ�", "����", JOptionPane.WARNING_MESSAGE);
				}
				else{
					SingleStudentFrame single=new SingleStudentFrame();
					SearchGUI.this.dispose();
				}
			}
			else{
				JOptionPane.showConfirmDialog(SearchGUI.this, "��������ȷ��ѧ����Ϣ��", "����", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
/**
 * ����ѧ���ɼ��Ŀɱ༭���ڣ����һ��ѧ���ĳɼ��༭�����ұ��浽�ڴ�
 * @author Mr.Bubbles
 *
 */
class SingleStudentFrame extends JFrame{
	
	private StudentManager sm=new StudentManager();  //sm�й������������
	private Student student;    //���ܴ��ݹ�������������
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
		initFrame();  //����Frame 
		initPanel();  //����Panel 
		setVisible(true); //��ʾ����
	}
	void initFrame(){
		Container c=getContentPane();
		StudentInformation=new JPanel();
		StudentGrade=new JPanel();
		ButtonPanel=new JPanel();
		setTitle("ѧ���ɼ��༭");
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
		//��ǩ��ʼ��
		JLabel name=new JLabel("ѧ������",JLabel.CENTER);	
		JLabel number=new JLabel("ѧ��ѧ��",JLabel.CENTER);	
		JLabel major=new JLabel("ѧ��רҵ",JLabel.CENTER);
		JLabel klass=new JLabel("ѧ���༶",JLabel.CENTER);
		
		JLabel attendence=new JLabel("���ó���",JLabel.CENTER);
		JLabel homework=new JLabel("ƽʱ��ҵ",JLabel.CENTER);
		JLabel experiment=new JLabel("ʵ��ɼ�",JLabel.CENTER);
		JLabel project=new JLabel("Project�ɼ�",JLabel.CENTER);
		JLabel test=new JLabel("���Գɼ�");
		//JLabel fin=new JLabel("�ܳɼ�");
		//ѧ����Ϣ��ʾ
		JLabel sname=new JLabel(student.getSname(),JLabel.CENTER);
		JLabel snumber=new JLabel(student.getSnumber(),JLabel.CENTER);
		JLabel sclass=new JLabel(student.getSclass(),JLabel.CENTER);
		JLabel smajor=new JLabel(student.getSmajor(),JLabel.CENTER);
		//ѧ���ɼ��༭
		attgrade=new JTextField(8);attgrade.setText(student.getAttendence_grade());
		homegrade=new JTextField(8);homegrade.setText(student.getHomework_grade());
		expgrade=new JTextField(8);expgrade.setText(student.getExperiment_grade());
		prograde=new JTextField(8);prograde.setText(student.getProject_grade());
		testgrade=new JTextField(8);testgrade.setText(student.getExam_grade());
		

		//��ť��ʼ��
		String str_save=new String("����");
		String str_cancel=new String("ȡ��");
		JButton save=new JButton(str_save);
		JButton cancel=new JButton(str_cancel);
		save.addActionListener(new Save());
		cancel.addActionListener(new Cancel());

		//���������ֺ�
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
		
		//����Panel
		StudentInformation.setBounds(0,0,Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH*1/3);
		StudentGrade.setBounds(0,Constant.INNER_FRAME_WIDTH*1/3,Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH*1/2);
		ButtonPanel.setBounds(0,Constant.INNER_FRAME_WIDTH*5/6,Constant.INNER_FRAME_LENGTH,Constant.INNER_FRAME_WIDTH*1/6);
		StudentInformation.setLayout(new GridLayout(2,4));
		StudentGrade.setLayout(new GridLayout(2,5));
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,90,10));
		StudentInformation.setBorder(new LineBorder(new Color(0,0,0)));
		StudentGrade.setBorder(new LineBorder(new Color(0,0,0)));
		//��ѧ����ϢPanel���������
		StudentInformation.add(name);
		StudentInformation.add(number);
		StudentInformation.add(major);
		StudentInformation.add(klass);
		
		StudentInformation.add(sname);
		StudentInformation.add(snumber);
		StudentInformation.add(smajor);
		StudentInformation.add(sclass);
		//��ѧ���ɼ�Panel���������
		StudentGrade.add(attendence);
		StudentGrade.add(homework);
		StudentGrade.add(experiment);
		StudentGrade.add(project);
		StudentGrade.add(test);
		//��ѧ���ɼ�Panel����ӿɱ༭���ı���
		StudentGrade.add(attgrade);
		StudentGrade.add(homegrade);
		StudentGrade.add(expgrade);
		StudentGrade.add(prograde);
		StudentGrade.add(testgrade);
		//��ťPanel�����
		ButtonPanel.add(save);
		ButtonPanel.add(cancel);
	}
	//������水ť�¼�
	class Save implements ActionListener{
		public void actionPerformed(ActionEvent e) {	
			int n=0;
			System.out.println(student.getSnumber());
			n=JOptionPane.showConfirmDialog(SingleStudentFrame.this, "ȷ������༭��");
			if(n==JOptionPane.YES_OPTION){
				student.setAttendence_grade(attgrade.getText());
				student.setHomework_grade(homegrade.getText());
				student.setExam_grade(testgrade.getText());
				student.setProject_grade(prograde.getText());
				student.setExperiment_grade(expgrade.getText());
				//sm.setStudent(student.getSnumber(),student);
				sm.setStudent("2013217160", student);
				JOptionPane.showMessageDialog(SingleStudentFrame.this, "�Ѿ����棡");
			}
		}
		
	}
	//���ȡ����ť���¼�
	class Cancel implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int n=0;
			n=JOptionPane.showConfirmDialog(SingleStudentFrame.this, "ȷ��ȡ���༭��");
			if(n==JOptionPane.YES_OPTION){
				SingleStudentFrame.this.dispose();
			}
		}
	}
}

/**
 * ������
 * @author Mr.Bubbles
 *
 */
class Constant{
	public static int MAIN_FRAME_LENGTH=1000;  //��Frame�Ĵ�С
	public static int MAIN_FRAME_WIDTH=800;
	public static int INNER_FRAME_LENGTH=900;  //ͨ��Frame�Ĵ�С
	public static int INNER_FRAME_WIDTH=500;
	public static int FONTSIZE=20;    //�����С
}