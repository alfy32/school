#ifndef TEST_HPP
#define TEST_HPP

#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
#include <fstream>

#include "Point.hpp"
#include "ClosestPair.hpp"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(std::vector<double>&);

public:
  static void test(std::string which);
  static void run(std::string which);

  static void printPoints(std::vector<Point>);

  static void test_rightAnswers();
  static void test_uniformPoints(int n);

  static void run_naiveRandom();
  static void run_naiveUniform();
  static void run_naiveMixed();

  static void run_slowRandom();
  static void run_slowUniform();
  static void run_slowMixed();

  static void run_fastRandom();
  static void run_fastUniform();
  static void run_fastMixed();
};

#endif