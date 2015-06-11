#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

int number;
printf("%s ","Tell me a number: ");
scanf("%d",&number);
while( number > 0){
number = number - 2;

}
if( number == 0){
printf("\n%s ","Your number is even.");

}
else{
printf("\n%s ","Your number is odd.");

}
return 0;
}
