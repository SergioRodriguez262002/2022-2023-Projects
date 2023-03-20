#include <stdio.h>
#include <stdlib.h> // malloc
#include <string.h>

typedef struct Histogram{int id; int frq; char st;} Histogram;

int* calcHistogram(Histogram **s, int arr[], int size);
void displayScores(int arr[], int size);
void displayHistogram(Histogram *s);
void sortHistogram(Histogram *s);
int* readScores(int *size);

int main(){
  int size_scores;
  int *scores = readScores(&size_scores);
  displayScores(scores, size_scores);

  printf("size scores %d\n", size_scores);

  Histogram *freq;
  int size_hist =  calcHistogram(&freq,scores,size_scores);
  displayHistogram(freq);
  //  sortHistogram(&freq);

}

int* readScores(int *size){
  int testInteger;
  int count = 0;
  int* arr = (int*)malloc(100 * sizeof(int));
  while(scanf("%d\n", &testInteger) != EOF){
    arr[count] = testInteger;
    count++;
  }
  *size = count;
  return arr;
}

void displayScores(int arr[], int size){
  for(int i = 0; i < size; i++){
    printf("score %d: %d\n", i, arr[i]);
  }
}

int* calcHistogram(Histogram **h, int arr[], int size){
  int num;
  int flag1 = 1;
  int size_hist;

  Histogram* s = (Histogram*)malloc(100 * sizeof(Histogram));
  for(int i = 0; i < 110; i++){
    s[i].st = 'e';
  }
  
  for(int x = 0; x < size; x++){
    for(int y = 0; y < size; y++){
      if(arr[x] == s[y].id){
	s[y].frq++;
	y = size;
	flag1 = 0;
      }
    }

    //adding unseen nums
    if(flag1){
      for(int i = 0; i < size; i++){
	if(s[i].st == 'e'){
	  s[i].st = 'f';
	  s[i].id = arr[x];
	  s[i].frq = 1;
	  i = size;
	  size_hist++;
	}
      }
    }
    flag1 = 1;
  }

  *h = s;   //  Make temp vars so I dont have to derefrence everytium 
  
  return size_hist;
}
void displayHistogram(Histogram *s){
  while(s->st != 'e'){
    printf("value %d: freq %d\n", s->id, s->frq);
    s++;
  }
}
  /*void sortHistogram(Histogram *s){
  struct temp{int id; int frq; int st;};
  struct temp small;
  small.id = s[0].id;
  small.frq = s[0].frq;

  // getting length
  int size = 0;
  while(s[size].st == 'f'){
    size++;
  }
  struct temp t[size];
  for(int i = 0; i < size; i++){
    if(small.frq > s[i].frq){
      small.frq = s[i].frq;
      small.id = s[i].id;
    }
  }
  
  for(int i = 1; i < size; i++){
    small.id = s[i].id;
    small.frq = s[i].frq;
    printf("test small frq %d\n", small.frq);
    for(int y = 0; y < size; y++){
      if(small.frq > s[y].frq && s[y].st == 'f'){
	small.frq = s[y].frq;
	small.id = s[y].id;
	s[y].st = 'x';
	printf("triggered new small test frq %d\n", small.frq);
      }
    }
    t[i].id = small.id;
    t[i].frq = small.frq;
  }

  for(int i = 0; i < size; i++){
    printf("t[%d] %d\n", i, t[i].frq);
  }
}
  */
