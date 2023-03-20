#include <iostream>
#include <thread>
#include <mutex>

// compile like: g++ vecAdd.cc -std=c++11 -pthread -o vecAdd

int isPrime(int num);

// threading global vars
std::mutex screenLock; // locks screen
std::mutex numprimeLock; // locks prime count
int numPrime; // total number of primes

//threading function
void primethread(int start, int stop){
  screenLock.lock();
  std::cout << start << " - " << stop << "\n";
  screenLock.unlock();

  int localPrime = 0;
  for(int i = start; i < stop; i++){
    if(isPrime(i)){
      localPrime++;
    }
  }

  numprimeLock.lock();
  numPrime += localPrime;
  numprimeLock.unlock();
  
}

int main(){
  // test is prime 1-20
  /*for(int i = 0; i <= 20; i++){
    if(isPrime(i)){
      std::cout << i << " ";
    }
    }*/

  // Threading
  int lowerRange = 0;
  int upperRange = 10000;
  numPrime = 0;

  int nth = 4;
  std::thread* thread[nth];
  for(int i = 0; i < nth; i++){
    int start = ((upperRange - lowerRange)/nth)*i;
    int stop = ((upperRange - lowerRange)/nth)*(i+1);
    thread[i] = new std::thread(primethread, start, stop);
  }

  //joining results
  for(int i = 0; i < nth; i++){
    thread[i] -> join();
  }

  std::cout << "total primes " << numPrime << "\n";
  
  

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
