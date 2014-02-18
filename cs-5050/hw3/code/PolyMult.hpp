#ifndef POLY_MULT_HPP
#define POLY_MULT_HPP

#include <vector>
#include <iostream>
#include <algorithm>
#include <utility>

class PolyMult {
  std::vector<double> P,Q,PQ;
public:
  PolyMult(int n);
  PolyMult(int n, double p[], double q[]);

  std::vector<double> getP() { return P; }
  std::vector<double> getQ() { return Q; }
  std::vector<double> getPQ() { return PQ; }

  void schoolBook();
  void divideAndConquer4(int pLow, int pHigh, int qLow, int qHigh);
  void divideAndConquer3(int pLow, int pHigh, int qLow, int qHigh);

  void print();
};

#endif