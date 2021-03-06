import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

class App
{
	public static native int calcBill(int[] params);
	static{System.loadLibrary("calc");}
	public static void main(String arg[])
	{
		MarvellousLogin mf=new MarvellousLogin();
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setVisible(true);
	}
}

class AppFrame extends JFrame
{

	public static final int DEFAULT_WIDTH=500;
	public static final int DEFAULT_HEIGHT=600;

	private Hashtable<String,Integer> processors;
	private Hashtable<String,Integer> ram;
	private Hashtable<String,Integer> graphicsCard;
	JLabel resultLabel2;

	public AppFrame()
	{
		setTitle("Marvellous Computer Shopping Portal");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		setLayout(new GridLayout(5,2));

		processors=new Hashtable<String,Integer>();
		processors.put("i3",10000);
		processors.put("i5",15000);
		processors.put("i7",20000);

		JPanel panel1=new JPanel();
		JLabel processorLabel=new JLabel("Select processor");
		panel1.add(processorLabel);

		JPanel panel2=new JPanel();
		Set<String>processorKeys=processors.keySet();
		JComboBox processorComboBox=new JComboBox(processorKeys.toArray());
		panel2.add(processorComboBox);

		 ram=new Hashtable<String,Integer>();
                ram.put("2 GB",3000);
                ram.put("4 GB",5000);
                ram.put("8 GB",8000);
		
		JPanel panel3=new JPanel();
                JLabel ramLabel=new JLabel("Select ram");
                panel3.add(ramLabel);

		JPanel panel4=new JPanel();
                Set<String> ramKeys=ram.keySet();
                JComboBox ramComboBox=new JComboBox(ramKeys.toArray());
                panel4.add(ramComboBox);

		 graphicsCard=new Hashtable<String,Integer>();
               	graphicsCard.put("2 GB",3000);
                 graphicsCard.put("4 GB",5000);
                 graphicsCard.put("8 GB",8000);

                JPanel panel5=new JPanel();
                JLabel  graphicsCardLabel=new JLabel("Select graphics card");
                panel3.add(graphicsCardLabel);

                JPanel panel6=new JPanel();
                Set<String> graphicsCardKeys= graphicsCard.keySet();
                JComboBox  graphicsCardComboBox=new JComboBox( graphicsCardKeys.toArray());
                panel6.add(graphicsCardComboBox);

		JPanel panel7=new JPanel();
		JButton calculateButton=new JButton("calculate Bill");
		calculateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int processPrice=processors.get((String)processorComboBox.getSelectedItem());
				  int ramPrice=ram.get((String)ramComboBox.getSelectedItem());
				  int   graphicsCardPrice=graphicsCard.get((String)graphicsCardComboBox.getSelectedItem());	
			int params[]=new int[3];
			params[0]=processPrice;
			params[1]=ramPrice;
			params[2]=graphicsCardPrice;

			int result=App.calcBill(params);
			resultLabel2.setText("" +result);
			}	
		});
	
		panel7.add(calculateButton);
		
		JPanel panel8=new JPanel();
		
		 JPanel panel9=new JPanel();
		JLabel resultLabel=new JLabel("Your estimated amount : ");
		panel9.add(resultLabel);
	
		 JPanel panel10=new JPanel();
                resultLabel2=new JLabel("");
                panel10.add(resultLabel2);
	
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		add(panel7);
		add(panel8);
		add(panel9);
		add(panel10);
	}

}
class MarvellousLogin extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JTextField tf1;
	JPasswordField tf2;
	JButton b;
	public static int Cnt=3;

	public MarvellousLogin()
	{
		super("Marvellous e shopping Portal");
		//setContentPane(new JLabel(new ImageIcon("/users/")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		l1=new JLabel("Username");
		l1.setBounds(50,70,150,20);;

		tf1=new JTextField();
		tf1.setBounds(50,100,200,20);
		tf1.setToolTipText("Enter your username");

		l2=new JLabel("Password");
		l2.setBounds(50,130,150,20);;

		tf2=new JPasswordField();
		tf2.setBounds(50,160,200,20);
		tf2.setToolTipText("Enter your password");

		b=new JButton("Login");
		b.setBounds(90,200,80,30);

		b.addActionListener(this);
		add(l1);
		add(tf1);
		add(l2);
		add(tf2);
		add(b);

		setSize(300,300);
		setLayout(null);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent ae)
	{
		CheckAuthetication();

	}
	public void CheckAuthetication()
	{
		String username=tf1.getText();
		String password=tf2.getText();

		if(username.equals("marvellous")&&(password.equals("marvellous@123")))
		{
			AppFrame af=new AppFrame();
			af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  //af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			af.setVisible(true);
			dispose();	
			   //af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else
		{
			--Cnt;
			if(Cnt==0)
			{
				JOptionPane.showMessageDialog(this,"Number of attempts finished.");
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Marvellous Authentication failed");
				tf1.setText("");
				tf2.setText("");
			}
		}
	}
}
