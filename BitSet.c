#include <stdio.h>
#include <stdlib.h>


typedef unsigned short bitSet;
// a short has 16 bits

bitSet makeBitSet(); // Create new bitset
void displayBitSet(bitSet a); // Displays the 16 bits of the bitset to the screen
void setBit(bitSet *bs, int index);// sets bit 'index' of the bitset to 1
void clearBit(bitSet* bs, int index); // sets bit 'index' of the bitset to 0
int bitValue(bitSet bs, int index); // returns the value of hte bit at the index

int main(){

  

  bitSet a = makeBitSet();
  displayBitSet(a);

  setBit(&a, 1);

  displayBitSet(a);

  clearBit(&a, 1);

  displayBitSet(a);

  setBit(&a, 1);

  displayBitSet(a);

  printf("check bit at 1 %d",bitValue(a,1));
  
  return 0;
}

bitSet makeBitSet(){
  bitSet a = 0;
  return a;
}

void setBit(bitSet *bs, int index){
  *bs |= 1UL << index;
}

void clearBit(bitSet *bs, int index){
  *bs &= ~(1UL << index);
}

int bitValue(bitSet bs, int index){
  return (bs >> index) & 1U;
}

void displayBitSet(bitSet a){ // remove parameters 
  unsigned short maxPow = 1<<(sizeof(bitSet)*8-1);
  printf("\n"); // new line
  for(int i = 0; i < 16; i++){
    printf("%u ", a&maxPow ? 1:0);
    a = a << 1;
  }
  
  
}
