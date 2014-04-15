#ifndef TEST_HPP
#define TEST_HPP

#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
#include <fstream>

#include "StringMatch.h"
#include "NaiveStringMatch.h"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(std::vector<double>&);

public:
  static void test_all();

  static void test_naive_works();
};

#endif