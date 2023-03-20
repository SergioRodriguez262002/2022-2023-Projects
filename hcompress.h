#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// add to hcompress.h                                                           
LinkedList* createFreqTable(char* fileRead);
tNode* createHuffmanTree(LinkedList* leafNodes);
void treeDisp(tNode* root);
void encodeFile(char* file, LinkedList *leafNodes);



