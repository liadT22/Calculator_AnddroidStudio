package com.example.my_first_java_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Double> numList=new ArrayList<>();
    List<Integer> commandList=new ArrayList<>();
    TextView result;
    String strNum="";
    boolean reset=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=findViewById(R.id.textResult);
    }

    public void numberClick(View view) {
        if(view instanceof Button){
            Button button=(Button) view;
            String str=button.getText().toString();
            if(reset){
                result.setText("");
                reset=false;
            }
            strNum=strNum+str;
            result.append(str);
        }
    }

    public void commandFun(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String str = button.getText().toString();
            if (!strNum.equals("")) {
                switch (str) {
                    case "/":
                        commandList.add(4);
                        break;
                    case "X":
                        commandList.add(3);
                        break;
                    case "-":
                        commandList.add(2);
                        break;
                    case "+":
                        commandList.add(1);
                        break;
                    default:
                        result.setText("Error in switch case");
                }
                numList.add(Double.parseDouble(strNum));
                strNum = "";
                result.append(str);
            }
        }
    }

    public void buttonEqual(View view) {
        if(view instanceof Button) {
            if(numList.size()==0){
                return;
            }
            if(strNum==""&&numList.size()==commandList.size()){
                commandList.remove(commandList.size()-1);
            }else{
                numList.add(Double.parseDouble(strNum));
                strNum="";
            }
            Double newNum;
            while(commandList.contains(3)){
                int index=commandList.indexOf(3);
                newNum=numList.get(index)*numList.get(index+1);
                numList.set(index,newNum);
                numList.remove(index+1);
                commandList.remove(index);
            }
            while(commandList.contains(4)) {
                int index = commandList.indexOf(4);
                if (numList.get(index + 1) == 0) {
                    result.setText("Cant divide by 0!!");
                    numList.clear();
                    commandList.clear();
                    strNum="";
                    reset=true;
                    return;
                } else {
                    newNum = numList.get(index) / numList.get(index + 1);
                    numList.set(index, newNum);
                    numList.remove(index + 1);
                    commandList.remove(index);
                }
            }
            while(commandList.size()!=0){
                if(commandList.get(0)==1){
                    newNum=numList.get(0)+numList.get(1);
                }else{
                    newNum=numList.get(0)-numList.get(1);
                }
                numList.set(0,newNum);
                numList.remove(1);
                commandList.remove(0);
            }
            result.setText(numList.get(0).toString());
            reset=true;
            numList.clear();
            commandList.clear();
            strNum="";
        }
    }
    public void clearFun(View view) {
        if(view instanceof Button){
            numList.clear();
            commandList.clear();
            strNum="";
            result.setText("");
        }
    }
}