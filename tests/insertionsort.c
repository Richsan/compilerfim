#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

int i,j,v;
int a[10];
printf("\n%s ","Enter ten numbers");
i = 1;
while( i <= 10){
printf("%c %d ",'#',i);
scanf("%d",&v);
a[ i ] = v;
i = i + 1;

}
i = 2;
while( i <= 10){
v = a[ i ];
j = i - 1;
while( j > 0 && a[ j ] > v){
a[ j + 1 ] = a[ j ];
j = j - 1;

}
a[ j + 1 ] = v;

}
printf("%s ","Here is your number list ordered: ");
i = 1;
while( i <= 10){
printf("%d ",a[ i ]);
if( i < 10){
printf("%s ",", ");

}

}
printf("\n%s ","");
return 0;
}
