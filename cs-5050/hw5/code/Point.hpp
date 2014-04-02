#ifndef POINT_H
#define POINT_H

#include <cmath>
#include <ctime>
#include <cstdlib>

class Point {
public:
  int x, y;

  Point(int = 0, int = 0);

  static Point random(int min, int max);

  double distance(Point);
};

#endif