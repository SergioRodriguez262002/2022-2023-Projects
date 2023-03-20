#include <stdio.h>
int main() {
  int a[10];
  for (int i=0; i<10; i++) {
    a[i] = i*2;
  }
  int target = 5;
  int i =0;
  while (i<10 && a[i] != target) {
    i++;
  }
  if (i==10) {
    printf("not found\n");
  } else {
    printf("target found at %d\n", i);
  }
    
}
