#ifndef TEST_HPP
#define TEST_HPP

#include "knapsack.hpp"

#include <iostream>
#include <string>
#include <ctime>

class Test {
private:

public:

  template<typename T>
  static double run(T t, int);
  static double avg(double[], int);

  static void test1(
    int runs = 30,
    int bagSize = 1000,
    int minItemSize = 1, int maxItemSize = 1000,
    int minValue = 1, int maxValue = 100,
    int minN = 6, int maxN = 1000
  );

  static void test2dynamic(int runs);
  static void test2caching(int runs);
};

#endif