#ifndef TEST_HPP
#define TEST_HPP

#include <string>

#include "Knapsack.hpp"
#include "CacheRegular.hpp"
#include "CacheLinear.hpp"

class Test {

public:
  static void run(std::string which);
  static void correctValues();
  static void traceback();
  static void showTraceback(int n = 0, int bagSize = 0);
  static void linear();

  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(double[], int size);

  static void divideAndConquer();
  static void DPTraceback();

  static void divideAndConquerAndDPTraceback();
};

#endif