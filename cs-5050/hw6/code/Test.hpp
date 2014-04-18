#ifndef TEST_HPP
#define TEST_HPP

#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
#include <fstream>

#include "StringMatch.hpp"
#include "NaiveStringMatch.hpp"
#include "KMPStringMatch.hpp"
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
  static void test_kmp_works();

  static void test_1(StringMatch*);
  static void test_2(StringMatch*);
  static void test_3(StringMatch*);
  static void test_fail(StringMatch*);

  static void run_alphabet_size_naive();
  static void run_alphabet_size_bm();
  static void run_alphabet_size_kmp();
};

#endif