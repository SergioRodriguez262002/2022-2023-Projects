#include <stdio.h>

struct hist{int id; int frq; char st;};

void calcHistogram(struct hist *s, int arr[], int size);
void displayScores(int arr[], int size);
void displayHistogram(struct hist *s);
void sortHistogram(struct hist *s);
void readScores(int* arr, int* size);

int main(){
  struct hist freq[100];
  for(int i = 0; i < 100; i++){
    freq[i].st = 'e';
  }

  int scores[100];
  int size_scores;
  readScores(&scores,&size_scores);
  displayScores(scores, size_scores);
  calcHistogram(&freq,scores,size_scores);
  displayHistogram(freq);
  sortHistogram(&freq);

}

void readScores(int* arr, int* size){
  int testInteger;
  int count = 0;
  while(scanf("%d\n", &testInteger) != EOF){
    arr[count] = testInteger;
    count++;
  }
  *size = count;
}

void displayScores(int arr[], int size){
  for(int i = 0; i < size; i++){
    printf("score %d: %d\n", i, arr[i]);
  }
}

void calcHistogram(struct hist *s, int arr[],  int size){
  int num;
  int flag1 = 1;
  for(int x = 0; x < size; x++){
    for(int y = 0; y < size; y++){
      printf("no seg x %d y %d\n", x, y);
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
	}
      }
    }
    flag1 = 1;   
  }
}
void displayHistogram(struct hist *s){
  while(s->st != 'e'){
    printf("value %d: freq %d\n", s->id, s->frq);
    s++;
  }
}
void sortHistogram(struct hist *s){
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
