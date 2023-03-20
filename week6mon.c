#include <stdio.h>


/*
  




 */

int main(){
  int epi = 0; // estimated pi
  int n = 1000; // 100 terms

  for(int i = 0; i < n; i++){
    double term = 1.0 / ((2.0 * i) + 1);
    if(i%2 == 1){
      term *= -1;
    }
    epi += term;
  }

  epi = epi*4;
  printf("%d\n",epi);
}
