package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员引用变量
	Button btCompute,btClean;
	EditText etNumber1,etNumber2,etResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.定义、注册事件监听器
		btCompute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strNumber1,strNumber2;
				strNumber1=etNumber1.getText().toString().trim();
				strNumber2=etNumber2.getText().toString().trim();
				if(strNumber1.equals("")||strNumber1==null){
					Toast.makeText(getApplicationContext(), "请输入被加数!",
							Toast.LENGTH_SHORT).show();
				}else if(strNumber2.equals("")||strNumber2==null){
					Toast.makeText(getApplicationContext(), "请输入加数!",
							Toast.LENGTH_SHORT).show();
				}else{
					double number1=Double.parseDouble(strNumber1);//Float.parseFloat();Integer.parseInteger()
					double number2=Double.parseDouble(strNumber2);
					double result=number1+number2;
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
