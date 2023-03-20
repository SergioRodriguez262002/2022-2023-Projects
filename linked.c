#include <stdio.h>
#include <stdlib.h>

typedef struct node {

  int value;
  struct node* next; // Cannot name this next node LinkedList*
  // because typedef LinkedList doesnt exists yet
} LinkedList;

LinkedList* llCreate(){
  return NULL;
}

void llDisplay(LinkedList* l){
  LinkedList* p = l;
  printf("[");
  while(p != NULL){
    printf("%d, ", p->value);
    p = p->next;
  }
  printf("]\n");
}

void llAdd(LinkedList** l, int value){
  LinkedList* nn = (LinkedList*)malloc(1*sizeof(LinkedList));
  nn->value = value;
  nn->next = NULL;

  LinkedList* p = l;
  if(p == NULL){
    *l = nn;
  } else {
    while(p->next != NULL){ //Look ahead approach
      p = p->next;
    }
  }

  p->next = nn;
}

int main(){

  LinkedList* ll = llCreate();
  llDisplay(ll);
  llAdd(&ll, 2);
  llDisplay(ll);  
}
