#include <iostream>
#include <thread>
#include <mutex>
#include <omp.h>

int isPrime(int num);

std::mutex screenLock;

int main(){



  // OpenMP threading

  
  int lowerRange = 1000;
  int upperRange = 1000000;
  int N = omp_get_num_threads(); 
  int block = (upperRange - lowerRange) / N;

  int numPrime = 0;
  double start = omp_get_wtime();
  
  std::cout << "total primes non threaded test\n";
  /*for(int i = lowerRange; i < upperRange; i++){
    if(isPrime(i)){
      numPrime++;
    }
    }*/
  double stop = omp_get_wtime();
  double totalTime = stop - start ;
  std::cout << "total primes non threaded " << numPrime << " time "<< totalTime << "\n";

  numPrime = 0;

  start = omp_get_wtime();

  // striping?
  

  
  

#pragma omp parallel for schedule(static, 8) reduction (+:numPrime)
  // reduction acts as a join
  // give 5 to each thread in a cound
  // for scheuld breaks the range into all threads
  // reduction handles the rance conditions
  // one pragma with a parralel and one pragma with a for
  
  for(int i = lowerRange; i < upperRange; i++){
    if(isPrime(i)){
      numPrime++;
    }
  }  

  stop = omp_get_wtime();
  totalTime = stop - start;
  std::cout << "total primes threaded " << numPrime << " time " << totalTime << "\n";


  // No scheduling

  numPrime = 0;

  start = omp_get_wtime();
#pragma omp parallel reduction (+:numPrime)
  {
    int tn = omp_get_thread_num();
    int numT = omp_get_num_threads();
    screenLock.lock();
    std::cout << "I'm thread " << tn << " of " << numT << "\n";
    screenLock.unlock();
    block = (upperRange - lowerRange) / numT;

#pragma omp for schedule(static, block)
    for(int i = lowerRange; i < upperRange; i++){
      //screenLock.lock();
      //std::cout << tn << ": " << i << "\n";
      //screenLock.unlock();
      if(isPrime(i)){
	numPrime++;
      }
    }
  }

  stop = omp_get_wtime();

  totalTime = stop - start ;
  std::cout << "total primes threaded " << numPrime << " time "<< totalTime <\
< "\n";

  
  
}
int isPrime(int num){
  if(num == 0 || num == 1){
    return 0;
  }
  int div = 2;
  while(num % div != 0){
    div++;
  }
  if(num == div){
    return num;
  }
  return 0;
}
