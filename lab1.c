#include <stdio.h>

//void readScores(struct* arr);
  // The function returns void so the parameters
  // are the pointers that change whats in the main
  // see the swap function for more information

//void displayScores(struct* arr); // Function prototype for disp only

struct hist{ int id; int frq; char st;};

void calcHistogram(struct hist *s, int num);
void displayScores(int* arr, int size);
void displayHistogram(struct hist *s);
void sortHistogram(struct hist *s, int size);


int main() {
  // Place functions below main and create prototypes
  // Cannot use global variables

  int testInteger;
  struct hist freq[11];
  for(int i = 0; i < 11; i++){
    freq[i].st = 'e';
  }

  // The scanf loop should be readScores
  int scores[100];
  int count = 0;
  while(scanf("%d\n", &testInteger) != EOF){
    //printf("read %d\n", testInteger);
    calcHistogram(&freq, testInteger);
    scores[count] = testInteger;
    count++;
   }
  displayScores(scores,count);
  displayHistogram(freq);
  sortHistogram(freq, count);
  printf(" test run of freq disp\n");
  return 0; 
}
void calcHistogram(struct hist *s, int num){ // This should be calcHistogram
  for(int i = 0; i < 11; i++){
    if(s[i].id == num){
      s[i].frq++;
      return;
    }
  }
  // If the loop doesnt detect a number it has seen before
  for(int i = 0; i < 11; i++){
    if(s[i].st == 'e'){
      s[i].st = 'f';
      s[i].id = num;
      s[i].frq = 1;
      return;
    }
  }
}

void displayScores(int* arr, int size){
  for(int i = 0; i < size; i++){
    printf("score %d: %d\n", i, arr[i]);
  }
}

void displayHistogram(struct hist *s){
  // clean histogram elements don't have e
  while(s->st != 'e'){
    printf("value %d: freq %d\n", s->id, s->frq);
    s++;
  }
}

void sortHistogram(struct hist *s, int size){
  struct temp{ int id; int frq; char st;};
  struct temp sort[10];
  int count = 0;
  for(int i = 1; i < 4; i++){
    for(int x = 0; x < 2; x++){
      //      printf("check for %d\n", i);
      for(int y = 0; y < size; y++){
	if(i == s[y].frq){
	  sort[count].frq = i;
	  sort[count].id = s[y].id;
	  count++;
	}
      }
    }
  }
  for(int i = 0; i < 6; i++){
    printf("sort %d\n",sort[i].frq);
  }
}

void sortHistogram2(struct hist *s, int size){

  struct temp{ int id; int frq;};
  
  
  // Look through s
  int count = 0;
  int smallest_frq;
  int smallest_id;
  for(int i = 0; i < size; i++){
    smallest_frq = s[i].frq;
    for(int y = i; y < size; y++){
      if(s[y].frq < smallest_frq){
      }
      
    }
    
  }
}


