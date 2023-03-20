#include <iostream>
#include <thread> // -std=c++11 -pthread
#include <mutex>

double epi;
std::mutex epiLock;

std::mutex screenLock;

// 2500
void pithread(int start, int stop) {

  screenLock.lock();
  std::cout << start << " - " << stop << "\n";
  screenLock.unlock();

  //double localEpi = 0.0;

  for (int i=start; i<stop; i++) {

    double term = 1.0/((2*i)+1);

    int sign = 1;
    if (i%2 == 1) {
      sign = -1;
    }

    epiLock.lock();
    epi = epi + (sign*term);
    epiLock.unlock();
  }

  //  epi = epi + localEpi;
}

int main() {
  int n = 10000;

  epi = 0.0;

  int nth = 4;

  std::thread* ths[nth];
  for (int i=0; i<nth; i++) {
    int start = (n/nth)*i;
    int stop = (n/nth)*(i+1);
    //if (i last term) then make stop by n
    ths[i] = new std::thread(pithread, start, stop);
  }

  for (int i=0; i<nth; i++) {
    ths[i]->join();
  }

  epi = epi * 4.0;

  std::cout << epi << "\n";
}
