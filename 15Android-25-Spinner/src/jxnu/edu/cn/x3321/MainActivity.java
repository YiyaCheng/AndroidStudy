package jxnu.edu.cn.x3321;

import java.util.ArrayList;

import jxnu.edu.cn.x3321.adapter.MyAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员引用变量
	Button btCompute,btClean;
	EditText etNumber1,etNumber2,etResult;
	Spinner sp;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    //2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.准备数据
		al.add(" + ");
		al.add(" - ");
		al.add(" * ");
		al.add(" / ");
		//4.自定义适配器封装数据
		ma=new MyAdapter(getApplicationContext(),al);
		//5.把ma中的数据加载到sp中
		sp.setAdapter(ma);
		
		//6.定义、注册事件监听器
		btCompute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strNumber1,strNumber2;
				String symbol;
				TextView tv=(TextView)sp.getChildAt(0);
				symbol=tv.getText().toString().trim();			
				
				strNumber1=etNumber1.getText().toString().trim();
				strNumber2=etNumber2.getText().toString().trim();
				if(strNumber1.equals("")||strNumber1==null){
					Toast.makeText(getApplicationContext(), "请输入操作数1!",
							Toast.LENGTH_SHORT).show();
				}else if(strNumber2.equals("")||strNumber2==null){
					Toast.makeText(getApplicationContext(), "请输入操作数2!",
							Toast.LENGTH_SHORT).show();
				}else{
									
					double number1=Double.parseDouble(strNumber1);//Float.parseFloat();Integer.parseInteger()
					double number2=Double.parseDouble(strNumber2);
					double result;
					if(symbol.equals("+")){			
			            result=number1+number2;
					}else if(symbol.equals("-")){
						result=number1-number2;
					}else if(symbol.equals("*")){
						result=number1*number2;
					}else
					{
						result=number1/number2;
					}
					
					
					etResult.setText(String.valueOf(result));
				}
			}
		});
		btClean.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etNumber1.setText("");
				etNumber2.setText("");
				etResult.setText("");
			}
		});
		
	}

	private void init() {
		// TODO Auto-generated method stub
		btCompute=(Button)this.findViewById(R.id.bt_compute);
		btClean=(Button)this.findViewById(R.id.bt_clean);
		etNumber1=(EditText)this.findViewById(R.id.et_number1);
		etNumber2=(EditText)this.findViewById(R.id.et_number2);
		etResult=(EditText)this.findViewById(R.id.et_result);
		sp=(Spinner)this.findViewById(R.id.sp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
