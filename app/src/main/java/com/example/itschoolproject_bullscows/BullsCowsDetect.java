package com.example.itschoolproject_bullscows;

class BullsCowsDetect {
    private String PlayerNum, RightNum; //число игрока и правильное число
    BullsCowsDetect(String n1, String n2){ //конструктор LMAO
        this.PlayerNum = n1;
        this.RightNum = n2;
    }
    private int BullsDetect( ){ //обнаружение всех "быков" ( совпадения с одинаковым индексом строки )
        int x = 0;
        try { //try-catch на выход за границы строки
            for (int i = 0; i < RightNum.length(); i++) {
                if (RightNum.charAt(i) == PlayerNum.charAt(i)) {
                    x++;
                }
            }
        }
        catch ( IndexOutOfBoundsException e ){
            //При выходе за границы число быков значительно больше возможного
            x = 228;
        }
        if( RightNum.length() != PlayerNum.length() ) x = 228;
        return x;
    }
    private int CowsDetect( ){ //обнаружение всех "коров" ( совпадений с разными индексами строки )
        int x = 0;
        try { //Аналогично с быками
            for (int i = 0; i < RightNum.length(); i++) {
                for (int j = 0; j < RightNum.length(); j++) {
                    if (RightNum.charAt(i) == PlayerNum.charAt(j) && i != j) {
                        x++;
                    }
                }
            }
        }
        catch( IndexOutOfBoundsException e ){
            //Аналогично с быками (опять)
            x = 228;
        }
        if( RightNum.length() != PlayerNum.length() ) x = 228;
        return x;
    }
    int[] GetBullsCows(){ //вывод массива [ быки , коровы ]
        int[] x = new int [ 2 ];
        x[ 0 ] = BullsDetect();
        x[ 1 ] = CowsDetect();
        return x;
    }
}
