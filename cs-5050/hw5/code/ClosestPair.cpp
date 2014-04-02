#include "ClosestPair.hpp"

const int WIDTH = 100;
const int HEIGHT = 100;

std::vector<Point> ClosestPair::randomPoints(int n) {
  std::vector<Point> points(n);

  for (int i = 0; i < n; ++i) {
    points[i] = Point::random(0, 100);
  }

  return points;
}

std::vector<Point> ClosestPair::uniformPoints(int n) {
  std::vector<Point> points;

  double ratio = 100 / sqrt(n);

  for (int x = 0; x*ratio + ratio*0.5 < WIDTH; ++x) {
    for (int y = 0; y*ratio < HEIGHT; y++) {
      if (y % 2) {
        points.push_back(Point(ratio*x, ratio*y));
      }
      else {
        points.push_back(Point(ratio*x + ratio*0.5, ratio*y));
      }
      if (points.size() == n) return points;
    }
  }

  return points;
}

std::vector<Point> ClosestPair::mixedPoints(int n) {
  int n95 = n*0.95;
  int n05 = n - n95;

  std::vector<Point> points(uniformPoints(n95));
  std::vector<Point> random(randomPoints(n05));

  for (Point& point : random) {
    points.push_back(point);
  }

  return points;
}

double ClosestPair::nTimesN(std::vector<Point>& points, int low, int high) {
  double closest = points[0].distance(points[1]);

  for (int i = low; i < high; ++i) {
    for (int j = i + 1; j <= high; ++j) {
      double distance = points[i].distance(points[j]);

      if (distance < closest) {
        closest = distance;
      }
    }
  }

  return closest;
}

double ClosestPair::divideAndConquerSlow(std::vector<Point>& points, int low, int high) {
  // base case
  if (low + 1 == high) return points[low].distance(points[high]);
  if (low == high) return 200;

  // problem decomposition:
  // a) just split points[0...n/2-1] points[n/2...n-1]
  int mid = (high - low) / 2 + low;

  // b) sort by x first
  std::sort(points.begin() + low, points.begin() + high + 1, [](Point left, Point right){
   return left.x < right.x;
  });

  // Solution Construction:
  double left = divideAndConquerSlow(points, low, mid);
  double right = divideAndConquerSlow(points, mid + 1, high);

  double min = left < right ? left : right;

  int rightIndex = mid, leftIndex = mid;
  for (int i = mid; i >= 0 && points[mid].x - points[i].x < min; --i) {
    leftIndex = i;
  }
  for (int i = mid; i < points.size() && points[i].x - points[mid].x < min; ++i) {
    rightIndex = i;
  }

  // Right mid section
  for (int i = mid-1; i < rightIndex; ++i) {
    for (int j = i + 1; j <= rightIndex; ++j) {
      double distance = points[i].distance(points[j]);
      if (distance < min) min = distance;
    }
  }

  // Left mid section
  for (int i = mid+1; i > leftIndex; --i) {
    for (int j = i - 1; j >= leftIndex; --j) {
      double distance = points[i].distance(points[j]);
      if (distance < min) min = distance;
    }
  }

  return min;
}

double ClosestPair::divideAndConquerFast(std::vector<Point>& points, int low, int high) {
  // base case
  if (low + 1 == high)return points[low].distance(points[high]);
  if (low == high)  return 200;

  // problem decomposition:
  // a) just split points[0...n/2-1] points[n/2...n-1]
  int mid = (high - low) / 2 + low;

  // b) sort by x first
  std::sort(points.begin() + low, points.begin() + high + 1, [](Point left, Point right){
    return left.x < right.x;
  });

  // Solution Construction:
  double left = divideAndConquerFast(points, low, mid);
  double right = divideAndConquerFast(points, mid + 1, high);

  double min = left < right ? left : right;

  // find low and high of strip
  for (int i = mid; i >= 0 && points[mid].x - points[i].x < min; --i) {
    low = i;
  }
  for (int i = mid; i < points.size() && points[i].x - points[mid].x < min; ++i) {
    high = i;
  }

  // sort strip by y
  std::sort(points.begin() + low, points.begin() + high + 1, [](Point left, Point right){
    return left.y < right.y;
  });

  // linear scan back 6 points
  for (int i = low; i <= high; ++i) {
    for (int j = i - 1; j > i - 6 && j >= low; --j) {
      double distance = points[i].distance(points[j]);
      if (distance < min) min = distance;
    }
  }

  return min;
}