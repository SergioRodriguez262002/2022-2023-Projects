#include <stdio.h>
#include <stdlib.h> // malloc                                                   
#include <string.h>



typedef struct tnode {
  double weight;
  int c;
  struct tnode* left;
  struct tnode* right;
  struct tnode* parent;
}tNode;

typedef struct LinkedList {
  //int value;
  tNode* node;
  struct LinkedList* next;
} LinkedList;


LinkedList* llCreate();
int llIsEmpty(LinkedList* ll);
void llDisplay(LinkedList* ll);
void llAdd(LinkedList** ll, tNode* newValue);
void llFree(LinkedList* ll);
void list_add_in_order(LinkedList** ll, tNode* node);

LinkedList* llCreate() { // New linked list                                     
  return NULL;
}

int llIsEmpty(LinkedList* ll) { // Checks if the ll is empty
  return (ll == NULL);
}

void llDisplay(LinkedList* ll) { // displays the ll

  LinkedList* p = ll;

  printf("[");

  while (p != NULL) { // while the pointer is not null display the character
    // and weight
    printf("%c:", (*p).node->c);
    printf("%d, ", (int)(*p).node->weight);
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
  llDisplay(*ll);
  printf("incoming node %c:%d\n", node->c, (int)node->weight);
  if(*ll == NULL){
    llAdd(ll,node);
  } else {

  LinkedList *temp = *ll;
  int count = 0;
  //  while temp has a next and the weight is larger than the next weigh
  while(temp->next != NULL && (node->weight) > (temp->node)->weight ){
    temp = temp->next;
    count++;
  }

  // editing the original ll
  LinkedList *new = (LinkedList*)malloc(sizeof(LinkedList));
  new->node = node;
  new->next = NULL;
  // Tha above sets the new node and its null next
  LinkedList *editedLL = *ll;
  if(count == 0){ // if the new node is at the start, add at the start
    *ll = new;
    new->next = editedLL;
  }else{
    for(int i = 0; i < count; i++){ // Count how far down the linked list to traverse
      editedLL = editedLL->next;
    }

    LinkedList* stop = editedLL->next; // at the select index
    // attach the new node at the next pointer
    // and set the new node pointer to the previous next pointer
    editedLL->next = new;
    new->next = stop;
  }
  }
  llDisplay(*ll);
  printf("end of add in order \n");
}

