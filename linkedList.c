#include <stdio.h>
#include <stdlib.h> // malloc
#include <string.h>
#include "linkedList.h"

/*
  Third, you will need to add a method called list_add_in_order which works very much like the current add, except that it takes the data in question and searches down the linked list from the given point and places the node “in order” into the list.  “In order” will be based on weights so that the lowest two weighed nodes will always be at the front. 
 */

//void list_add_in_order(LinkedList** ll, tnode* newValue

LinkedList* llCreate() { // New linked list 
  return NULL;
}

int llIsEmpty(LinkedList* ll) {
  return (ll == NULL);
}

void llDisplay(LinkedList* ll) {
  
  LinkedList* p = ll;

  printf("[");
  
  while (p != NULL) {
    printf("%d, ", (*p).node->c);
    p = p->next;
  }

  printf("]\n");
}


void llAdd(LinkedList** ll, tNode* newValue) {

  // Create the new node
  LinkedList* newNode = (LinkedList*)malloc(1 * sizeof(LinkedList));
  newNode->node = newValue;
  newNode->next = NULL;
  
  // Find the end of the list
  LinkedList* p = *ll;

  if (p == NULL) {  // Add first element
    *ll = newNode;  // This is why we need ll to be a **

  } else {

    while (p->next != NULL) {
      p = p->next;
    }
    
    // Attach it to the end
    p->next = newNode;
  }

}


void llFree(LinkedList* ll) {
  LinkedList* p = ll;

  while (p != NULL) {
    LinkedList* oldP = p;
    p = p->next;
    free(oldP);
  }
  
}

void list_add_in_order(LinkedList** ll, tNode* node){
  // tree is empty
  if(*ll == NULL){
    llAdd(ll,node);
  } else {
    LinkedList *temp = *ll;

    int count = 0;
    while((temp != NULL) && (node->weight > (temp->node)->weight)){
      count++;
      temp = temp->next;
    }
    llAdd(ll, node);
  }
}

int main() {
  /*
  LinkedList* l = llCreate();
  
  llDisplay(l);

  llAdd(&l, 1);
  llDisplay(l);

  llAdd(&l, 2);
  llDisplay(l);

  llAdd(&l, 3);
  llDisplay(l);

  llAdd(&l, 4);
  llDisplay(l);

  llFree(l);*/
}
