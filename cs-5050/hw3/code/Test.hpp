#ifndef TEST_HPP
#define TEST_HPP

#include <string>

#include "PolyMult.hpp"

class Test {
private:
  template<typename T>
  static double averageRuntime(T t, int);
  static double avg(double[], int size);

public:
  static void test(std::string which);
  static void run(std::string which);

  static void SchoolBook();
  static void SchoolBook_works();

  static void DnC4();
  static void DnC4_works();
};

#endif