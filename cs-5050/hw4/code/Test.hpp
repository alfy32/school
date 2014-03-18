#ifndef TEST_HPP
#define TEST_HPP

#include <string>

#include "FFT.hpp"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(double[], int size);

public:
  static void test(std::string which);
  static void run(std::string which);
};

#endif