package project1;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.management.openmbean.OpenDataException;
import javax.naming.InitialContext;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



class  myJFrame extends JFrame{//swing����JFrame

private JButton  but1;//swing��ťJButton  
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
    super("ѧ���ɼ�����ϵͳ"); //���ø��๹�췽��
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
    
    JLabel jl=new JLabel("��ӭʹ��ѧ���ɼ�����ϵͳ��");
    jl.setFont(new Font("����",Font.BOLD, 30));
    jp1.add(jl);
    but1=new JButton("��������");
    but1.setFont(new Font( "����",Font.BOLD, 20));
    //but1.setBackground(Color.MAGENTA);
    jp2.add(but1); 
    but2=new JButton("����");
    but2.setFont(new Font( "����",Font.BOLD, 20));
    //but2.setBackground(Color.pink);
    but2.addActionListener(new SearchDialog());
    jp2.add(but2);
    but3=new JButton("����");
    but3.setFont(new Font( "����",Font.BOLD, 20));
    //but3.setBackground(Color.orange);
    but3.addActionListener(new listener1());
    jp2.add(but3);
    but4=new JButton("����");
    but4.setFont(new Font( "����",Font.BOLD, 20));
    //but4.setBackground(Color.yellow);
    jp2.add(but4);
    
    //����͵���
    but1.addActionListener(new listener_import());
    but4.addActionListener(new listener_export());
    
    jt=new JTextArea(1,20);
    jt.setFont(new Font( "����",Font.BOLD, 16));
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
    chooser.setDialogTitle("ѡ��ѧ������");
    
    int result = chooser.showOpenDialog(this);
    if(result == JFileChooser.APPROVE_OPTION){
     File file1 = chooser.getCurrentDirectory();
     String filepath = file1.getPath();
     String filename=chooser.getSelectedFile().getName();
     jt.setText("�ļ�·��:"+filepath+"\\"+filename+"�ѵ��롣"); //���ļ�·���赽JTextField 
     
     //��������
     dataimport = new StudentManager(filepath + "\\" + filename);
     boolean import_success = dataimport.init();
     if (import_success){
		jt.setText("���ݵ���ɹ���");
		}
     }
    }
//�¼�������
private class listener_import implements ActionListener{//����listener�ಢʵ�ּ�������
  public void actionPerformed(ActionEvent e) {
      open();  
      }
  }

private class listener_export implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		//��������
		dataimport.export();
		jt.setText("�����ɹ�");
	}	
}

/****����������****/
class SearchDialog implements ActionListener{  //��������
	//private StudentManager dataimport=new StudentManager();
	public void actionPerformed(ActionEvent e) {	
		String str=JOptionPane.showInputDialog(myJFrame.this,"����","����Ի���",JOptionPane.PLAIN_MESSAGE);
		if(str.length()!=0){
			mystudent=dataimport.getStudent(str);
			//System.out.println(mystudent.getSnumber());
			if(mystudent==null){
				JOptionPane.showConfirmDialog(myJFrame.this, "���޴��ˣ�", "����", JOptionPane.WARNING_MESSAGE);
			}
			else{
				SingleStudentFrame single=new SingleStudentFrame();
				//myJFrame.this.dispose();
			}
		}
		else{
			JOptionPane.showConfirmDialog(myJFrame.this, "��������ȷ��ѧ����Ϣ��", "����", JOptionPane.WARNING_MESSAGE);
		}
	}
}
/****���㰴ť������****/
class listener1 implements ActionListener {   

	//����listener�ಢʵ�ּ�������
		public void actionPerformed(ActionEvent e) {
			 MyFrame2 window2=new MyFrame2(); 
			 window2.init();
		 }
	}
}

//����
public class MainGUI{
	public static void main(String args[]){
 	myJFrame frm=new myJFrame(); 
 	//SingleStudentFrame single=new SingleStudentFrame();
 
 }
}