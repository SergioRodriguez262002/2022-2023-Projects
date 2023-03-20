#include <stdio.h>
#include <stdlib.h> // malloc
#include <string.h>

typedef struct Histogram{char* id; int frq; char st;} Histogram;

int* calcHistogram(Histogram **s, char *arr[], int size);
void displayScores(char *arr[], int size);
void displayHistogram(Histogram *s);
void sortHistogram(Histogram *s);
char** readScores(int *size);

char** test(){
  char **arr;
  char str[] = "hi";
  char str2[] = "hello";
  arr = malloc(2*sizeof(char*));
  arr[0] = malloc(strlen(str+1)*sizeof(char*));
  arr[1] = malloc(strlen(str2+1)*sizeof(char*));
  
  strcpy(arr[0],str);
  strcpy(arr[1],str2);
  printf("test %s\n", arr[0]);
  printf("test %s\n", arr[1]);
  return arr;
}

int main(){
  // scores and freq is on the heap
  int *size_scores;
  char **scores = readScores(&size_scores);
  displayScores(scores, size_scores);

  Histogram *freq;
  int size_hist = calcHistogram(&freq, scores, size_scores);
  displayHistogram(freq);

  free(freq);
  free(scores); 
  return 0;
}

char** readScores(int *size){
  int count = 0;
  char str[100];
  char **arr;
  arr = (char*)malloc(100 * sizeof(char*));
  while(scanf("%s", str) != EOF){
    arr[count] = malloc(strlen(str+1)*sizeof(char*));
    strcpy(arr[count],str);
    count++;
  }
  *size = count;
  return arr; 
}

void displayScores(char *arr[], int size){
  for(int i = 0; i < size; i++){
    printf("score %d: %s\n", i, arr[i]);
  }
}

int* calcHistogram(Histogram **h, char *arr[], int size){
  int num;
  int flag1 = 1;
  int size_hist;

  char str[100];
  Histogram *hist;
  hist = (Histogram*)malloc(100 * sizeof(Histogram));

  //printf("scores seg check\n");
  for(int i = 0; i < 10; i++){
    //printf("test %s\n", arr[i]);
  }

  char test[] = "this is a test";
  for(int i = 0; i < 110; i++){
    
    
    
    hist[i].st = 'e';
    hist[i].id = malloc(strlen(test+1)*sizeof(char*));
    strcpy(hist[i].id, test);
  }
  //printf("seg check\n");
  			  
  for(int x = 0; x < size; x++){
    for(int y = 0; y < size; y++){
      if( strcmp(arr[x],hist[y].id) == 0 ){
	hist[y].frq++;
	y = size;
	flag1 = 0;
      }
    }

    //adding unseen nums
    if(flag1){
      for(int i = 0; i < size; i++){
	if(hist[i].st == 'e'){
	  hist[i].st = 'f';
	  hist[i].id = malloc(strlen(arr[x]+1)*sizeof(char*));
	  strcpy(hist[i].id, arr[x]);
	  hist[i].frq = 1;
	  i = size;
	  size_hist++;
	}
      }
    }
    flag1 = 1;
  }
  

  *h = hist;   //  Make temp vars so I dont have to derefrence everytium 
  
  return size_hist;
}


void displayHistogram(Histogram *s){
  while(s->st != 'e'){
    printf("value %s: freq %d\n", s->id, s->frq);
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
