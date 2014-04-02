#include "ClosestPair.hpp"

const int WIDTH = 100;
const int HEIGHT = 100;

std::vector<Point> ClosestPair::randomPoints(int n) {
  std::vector<Point> points(n);

  for (int i = 0; i < n; ++i) {
    points[n] = Point::random(0, 100);
  }

  return points;
}

std::vector<Point> ClosestPair::uniformPoints(int n) {
  std::vector<Point> points(n);



  for (double x = 0; x < WIDTH; ++x) {
    for (double y = 0; y < HEIGHT; y += sqrt(3)/2) {
      points[n].x = x;
      points[n].y = y;
    }
  }

  return points;
}

std::vector<Point> ClosestPair::mixedPoints(int n) {
  std::vector<Point> points(n);

  for (int i = 0; i < n; ++i) {
    points[n] = Point::random(0, 100);
  }

  return points;
}

double ClosestPair::nTimesN(std::vector<Point>& points) {
  double closest = points[0].distance(points[1]);

  for (int i = 0; i < points.size() - 1; ++i) {
    for (int j = i + 1; j < points.size(); ++j) {
      double distance = points[i].distance(points[j]);

      if (distance < closest) {
        closest = distance;
      }
    }
  }

  return closest;
}