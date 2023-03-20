#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
//#include <sys/types.h>
//#include <sys/socket.h>                                                      
#include <netinet/in.h>

#include "duSocket.h"

int setupServerSocket(int portno){ // Like new ServerSocket in Java
  int sockfd = socket(AF_INET, SOCK_STREAM, 0);
  if (sockfd < 0) {
    printf("ERROR opening socket");
    exit(1);
  }
  
  // server address structure                                                  
  struct sockaddr_in serv_addr;

  // Set all the values in the server address to 0
  memset(&serv_addr, '\0', sizeof(serv_addr));

  // Setup the type of socket (internet vs filesystem)
  serv_addr.sin_family = AF_INET;

  // Basically the machine we are on...
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  
  // Setup the port number
  // htons - is host to network byte order
  // network byte order is most sig bype first
  // which might be host or might not be
  serv_addr.sin_port = htons(portno);

  // Bind the socket to the given port                                          
  if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
    printf("ERROR on binding\n");
    exit(1);
  }

  return sockfd;

}

int callServer(char* host, int portno){ // Like new Socket in Java 

// Socket pointer                                                             
  int sockfd;
  sockfd = socket(AF_INET, SOCK_STREAM, 0);
  if (sockfd < 0) {
    fprintf(stderr,"ERROR opening socket\n");
    exit(0);
  }

  // port number                                                                
  int portno = 9000;

  struct sockaddr_in serv_addr;

  // Set all the values in the server address to 0                              
  memset(&serv_addr, '\0', sizeof(serv_addr));

  // Setup the type of socket (internet vs filesystem)                          
  serv_addr.sin_family = AF_INET;

   // Setup the port number                                                     
  // htons - is host to network byte order                                      
  // network byte order is most sig byte first                                  
  //   which might be host or might not be                                      
  serv_addr.sin_port = htons(portno);
  // Setup the server host address                                              
  struct hostent *server;
  server = gethostbyname("localhost");
  if (server == NULL) {
    fprintf(stderr,"ERROR, no such host\n");
    exit(0);
  }
  memcpy(&serv_addr.sin_addr.s_addr, server->h_addr, server->h_length);  /// de\
st, src, size                                                                   

  // Connect to the server                                                      
  if (connect(sockfd,(struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) {
    printf("ERROR connecting\n");
    exit(0);
  }
  return 0;
}

int serverSocketAccept(int serverSocket){ // Like ss.accept() in Java
  int sockfd = serverSocket;
  listen(sockfd,5);


  int newsockfd;
  struct sockaddr_in cli_addr;
  socklen_t clilen = sizeof(cli_addr);

  // Wait for a call                                                            
  printf("waiting for a call...\n");
  newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
  printf("connected\n");
  if (newsockfd < 0) {
    printf("ERROR on accept");
    exit(1);
  }
  

  return 0;
}

void writeInt(int x, int socket){ // Write an int over the given socket 

}

int readInt(int socket){ // Read an int from the given socket
  return 0;
}




