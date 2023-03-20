// char is 2^8 256

// 2s complement
/*
  flip bits of the original number  add 1 to represent negatives

   0 000
   1 001
   2 010
   3 011
  -4 100
  -3 101
  -2 110
  -1 111

  therefore the representation of -128 ... 127 decimal


  010 is  2
  110 si -2

  unsigned ints cannot hold negatives and range from 0 to all positives

   
  


 */


#include <stdio.h>

int main(){


  unsigned char x;
  char y;
  x = 0;

  for(int i = 0; i < 500; i++){
  printf("unsigned %d\n",x);
  printf("signed %d\n",y);
  y++;
  x++;
  }

  return 0;
}
