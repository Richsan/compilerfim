#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

int freq,i,j,k,l;
char w0[10],w1[10],w2[10];
printf("%s ","Wave frequency: ");
scanf("%d",&freq);
i = 1;
while( i <= 10){
if( ( i % freq) == 0){
w0[ i ] = '|';

}
else{
w0[ i ] = ' ';

}
i = i + 1;

}
i = 1;
while( i <= 10){
if( ( freq / 2) < i && i < ( 3 * freq / 2)){
w1[ i ] = '|';

}
else{
w1[ i ] = ' ';

}
i = i + 1;

}
i = 1;
while( i <= 10){
w2[ i ] = '|';
i = i + 1;

}
j = 1;
while( j <= 80){
k = j % 10;
l = j / 10 + 1;
while( l > 1){
i = 10 - k;
while( i <= 10){
printf("%c ",w0[ 10 - i ]);
i = i + 1;

}
l = l - 1;

}
printf("\n%s ","");
l = j / 10 + 1;
while( l > 1){
i = 10 - k;
while( i <= 10){
printf("%c ",w1[ 10 - i ]);
i = i + 1;

}
l = l - 1;

}
printf("\n%s ","");
l = j / 10 + 1;
while( l > 1){
i = 10 - k;
while( i <= 10){
printf("%c ",w2[ 10 - i ]);
i = i + 1;

}
l = l - 1;

}
printf("\n%s ","");

}
return 0;
}
