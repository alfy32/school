#ifndef POINT_H
#define POINT_H

#include <cmath>
#include <ctime>
#include <cstdlib>
#include <ostream>

class Point {
public:
  int x, y;

  Point(int = 0, int = 0);

  static Point random(int min, int max);

  double distance(Point);
};

std::ostream& operator<< (std::ostream& out, Point point);

#endif