package com.example.mathprojectshaharlt;

import java.util.Random;

public class Exercise {
    private int num;
    private int num1;
    public Exercise(){

    }
    public void challenge(){
        Random r = new Random();
        num = r.nextInt(9)+1;
        num1 = r.nextInt(90)+10;
    }
    public void until20(){
        Random r = new Random();
        num = r.nextInt(9)+1;
        num1 = r.nextInt(10)+10;
    }
    public void multyTable(){
        Random r = new Random();
        num = r.nextInt(9)+1;
        num1 = r.nextInt(9)+1;
    }
    public boolean check(int ans){
        if(num*num1 == ans)
            return true;
        else
            return false;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

}
