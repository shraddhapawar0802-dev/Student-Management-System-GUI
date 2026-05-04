import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

class StudentManagement_GUI
{
    static class Student
    {
        int id;
        String name;
        double marks;

        Student(int id, String name, double marks)
        {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }
    }


static class StudentManager
{
    ArrayList<Student> list = new ArrayList<>();

    void addStudent(Student s)
    {
        list.add(s);
    }
    void deleteStudent(int id)
    {
        list.removeIf(s-> s.id==id);
    }
    Student search(int id)
    {
        for(Student s: list)
        {
            if(s.id ==id)
                return s;
        }
        return null;
    }
    ArrayList<Student> getAllStudents()
    {
        return list;
    }
}

static StudentManager manager = new StudentManager();

public static void main(String [] args)
 {
    JFrame f = new JFrame("Student Management System");
    f.setSize(600,450);
    f.setLayout(null);

    JLabel l1 = new JLabel("ID:");
    JLabel l2 = new JLabel("Name:");
    JLabel l3 = new JLabel("Marks:");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JButton add = new JButton("Add");
    JButton delete = new JButton("Delete");
    JButton search = new JButton("search");
    JButton update = new JButton("update");

    String[] cols = {"ID","Name","Marks"};
    DefaultTableModel model = new
    DefaultTableModel(cols,0);
    JTable table = new JTable(model);
    JScrollPane sp = new JScrollPane(table);

    l1.setBounds(30,30,100,30);
    l2.setBounds(30,70,100,30);
    l3.setBounds(30,110,100,30);
    t1.setBounds(120,30,120,30);
    t2.setBounds(120,70,120,30);
    t3.setBounds(120,110,120,30);
    add.setBounds(30,160,80,30);
    delete.setBounds(120,160,80,30);
    search.setBounds(210,160,80,30);
    update.setBounds(300,160,80,30);
    sp.setBounds(30,210,520,180);

    f.add(l1);
    f.add(l2);
    f.add(l3);
    f.add(t1);
    f.add(t2);
    f.add(t3);
    f.add(add);
    f.add(delete);
    f.add(search);
    f.add(update);
    f.add(sp);

    add.addActionListener(e->{
        try{
            int id= Integer.parseInt(t1.getText());
            String name = t2.getText();
            double marks = Double.parseDouble(t3.getText());
            
            manager.addStudent(new Student(id,name,marks));
            model.addRow(new Object[]{id,name,marks});

            JOptionPane.showMessageDialog(f,"Student Added!");
            t1.setText("");
            t2.setText("");
            t3.setText("");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(f,"Invalid input!");
        }
    });
    delete.addActionListener(e->{
        try{
            int id=Integer.parseInt(t1.getText());
            manager.deleteStudent(id);

            for(int i=0;i<model.getRowCount(); i++)
            {
                if((int) model.getValueAt(i,0) ==id)
                {
                    model.removeRow(i);
                    break;
                }
            }
            JOptionPane.showMessageDialog(f,"Student Deleted!");
            t1.setText("");
            t2.setText("");
            t3.setText("");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(f,"Enter Valid ID!");
        }
    });
    
    search.addActionListener(e->{
        try{
            int id = Integer.parseInt(t1.getText());

            Student s = manager.search(id);
            if(s !=null)
            {
                t2.setText(s.name);
                t3.setText(String.valueOf(s.marks));
                JOptionPane.showMessageDialog(f,"Student Found!");
            }
            else
            {
                JOptionPane.showMessageDialog(f,"Not Found!");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(f,"Enter Valid ID!");
        }
    });

    update.addActionListener(e->{
        try{
            int id = Integer.parseInt(t1.getText());

            Student s = manager.search(id);
            if(s != null)
            {
                s.name = t2.getText();
                s.marks = Double.parseDouble(t3.getText());
                model.setRowCount(0);
                for(Student st: manager.list)
                {
                    model.addRow(new Object[]{st.id,st.name,st.marks});
                }
                JOptionPane.showMessageDialog(f,"Updated!");
            }
            else
            {
                JOptionPane.showMessageDialog(f,"Student Not Found!");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(f,"Invalid input!");
        }
    });

    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }  
}
