package project1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


/**
 * ���Excel�ĵ���͵��������Լ�student����Ϣ��װ
 * @author Kuang Haofei
 *
 */
class Student {
////////////////////////////////////���ݶ���//////////////////////////////////////
	//ѧ����Ϣ
	private String snumber; //ѧ��
	private String sname;   //����
	private String smajor;  //רҵ
	private String sclass;  //�༶
	
	//�ɼ���Ϣ
	private String attendence_grade;   //���ó���
	private String homework_grade;     //ƽʱ��ҵ
	private String experiment_grade;   //ʵ��ɼ�
	private String project_grade;      //Project
	private String exam_grade;         //���Գɼ�
	private String final_grade;        //�ܳɼ�
	
////////////////////////////////////��������//////////////////////////////////////
	//Ĭ�Ϲ��췽����������ʼ��
	public Student() {
		this.snumber = null;
		this.sname = null;
		this.smajor = null;
		this.sclass = null;
		this.attendence_grade = null;
		this.homework_grade = null;
		this.experiment_grade = null;
		this.project_grade = null;
		this.exam_grade = null;
		this.final_grade = null;
	}

	
	//���ι��췽�����Ա������г�ʼ��
	public Student(String snumber, String sname, String smajor, String sclass,
		String attendence_grade, String homework_grade, String experiment_grade,
		String project_grade, String exam_grade, String final_grade) {
	this.snumber = snumber;
	this.sname = sname;
	this.smajor = smajor;
	this.sclass = sclass;
	this.attendence_grade = attendence_grade;
	this.homework_grade = homework_grade;
	this.experiment_grade = experiment_grade;
	this.project_grade = project_grade;
	this.exam_grade = exam_grade;
	this.final_grade = final_grade;
}



	//��ȡ������ѧ��
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	//��ȡ����������
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}

	//��ȡ������רҵ
	public String getSmajor() {
		return smajor;
	}
	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	//��ȡ�����ð༶
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	//��ȡ�����ÿ��ó���
	public String getAttendence_grade() {
		return attendence_grade;
	}
	public void setAttendence_grade(String attendence_grade) {
		this.attendence_grade = attendence_grade;
	}

	//��ȡ������ƽʱ�ɼ�
	public String getHomework_grade() {
		return homework_grade;
	}
	public void setHomework_grade(String homework_grade) {
		this.homework_grade = homework_grade;
	}

	//��ȡ������ʵ��ɼ�
	public String getExperiment_grade() {
		return experiment_grade;
	}
	public void setExperiment_grade(String experiment_grade) {
		this.experiment_grade = experiment_grade;
	}

	//��ȡ������project
	public String getProject_grade() {
		return project_grade;
	}
	public void setProject_grade(String project_grade) {
		this.project_grade = project_grade;
	}

	//��ȡ�����ÿ��Գɼ�
	public String getExam_grade() {
		return exam_grade;
	}
	public void setExam_grade(String exam_grade) {
		this.exam_grade = exam_grade;
	}

	//��ȡ�������ܳɼ�
	public String getFinal_grade() {
		return final_grade;
	}
	public void setFinal_grade(String final_grade) {
		this.final_grade = final_grade;
	}
}

class StudentManager {
////////////////////////////////////���ݶ���//////////////////////////////////////
	//ѧ������
	//��װ��ѧ������Ϣ��ÿ���ڵ�ʱһ��ѧ������Ϣ
	public static ArrayList<Student> student = new ArrayList<Student>();

	private String path;  //excel�ļ�·��
	private File file;    //�ļ�
	
	
////////////////////////////////////��������//////////////////////////////////////
	//Ĭ�Ϲ��췽��
	//Ĭ��excel·��ΪProject1_�ɼ�.xls
	public StudentManager() {
		// TODO Auto-generated constructor stub
		path = "Project1_�ɼ�.xls";
		file = new File(path);
	}
	
	//���ι��췽��
	//����pathΪexcel�ļ���·��
	public StudentManager(String path) {
		this.path = path;
		file = new File(path);
	}
	
