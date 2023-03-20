#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
//#include <sys/types.h>      
//#include <sys/socket.h>
#include <netinet/in.h>
#include "duSocket.h"


int main(){

  int ss = setupServerSocket(9000);

  

  return 0;
}
