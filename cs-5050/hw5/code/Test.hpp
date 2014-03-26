#ifndef TEST_HPP
#define TEST_HPP

#include <string>
#include <vector>
#include <iomanip>

#include "FFT.hpp"
#include "PolyMult.hpp"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(std::vector<double>&);

public:
  static void test(std::string which);
  static void run(std::string which);

};

#endif