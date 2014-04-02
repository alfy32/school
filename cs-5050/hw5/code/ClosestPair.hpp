#ifndef CLOSEST_PAIR_H
#define CLOSEST_PAIR_H

#include <utility>
#include <vector>
#include <algorithm>
#include <iostream>
#include <climits>

#include "Point.hpp"

class ClosestPair {
public:
  std::vector<Point> randomPoints(int n);
  std::vector<Point> uniformPoints(int n);
  std::vector<Point> mixedPoints(int n);

  double nTimesN(std::vector<Point> points, int low, int high);
  double divideAndConquerSlow(std::vector<Point> points, int low, int high);
  double divideAndConquerFast(std::vector<Point> points, int low, int high);
};

#endif