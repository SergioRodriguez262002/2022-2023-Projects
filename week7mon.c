#include <stdio.h>

int main(){

  printf("27 div 4 %d\n", 27/4);
  printf("27 mod 4 %d\n", 27%4);
  printf("-27 div 4 %d\n", -27/4);
  printf("-27 mod 4 %d\n", -27%4);

}



/*
  Sockets
  looking at tcp sockets.
  If reciving a lot of re submit requests, will slow down packet
  sending.

  UDP was desgined for broadcasting, sending something along
  the wire and having everyone tune in. Doesnt matter if packets are
  dropped. 

  package naming ex:

  edu.du.cs.srodriguez.foo

  Running multiple things on one window

  ./server &


  Error on binding is when no port is open, closing ports take
  time to open closed ports. 

 */



