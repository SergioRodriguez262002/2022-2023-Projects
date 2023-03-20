#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedList.h"
#include "hcompress.h"


int main(int argc, char *argv[]) {


  // Check the make sure the input parameters are correct

   if (argc != 3) {
      printf("Error: The correct format is \"hcompress -e filename\" or \"hcompress -d filename.huf\"\n"); fflush(stdout);

    exit(1);
  }

  // Create the frequency table by reading the generic file
  LinkedList*  leafNodes = createFreqTable("testhuff.txt");
  llDisplay(leafNodes);

  //Create the huffman tree from the frequency table
  tNode* treeRoot = createHuffmanTree(leafNodes);
  treeDisp(treeRoot);
  llDisplay(leafNodes);
  //  treeDisp(leafNodes);
  

  
  // encode
  if (strcmp(argv[1], "-e") == 0) {
    // Pass the leafNodes since it will process bottom up
    encodeFile("testhuff.txt", leafNodes);
  } /*else { // decode
    // Pass the tree root since it will process top down
    decodeFile(argv[2], treeRoot);
    }*/
  return 0;
}

tNode* createHuffmanTree(LinkedList* leafNodes){
  tNode* root;
  // while there is a next node to search
  while(leafNodes->next != NULL){
    // Setting the root of of each sub tree 
    root = (tNode*)malloc(sizeof(tNode));
    root->c = 0;
    // setting the parent to the root
    leafNodes->node->parent = root;
    // seeting the left of root to the next node
    root->left = leafNodes->node;
    leafNodes = leafNodes->next;

    // seeting the parent of the root
    leafNodes->node->parent = root;
    // setting the right of root to the next node
    root->right = leafNodes->node;
    leafNodes = leafNodes->next;
    // setting the weight of the root to the combined weight of the left and right
    root->weight = (root->left)->weight + (root->right)->weight;

    // Ordering the subtree just generated
    list_add_in_order(&leafNodes, root);

  }
  return root;
}

void treeDisp(tNode *root){
  // Helper program for displaying the contents of a tree
   if(root != NULL) {
     printf("Node weight %d:%c\n",(int)root->weight, root->c);
     if(root->left != NULL){
     printf("Left node weight %d:%c\n", (int)(root->left)->weight, (root->left)->c);
     }
     if(root->right != NULL){
     printf("Right node weight %d:%c\n", (int)(root->right)->weight, (root->right)->c);
     }
      treeDisp(root->left);
      treeDisp(root->right);
   }
  

}

void pre_order_traversal(tNode* root) {
   if(root != NULL) {
     printf("%d ",(int)root->weight);
     pre_order_traversal(root->left);
     pre_order_traversal(root->right);
   }
}

void encodeFile(char* file, LinkedList* leafNodes){
  FILE* readIn = fopen(file, "r");

  char c;
  int charCode[128][100];

  LinkedList* pointer;
  tNode* temp;
  int tempSize = 0;

  char text[200];
  int charBin[128][8];

  int textCount = 0;
  FILE *fp = fopen("write.txt","wb");
  while((c = fgetc(readIn)) != EOF){
    text[textCount] = c;
    textCount++;
    tempSize = 0;
    pointer = leafNodes;

    while(pointer->node->c != (int)c){
      // finding the end of the linked list to the char read
      pointer = pointer->next;
    }

    temp = pointer->node->parent; // getting the parent 
    if((temp->right->c) == (pointer->node->c)){
      // if the right of the parent is the pointers c set the start to one
      // else set to zero
      charCode[c][tempSize] = 1;
    } else {
      charCode[c][tempSize] = 0;
    }
    // Ideally the below would create a stream of ones and zeros
    tempSize++; // Advance the size of the current stream of weight tracking
    while(temp->parent != NULL){
      // the while keep searching up the parent and creating a stream
      // of weights tracking the path to the root.
      charCode[c][tempSize] = (int)temp->weight;
      temp = temp->parent;
      tempSize++;
    }
    printf("read %c\n", c);
    for(int i = 0; i < tempSize; i++){ // reads the stream of weights to the root
      printf("%d, ",charCode[c][i]);
    }
    printf("\n");
    pointer = leafNodes;
    temp = pointer->node->parent;
    while(temp->parent != NULL){
      temp = temp->parent;
    }
    printf("p weight %d", (int)temp->weight);
    for(int x = 0; x < 128; x++){
      for(int y = 0; y < 8; y++){
	charBin[x][y] = 5; // setting all the binary character codes to a default of 5
      }
    }
    int track = tempSize-1;
    // the while loop below will search down the huffman tree
    // and set the character codes to ones and zeros based off
    // how the track leads to the character
    while(temp->left != NULL || temp->right != NULL){
      if(temp->right->weight == charCode[c][track]){
	printf("curr node right %d\n", (int)temp->right->weight);
	charBin[c][track] = 1;
	temp = temp->right;
      }else{
	printf("curr node left %d\n", (int)temp->left->weight);
	charBin[c][track] = 0;
	temp = temp->left;
      }
      track--;
    }
    printf("code for %c \n",c);
      for(int x = 0; x < tempSize; x++){
        printf("%d ",charBin[c][x]);
	//sprintf(char
	//fwrite(&charBin[c][x],1,sizeof(int),fp);
      }
    printf("\n");
  }
  fclose(fp);
  fclose(readIn);
}

LinkedList* createFreqTable(char* fileRead){
  // opening the file
  FILE* read;
  read = fopen(fileRead, "r"); // opening and reading r

  // intit char array
  // use the int representaion of the char as the index and
  // add the frequency to the array at index
  int arr[128];
  for(int i = 0; i < 128; i++){
    arr[i] = 0;
  }

  int count = 0;
  char c;
  while((c = fgetc(read)) != EOF){
    // checking through every character and adding at the character index
    // characters and ints are interchangble
    arr[(int)c]++;
    
  }
  fclose(read); // closing the stream
  for(int i = 0; i < 128; i++){
    if(arr[i]){
      printf("%c : %d\n ", i, arr[i]);
    }
    
   } 
  LinkedList* leafNodes = llCreate(); // generating the leaf nodes
  for(int i = 0; i < 128; i++){
    if(arr[i] != 0){
      // creating the new node and setting the weight and character from arr
      tNode* node = (tNode*)malloc(sizeof(tNode)); 
      node->c = i;
      node->weight = arr[i];
      list_add_in_order(&leafNodes, node); // Ordering the leaf nodes from largest to smallest
    }
  }
  

  return leafNodes;
}

