#include <iostream>
#include <omp.h>
// linux1/2/3/4: openMP is alread installed.  Just need -fopenmp flag:
//    g++ piomp.cc -fopenmp -o pi

// mac: Need to install openMP with 'brew install libomp'
//    For Intel chips you need preprocessor and omp lib flags:
//      g++ piomp.cc -Xpreprocessor -fopenmp -lomp -o pi
//
//    For M1 (arm) chips you also need to give different include/lib paths
//      g++ piomp.cc -Xpreprocessor -fopenmp -lomp -L/opt/homebrew/opt/libomp/lib -I/opt/homebrew/opt/libomp/include -o pi

#include <mutex>

std::mutex screenLock;

int main() {

  omp_set_num_threads(4);

  // parallel forks the threads
#pragma omp parallel
  {
    int tn = omp_get_thread_num();
    int numT = omp_get_num_threads();
    screenLock.lock();
    std::cout << "I'm thread " << tn << " of " << numT << "\n";
    screenLock.unlock();

    // Break the threads over the loop
#pragma omp for schedule (static, 3) //nowait
    for (int i=0; i<10; i++) {
      screenLock.lock();
      std::cout << tn << ": " << i << "\n";
      screenLock.unlock();
    } // barrier (not a join one)
    
  } // barrier (join)

  std::cout << "world\n";

  /*

  omp_set_num_threads(4);

  int n = 10000;

  double epi = 0.0;

#pragma omp parallel for schedule(static, 2500) reduction (+:epi)
  for (int i=0; i<n; i++) {

    double term = 1.0/((2*i)+1);

    int sign = 1;
    if (i%2 == 1) {
      sign = -1;
    }

    epi = epi + (sign*term);
  }

  epi = epi * 4.0;

  std::cout << epi << "\n";
  */
}
