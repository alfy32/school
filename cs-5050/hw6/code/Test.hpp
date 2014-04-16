#ifndef TEST_HPP
#define TEST_HPP

#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
#include <fstream>

#include "StringMatch.hpp"
#include "NaiveStringMatch.hpp"
#include "KMLStringMatch.hpp"
#include "BMStringMatch.hpp"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(std::vector<double>&);

public:
  static void test_all();

  static void test_naive_works();
  static void test_bm_works();
  static void test_kml_works();
};

#endif