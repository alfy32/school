#include "Point.hpp"

Point::Point(int x, int y) {
  this->x = x;
  this->y = y;
}

Point Point::random(int min, int max) {
  int x = rand() % (max - min) + min;
  int y = rand() % (max - min) + min;

  return Point(x, y);
}

double Point::distance(Point point) {
  int x = point.x - this->x;
  int y = point.y - this->y;

  return sqrt(x*x + y*y);
}

std::ostream& operator<< (std::ostream& out, Point point) {
  out << '(' << point.x << ',' << point.y << ')';
  return out;
}
