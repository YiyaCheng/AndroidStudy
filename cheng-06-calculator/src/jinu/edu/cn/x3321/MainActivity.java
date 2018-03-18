package jinu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	Button number1,number2,number3,number4,number5,number6,number7,number8,number9,number0;
	Button btCheng,btJian,btChu,btJia,btEqual,btDot,btClean,btDel,btGen,btPer;
	EditText etResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		number1.setOnClickListener(this);
		number2.setOnClickListener(this);
		number3.setOnClickListener(this);
		number4.setOnClickListener(this);
		number5.setOnClickListener(this);
		number6.setOnClickListener(this);
		number7.setOnClickListener(this);
		number8.setOnClickListener(this);
		number9.setOnClickListener(this);
		number0.setOnClickListener(this);
		btCheng.setOnClickListener(this);
		btChu.setOnClickListener(this);
		btJia.setOnClickListener(this);
		btJian.setOnClickListener(this);
		btEqual.setOnClickListener(this);
		btDot.setOnClickListener(this);
		btClean.setOnClickListener(this);
		btDel.setOnClickListener(this);
		btGen.setOnClickListener(this);
		btPer.setOnClickListener(this);
		
	}
	public void onClick(View v)
	{
		String existNum="";
		existNum=etResult.getText().toString();
		switch(v.getId())
		{
		   case R.id.bt_0:
		   case R.id.bt_1:
		   case R.id.bt_2:
		   case R.id.bt_3:
		   case R.id.bt_4:
		   case R.id.bt_5:
		   case R.id.bt_6:
		   case R.id.bt_7:
		   case R.id.bt_8:
		   case R.id.bt_9:
		   case R.id.bt_dot:
			   etResult.setText(existNum+((Button)v).getText());
			   break;
		   case R.id.bt_jia:
		   case R.id.bt_jian:
		   case R.id.bt_cheng:
		   case R.id.bt_chu:
			   etResult.setText(existNum+" "+((Button)v).getText()+" ");
			   break;
		   case R.id.bt_percent:
			   etResult.setText(existNum);
			   getResult1();
			   break;
		   case R.id.bt_gen:
			   etResult.setText(existNum);
			   getResult2();
			   break;
		   case R.id.bt_del:
			   etResult.setText(existNum.substring(0,existNum.length() - 1));
			   break;
		   case R.id.bt_clean:
			   etResult.setText("");
			   break;
		   case R.id.bt_equal:
			   getResult();
			   break;
		}	
	}

	private void getResult1() {
		// TODO Auto-generated method stub
		double s=Double.parseDouble(etResult.getText().toString());
		s=1/s;
		etResult.setText(s+"");
	}
	private void getResult2() {
		// TODO Auto-generated method stub
		double s=Double.parseDouble(etResult.getText().toString());
		s=Math.sqrt(s);
		etResult.setText(s+"");
	}
	private void getResult() {
		// TODO Auto-generated method stub
		String str=etResult.getText().toString();
		if(str.equals(null)||str=="")
			return;
		
		String s1=str.substring(0,str.indexOf(" "));//截取的第一个数
		String operate=str.substring(str.indexOf(" ")+1,str.indexOf(" ")+2);//截取的运算符
		String s2=str.substring(str.indexOf(" ")+3);
		double result=0;
		if(!s1.equals("")&&!s2.equals(""))
		{
			double num1=Double.parseDouble(s1);
			double num2=Double.parseDouble(s2);
			if(operate.equals("+"))
			{
				result=num1+num2;
			}
			else if(operate.equals("-"))
			{
				result=num1-num2;
			}
			else if(operate.equals("*"))
			{
				result=num1*num2;
			}
			else if(operate.equals("/"))
			{
				if(num2!=0)
				  result=num1/num2;
				else
					Toast.makeText(getApplicationContext(), "除数不能为0！",Toast.LENGTH_SHORT).show();
			}
		}
			
		if(!s1.contains(".")&&!s2.contains("."))
		{
			int r=(int)result;
			etResult.setText(r+"");
			}
		else
		{
			etResult.setText(result+"");
		}
	
		if(!s1.equals("")&&s2.equals(""))
		{
			etResult.setText(str);
		}
		else if(s1.equals("")&&!s2.equals(""))
		{
			etResult.setText(str);
		}
	}
	private void init() {
		number1=(Button)this.findViewById(R.id.bt_1);
		number2=(Button)this.findViewById(R.id.bt_2);
		number3=(Button)this.findViewById(R.id.bt_3);
		number4=(Button)this.findViewById(R.id.bt_4);
		number5=(Button)this.findViewById(R.id.bt_5);
		number6=(Button)this.findViewById(R.id.bt_6);
		number7=(Button)this.findViewById(R.id.bt_7);
		number8=(Button)this.findViewById(R.id.bt_8);
		number9=(Button)this.findViewById(R.id.bt_9);
		number0=(Button)this.findViewById(R.id.bt_0);
		btCheng=(Button)this.findViewById(R.id.bt_cheng);
		btChu=(Button)this.findViewById(R.id.bt_chu);
		btJia=(Button)this.findViewById(R.id.bt_jia);
		btJian=(Button)this.findViewById(R.id.bt_jian);
		btEqual=(Button)this.findViewById(R.id.bt_equal);
		btClean=(Button)this.findViewById(R.id.bt_clean);
		btDot=(Button)this.findViewById(R.id.bt_dot);
		btDel=(Button)this.findViewById(R.id.bt_del);
		btGen=(Button)this.findViewById(R.id.bt_gen);
		btPer=(Button)this.findViewById(R.id.bt_percent);
		etResult=(EditText)this.findViewById(R.id.et_show);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
