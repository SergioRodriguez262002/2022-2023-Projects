#include <stdio.h>
#include <stdlib.h> // malloc
#include <string.h>
#include <math.h>

int binToDec(char* bin);
char* decToBin(int dec);
int baseToDec(int base, char* value);
int decToBase(int base, char* value);
int hexInt(char c);

int main(){
  int dec = binToDec("11001");
  printf("test bin to dec %d\n", dec); // should result in 25

  char *bin = decToBin(dec);
  printf("test dec to bin %s\n", bin);

  int dec2 = baseToDec(2, "11001");
  printf("dec2 %d\n", dec2);
  int dec8 = baseToDec(8, "157");
  printf("dec8 %d\n", dec8);
  int decH = baseToDec(16, "f8");
  printf("decH %d\n", decH);
  return 0;
}

int hexInt(char c){
  if(c >= '0' && c <= '9'){
    return c -'0';
  }
  if(c >= 'a' && c <= 'f'){
    return c - 'a' + 10;
  }
  return 0;
}

int baseToDec(int base, char* value){
  double num = 0.0;
  double exp = 0.0;
  for(int i = strlen(value)-1; i >= 0; i--){
    num += (hexInt(value[i]))*pow(base,exp);
    exp += 1.0;
  }
  return (int)num;
  
}

int binToDec(char* bin){
  double num = 0.0;
  double exp = 0.0;
  for(int i = strlen(bin)-1; i >= 0; i--){
    num += (hexInt(bin[i]))* pow(2.0, exp);
    exp += 1.0;
  }
  return (int)num;
}

char* decToBin(int dec){
  int temp = dec;
  char bin[100];
  int count = 0;
  while(temp != 0){
    bin[count] = (temp % 2) + '0';
    temp = temp / 2;
    count++;
  }
  char fin[count-1];
  int count2 = 0;
  for(int i = count-1; i >= 0; i--){
    fin[count2] = bin[i];
    count2++;
  }
  return fin;
}
