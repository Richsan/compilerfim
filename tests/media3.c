#include<stdio.h>
#incude<string.h>
#include<stdlib.h>

int main(){

double x1,x2,x3,m;
printf("%s ","The first note: ");
scanf("%lf",&x1);
printf("%s ","The second note: ");
scanf("%lf",&x2);
printf("%s ","The third note: ");
scanf("%lf",&x3);
m = ( x1 + x2 + x3) / 3;
printf("\n%s %lf ","The average is: ",m);
return 0;
}
