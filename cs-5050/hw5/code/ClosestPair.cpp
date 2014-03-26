#include "ClosestPair.h"

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