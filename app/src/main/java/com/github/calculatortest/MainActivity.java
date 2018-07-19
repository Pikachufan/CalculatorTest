package com.github.calculatortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_num0;
    private Button btn_num1;
    private Button btn_num2;
    private Button btn_num3;
    private Button btn_num4;
    private Button btn_num5;
    private Button btn_num6;
    private Button btn_num7;
    private Button btn_num8;
    private Button btn_num9;

    private Button btn_point;// 小数点
    private Button btn_divide;// 除以
    private Button btn_multiply;// 乘以
    private Button btn_minus;// 减去
    private Button btn_pluse;// 加
    private Button btn_equal;// 等于

    private Button btn_clear;  //清空
    private Button btn_del;  //退格

    private TextView textview1;
    private TextView textview2;

    private boolean flag;  //清空标识
    private double dou=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();

    }


    public void initView(){

        btn_num0 = (Button)findViewById(R.id.btn_num0);
        btn_num1 = (Button)findViewById(R.id.btn_num1);
        btn_num2 = (Button)findViewById(R.id.btn_num2);
        btn_num3 = (Button)findViewById(R.id.btn_num3);
        btn_num4 = (Button)findViewById(R.id.btn_num4);
        btn_num5 = (Button)findViewById(R.id.btn_num5);
        btn_num6 = (Button)findViewById(R.id.btn_num6);
        btn_num7 = (Button)findViewById(R.id.btn_num7);
        btn_num8 = (Button)findViewById(R.id.btn_num8);
        btn_num9 = (Button)findViewById(R.id.btn_num9);

        btn_point = (Button)findViewById(R.id.btn_point);//小数点
        btn_divide = (Button)findViewById(R.id.btn_divide);//除以
        btn_multiply = (Button)findViewById(R.id.btn_multiply);//乘法
        btn_minus = (Button)findViewById(R.id.btn_minus);//减法
        btn_pluse = (Button)findViewById(R.id.btn_pluse);//加法
        btn_equal = (Button)findViewById(R.id.btn_equal);//等于

        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_del = (Button)findViewById(R.id.btn_del);

        textview1 = (TextView)findViewById(R.id.textview1);
        textview2 = (TextView)findViewById(R.id.textview2);

        btn_num0.setOnClickListener(this);
        btn_num1.setOnClickListener(this);
        btn_num2.setOnClickListener(this);
        btn_num3.setOnClickListener(this);
        btn_num4.setOnClickListener(this);
        btn_num5.setOnClickListener(this);
        btn_num6.setOnClickListener(this);
        btn_num7.setOnClickListener(this);
        btn_num8.setOnClickListener(this);
        btn_num9.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_pluse.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {

        String str = textview2.getText().toString();
        switch (v.getId()){

            //点击数字键，textview就收到计算数字
            case R.id.btn_num0:
            case R.id.btn_num1:
            case R.id.btn_num2:
            case R.id.btn_num3:
            case R.id.btn_num4:
            case R.id.btn_num5:
            case R.id.btn_num6:
            case R.id.btn_num7:
            case R.id.btn_num8:
            case R.id.btn_num9:
            case R.id.btn_point:

                //再次输入的时候判断，把前面的清空，再把点击的按钮放在输入框上
                if(flag){
                    flag = false;
                    str = "";
                    textview2.setText("");
                }
                //我们只要点击键盘，相应的数字添加在TextView上
                textview2.setText(str+((Button) v).getText());
                break;

            //运算符也是一样需要添加的
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:

                if(flag){
                    flag = false;
                    str = "";
                    textview2.setText("");
                }
                //为了计算方便，我们可以在前后添加空格
                textview2.setText(str + " " + ((Button) v).getText() + " ");
                break;

            case R.id.btn_del:
                if(flag){
                    flag = false;
                    str = "";
                    textview2.setText("");
                } if(str != null && !str.equals(" ")){
                    //需要一个个删除，首先判断是否为空或者""
                    //用substring（）截取字符串-1，再设置回去，形成退格效果
                    textview2.setText(str.substring(0,str.length()-1));
                }
                break;

            //清除按钮，直接把输入框设置成空
            case R.id.btn_clear:
                flag = false;
                str = "";
                textview2.setText("");
                textview1.setText("");
                break;
            //计算结果
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    //等号计算结果
    public void getResult(){
        //首先取出完整的输入内容
        String result = textview2.getText().toString();
        //当输入是null或者""时我们不进行操作
        if(result == null || result.equals("")) {
            return;
        }

        //要计算结果，需要找到是否有运算符，判断条件是前后是否有" "
        //java.lang.String.contains()方法返回true，当且仅当此字符串包含指定的char值序列
        if(!result.contains(" ")){
            //没有运算符，不用运算
            textview1.setText(result);
        }

        //如果有空格，我们就截取前后段再获取运算符进行计算
        String str1 = result.substring(0,result.indexOf(" "));
        //indexOf字符在此字符串中的第一个匹配项
        String op = result.substring(result.indexOf(" ") + 1,result.indexOf(" ") + 2);
        String str2 = result.substring(result.indexOf(" ") + 3);

        //判断str1 和 str2 是否为空，才能计算

        if(!str1.equals("") && !str2.equals("")){
            //都不为空
              double d1 = Double.parseDouble(str1);
              double d2 = Double.parseDouble(str2);
              //进行计算
            if(op.equals("＋")){
                dou = d1 + d2;
            }else if(op.equals("-")) {
                dou = d1 - d2;
            }else if(op.equals("×")) {
                dou = d1 * d2;
            }else if (op.equals("÷")) {
                if(d2 == 0){
                    textview1.setText("error");
                    Toast.makeText(MainActivity.this,"除数不能为0",Toast.LENGTH_SHORT).show();
                }
                else {
                    dou = d1 / d2;
                }
            }
            //如果没有小数点就是int
            if(!str1.contains(".") && !str2.contains(".") && !op.equals("÷") ){
                int i = (int) dou;
                textview1.setText(i + "");
            }else{
                textview1.setText(dou + "");
            }
        }else if (!str1.equals("") && str2.equals("")){
            //str2为空
            textview1.setText(str1);
        }else if(str1.equals("") && !str2.equals("")){
            //str1为空
            //这种情况我们就需要判断了，因为此时str1 = 0;
            double d3 = Double.parseDouble(str2);
            if (op.equals("+")) {  //加
                dou = 0 + d3;
            } else if (op.equals("-")) { //减
                dou = 0 - d3;
            } else if (op.equals("×")) { //乘
                dou = 0;
            } else if (op.equals("÷")) {
                dou = 0;
            }
            //如果没有小数点就是int
            if(!str1.contains("·") && !str2.contains(".") && !op.equals("÷") ){
                int i = (int) dou;
                textview1.setText(i + "");
            }else{
                textview1.setText(dou + "");
            }
        }else {  //最后一种情况，他们两个都是空
            textview1.setText("");
            textview2.setText("");
        }

    }
}
