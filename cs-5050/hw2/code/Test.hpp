#ifndef TEST_HPP
#define TEST_HPP

#include <string>

#include "Knapsack.hpp"
#include "CacheRegular.hpp"

class Test {

public:
  static void run(std::string which);
  static void correctValues();
  static void traceback();
  static void showTraceback(int n = 0, int bagSize = 0);
};

#endif