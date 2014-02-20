#ifndef POLY_MULT_HPP
#define POLY_MULT_HPP

#include <vector>
#include <iostream>
#include <algorithm>
#include <utility>

class PolyMult {
public:
  PolyMult();

  std::vector<double> makeRandomVector(int n, int min, int max);

  std::vector<double> schoolBook(int n, std::vector<double> P, std::vector<double> Q);
  std::vector<double> divideAndConquer4(int n, std::vector<double> P, std::vector<double> Q);
  std::vector<double> divideAndConquer3(int n, std::vector<double> P, std::vector<double> Q);

  void print(int n, std::vector<double>& P, std::vector<double>& Q, std::vector<double>& PQ);
};

#endif