#include <iostream>
#include <thread>
#include <mutex>

int isPrime(int num);

int main(){
  // test is prime 0-10000
  int numPrime = 0;
  for(int i = 0; i <= 10000; i++){
    if(isPrime(i)){
      numPrime++;
    }
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
