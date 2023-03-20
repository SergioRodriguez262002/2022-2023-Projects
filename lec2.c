#include <stdio.h> // printf
#include <stdlib.h> // malloc 
#include <string.h>



int* fill(int* count){
  //   int a[10];
  // 10 * sizeof(int) says an array of 10 ints
  int* a = (int*)malloc(10 * sizeof(int)); // Creates an array in the heap
  // heap memory is more permantent than runtime stack
  // there is no garbage collector in c for the heap
  // notice how theres a star at the dec and beggining of malloc
  // the 40 represents the num bytes of memory allocated
  int c = 0;
  for(int i =0; i < 10; i++){
    a[i] = i*2;
    c++;
  }

  *count = c; // pointer changing count
  return a;
}

// Once the fill function ends the a array will remain
// until something else takes up its memory, Fill will eventually
// be popped off the stack


// The solution is to put things in heap memory



int main(){

   int* a; // Making a null pointer memory adress
  

  
  // a is a pointer
  // Equivalent to ArrayList a; in java
  // Notice how a is used later on in fill 
  
  // Arrays are pointers in c
  // %p is for printing pointers in hex
  // ex
  //   printf("arr pointer ex %p\n", a);

  int count = 0;
  // notice that the function will change the
  // pointer in the param
  a = fill( &count);
  printf("fill ex %d\n", a[2]);


  // Below is an ex of representing pointers %p and &
  // Adding one to a (a+1) ex; a[5] === *(a + 5)
  // * will return the memory at the a adress 
  printf("%p\n %p\n %p\n", a, a+1,  &(a[0]));

  // * is derefrence
  // segementation fault is a NULL pointer excp
  a[4] = 5;
  // printf("%d\n", a[15]); // a[15] --> *(a+15)
  // c has no array out of bounds checking,
  // memory can be reached through reading past the array
  // Will return one

  // making strings from arrays of chars
  char s[6];

  s[0] = 'h';
  s[1] = 'e';
  s[2] = 'l';
  s[3] = 'l';
  s[4] = 'o';
  s[5] = '\0'; // \0 is ascii code for 0, this is called terminating
  // a string

  printf("%s\n", s); // use %s to represent a string.

  s = "hi"; // shorthand for an aray of chars for a char

  strlen(s); // returns the length of a string, num chars
  strcmp(s1,s2); // like compareto in java, used in most things
  // compareto can return -anything 0 +anything, 0 means equal
  // remember that returning 0 in an if statement means false

  
  return 0;
}
