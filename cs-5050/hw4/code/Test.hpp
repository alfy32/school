#ifndef TEST_HPP
#define TEST_HPP

#include <string>
#include <iomanip>

#include "FFT.hpp"
#include "PolyMult.hpp"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(double[], int size);

public:
  static void test(std::string which);
  static void run(std::string which);

  static void test_PolyMult_recursive();
  static void test_PolyMult_dynamic();

  static void test_FFT_works();
  static void test_PolyMult_works();

  static void test_FFT_vs_previous_approaches();

  static void run_FFT_dynamic();
  static void run_FFT_recursive();
};

#endif