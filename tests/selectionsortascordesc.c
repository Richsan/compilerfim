#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

int i,j,min,max,t,n;
char * order;
int a[30];
printf("%s ","How much number (max: 30) have your list? ");
scanf("%d",&n);
printf("\n%s ","Enter your number list:");
i = 1;
while( i <= n){
printf("%c %d ",'#',i);
scanf("%d",&t);
a[ i ] = t;
i = i + 1;

}
order = (char *)realloc(order,sizeof(char) * strlen("order"));
strcpy(order,"order");
while( !( order == "ASC" || order == "DESC")){
printf("%s ","How order? (ASC/DESC)? ");
scanf("%s",&order);

}
if( order != "DESC"){
i = 1;
while( i < n){
min = i;
j = i + 1;
while( j < n){
if( a[ j ] < a[ min ]){
min = j;

}

}
t = a[ min ];
a[ min ] = a[ i ];
a[ i ] = t;

}

}
else{
i = 1;
while( i < n){
max = i;
j = i + 1;
while( j < n){
if( a[ j ] > a[ max ]){
min = j;

}

}
t = a[ max ];
a[ max ] = a[ i ];
a[ i ] = t;

}

}
printf("%s ","Here is your number list ordered: ");
i = 1;
while( i <= n){
printf("%d ",a[ i ]);
if( i < n){
printf("%s ",", ");

}

}
printf("\n%s ","");
return 0;
}
