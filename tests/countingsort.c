#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

int i,j,v;
int a[10],b[10],c[10];
printf("\n%s ","Enter ten numbers (positive and less than 10)");
i = 1;
while( i <= 10){
printf("%c %d ",'#',i);
scanf("%d",&v);
a[ i ] = v;
i = i + 1;

}
i = 1;
while( i <= 10){
c[ i ] = 0;
i = i + 1;

}
j = 1;
while( j <= 10){
c[ a[ j ] ] = c[ a[ j ] ] + 1;
j = j + 1;

}
i = 2;
while( i <= 10){
c[ i ] = c[ i ] + c[ i - 1 ];
i = i + 1;

}
j = 10;
while( j > 0){
b[ c[ a[ j ] ] ] = a[ j ];
c[ a[ j ] ] = c[ a[ j ] ] - 1;
j = j - 1;

}
printf("%s ","Here is your number list ordered: ");
i = 1;
while( i <= 10){
printf("%d ",a[ i ]);
if( i <= 9){
printf("%s ",", ");

}

}
printf("\n%s ","");
return 0;
}