	//��ȡexcel���ݣ���student���Ͻ������ݵ���
	//�ɹ��򷵻�true��ʧ�ܷ���false
	public boolean init() {
		try {
			Workbook rbook = Workbook.getWorkbook(file);
			//�ɼ���
			Sheet sgrade = rbook.getSheet(0);
			Vector<String> temp = new Vector<String>();//�м����
			
			for (int i = 1; i < sgrade.getRows(); i++) {
				temp.clear();
				Student person = new Student();
				for (int j = 0; j < sgrade.getColumns(); j++) {
					temp.add(sgrade.getCell(j, i).getContents());
				}				
				for (int j = 0; j < temp.size(); j++) {
					
					switch (j) {
					case 0:
						person.setSnumber(temp.get(j));
						break;
					case 1:
						person.setSname(temp.get(j));
						break;
					case 2:
						person.setSmajor(temp.get(j));
						break;
					case 3:
						person.setSclass(temp.get(j));
						break;
					case 4:
						person.setAttendence_grade(temp.get(j));
						break;
					case 5:
						person.setHomework_grade(temp.get(j));
						break;
					case 6:
						person.setExperiment_grade(temp.get(j));
						break;
					case 7:
						person.setProject_grade(temp.get(j));
						break;
					case 8:
						person.setExam_grade(temp.get(j));
						break;
					case 9:
						person.setFinal_grade(temp.get(j));
						break;
						
					default:
						break;
					}
				}
				student.add(person);
			}
			rbook.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	//���һ��Student�Ķ���
	//����snumberΪ������ѧ��
	//�ҵ�����Student�Ķ���δ�ҵ�����null
	Student getStudent(String snumber){
		for (int i = 0; i < student.size(); i++) {
			if ( student.get(i).getSnumber().equals(snumber)) {
				return student.get(i);
			}
		}
		return null;
	}
	
	//�õ�ѧ���ĸ���
	//����student��ĸ���
	public int getStudentNumber() {
		return student.size();
	}

	//�õ�ĳһ�е�ѧ��
	//����iΪ�к�
	//����һ��ѧ������
	public Student getStudentByNumber(int i) {
		return student.get(i);	
	}
	
	//���ĳ��ѧ����ĳһ�е���Ϣ
	//����noΪ��no�е�ѧ��������coΪ��co�е�����
	//���ظõ�Ԫ�������
	public String getColumData(int no, int co) {
		String index = null;
		switch (co) {
		case 0:
			index = student.get(no).getSnumber();
			break;
		case 1:
			index = student.get(no).getSname();
			break;
		case 2:
			index = student.get(no).getSmajor();
			break;
		case 3:
			index = student.get(no).getSclass();
			break;
		case 4:
			index = student.get(no).getAttendence_grade();
			break;
		case 5:
			index = student.get(no).getHomework_grade();
			break;
		case 6:
			index = student.get(no).getExperiment_grade();
			break;
		case 7:
			index = student.get(no).getProject_grade();
			break;
		case 8:
			index = student.get(no).getExam_grade();
			break;
		case 9:
			index = student.get(no).getFinal_grade();
			break;
		default:
			break;
		}
		return index;
	}

	//�޸�һ��Student����
	//����snumberΪҪ�޸ĵ�ѧ���� ����stdΪ�µ�ֵ
	public void setStudent(String snumber, Student std) {
		for (int i = 0; i < student.size(); i++) {
			if ( student.get(i).getSnumber().equals(snumber)) {
				student.set(i, std);
			}
		}
	}
	
	//����ѧ�����ݵ�excel
	public void export() {
		try {
			Workbook wb=Workbook.getWorkbook(file);
			WritableWorkbook ebook=Workbook.createWorkbook(new File(path),wb);
			WritableSheet esheet=ebook.getSheet(0);
			Label label = null;
			for(int i=1;i<esheet.getRows();i++)
			{
				for(int j=0;j<esheet.getColumns();j++)
				{
					switch (j) {
					case 0:
						label=new Label(j,i,student.get(i - 1).getSnumber());
						break;
					case 1:
						label=new Label(j,i,student.get(i - 1).getSname());
						break;
					case 2:
						label=new Label(j,i,student.get(i - 1).getSmajor());
						break;
					case 3:
						label=new Label(j,i,student.get(i - 1).getSclass());
						break;
					case 4:
						label=new Label(j,i,student.get(i - 1).getAttendence_grade());
						break;
					case 5:
						label=new Label(j,i,student.get(i - 1).getHomework_grade());
						break;
					case 6:
						label=new Label(j,i,student.get(i - 1).getExperiment_grade());
						break;
					case 7:
						label=new Label(j,i,student.get(i - 1).getProject_grade());
						break;
					case 8:
						label=new Label(j,i,student.get(i - 1).getExam_grade());
						break;
					case 9:
						label=new Label(j,i,student.get(i - 1).getFinal_grade());
						break;
					default:
						break;
					}
					esheet.addCell(label);
				}
			}
			ebook.write();
			ebook.close();
			wb.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void print() {
		String no,n,m,c,a,h,ep,p,ex,f;
		for (int i = 0; i < student.size(); i++) {
			no = student.get(i).getSnumber();
			n = student.get(i).getSname();
			m = student.get(i).getSmajor();
			c = student.get(i).getSclass();
			
			a = student.get(i).getAttendence_grade();
			h = student.get(i).getHomework_grade();
			ep = student.get(i).getExperiment_grade();
			p = student.get(i).getProject_grade();
			ex = student.get(i).getExam_grade();
			f = student.get(i).getFinal_grade();
			
			System.out.println(no + ' ' + n + ' ' + m + ' ' + c + ' ' +
								a + ' ' + h + ' ' + ep + ' ' + p + ' ' + ex + ' ' + f);
		}
	}
	
	public void execute() {
		init();
		print();
		
		String index = "2013217143";
		System.out.println(getStudent(index).getSname());
		
		Student new_student = new Student("2013217172", "����", "�ƿ�", "����", "100", "100", "100", "100", "100", "100");
		
		setStudent(index, new_student);
		
		print();
		
		export();
	}	
}
