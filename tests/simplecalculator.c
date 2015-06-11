#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

double a,b;
char op;
char * again;
again = (char *)realloc(again,sizeof(char) * strlen("yes"));
strcpy(again,"yes");
while( again == "yes"){
scanf("%lf",&a);
scanf("%c",&op);
scanf("%lf",&b);
if( op == '+'){
printf("\n%lf %c %lf %c %lf ",a,op,b,'=',a + b);

}
else{
if( op == '-'){
printf("\n%lf %c %lf %c %lf ",a,op,b,'=',a - b);

}
else{
if( op == '*'){
printf("\n%lf %c %lf %c %lf ",a,op,b,'=',a * b);

}
else{
if( op == '/'){
if( b != 0){
printf("\n%lf %c %lf %c %lf ",a,op,b,'=',a / b);

}
else{
printf("\n%s ","Can not divide by zero!");

}

}

}

}

}
again = op;
while( !( again == "yes" || again == "no")){
printf("%s ","Do you wanna calculate again? ");
scanf("%s",&again);

}

}
printf("\n%s ","Thank you by use our hypermegablasterfucked calculator! :-)");
return 0;
}
