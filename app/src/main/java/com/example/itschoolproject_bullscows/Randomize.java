package com.example.itschoolproject_bullscows;

class Randomize {
    private int[] num; //массив чисел длиной nx( см. конструктор )
    private boolean x = true; //условие для окончания обработки массива
    private boolean FirstZero = false;

    Randomize(int nx, boolean fz){ //собственно, конструктор; получаем длину массива
        num = new int[ nx ];
        FirstZero = fz;
    }
    private String ArrayToString( int[] a ){ //создание строки из массива однозначных чисел
        String s = "";
        for( int i = 0 ; i < a.length ; i++ ){
            s += Integer.toString( a[ i ] ); //к строке прибавляется каждый элемент массива
        }
        return s;
    }
    String Generate(){ //генератор случайного числа с условием игры
        for( int i = 0 ; i < num.length ; i++ ){
            num[ i ] = ( int )( Math.random() * 10 ); //генерация числа без условия
        }
        while( x ) {
            x = false;
            for ( int i = 0 ; i < num.length ; i++ ) {
                for ( int j = i + 1 ; j < num.length ; j++ ) {
                    /*
                    сравнение каждого элемента массива с каждым
                    если есть одинаковые или нулевой элемент равен нулю, то рандомим всё число по-новой
                     */
                    if( FirstZero ){
                        if ( num[ i ] == num[ j ] ) {
                            num[ i ] = ( int )( Math.random() * 10 );
                            x = true;
                        }
                    }
                    else if ( num[ i ] == num[ j ] || num[ 0 ] == 0 ) {
                        num[ i ] = ( int )( Math.random() * 10 );
                        x = true;
                    }
                }
            }
        }
        return ArrayToString( num );
    }
}
