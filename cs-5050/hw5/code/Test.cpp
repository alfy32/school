#include "Test.hpp"

const int RUNS = 30;

/////////////// Private ////////////////////

template<typename T>
double Test::averageRuntime(T t, int runs) {
  std::vector<double> times(runs);

  for (int i = 0; i < runs; i++) {
    clock_t start = clock();

    t();

    clock_t end = clock();

    times[i] = (end - start) / (CLOCKS_PER_SEC / 1000);
  }

  return avg(times);
}

double Test::avg(std::vector<double>& values) {
  double sum = 0;
  for (int i = 0; i < values.size(); i++) {
    sum += values[i];
  }
  return sum / values.size();
}

///////////////// Public ////////////////////

void Test::test(std::string which) {
  if (which == "all") {

  }
  else if (which == "rightAnswers") {
    test_rightAnswers();
  }
}

void Test::run(std::string which) {
  if (which == "") {
    std::cout << "Type which test to run." << std::endl;
  }
}

///////////////// Tests ///////////////////

void Test::test_rightAnswers() {
  std::cout << "Tesing set points..." << std::endl;
  std::cout << std::endl;

  {
    int n = 5;

    ClosestPair closestPair;

    std::vector<Point> points(n);

    points[0] = Point(1, 2);
    points[1] = Point(2, 2);
    points[2] = Point(10, 12);
    points[3] = Point(20, 20);
    points[4] = Point(10, 40);

    for (Point& point : points) {
      std::cout << point << std::endl;
    }

    double naive = closestPair.nTimesN(points, 0, n - 1);
    double slow = closestPair.divideAndConquerSlow(points, 0, n - 1);
    double fast = closestPair.divideAndConquerFast(points, 0, n - 1);

    std::cout << std::endl;
    std::cout << "Naive:\t" << naive << std::endl;
    std::cout << "Slow:\t" << slow << std::endl;
    std::cout << "Fast:\t" << fast << std::endl;

    if ((int)naive != (int)slow || (int)naive != (int)fast)
      std::cout << "************Fail**********" << std::endl;
  }

  std::cout << std::endl;
  std::cout << "Testing random points..." << std::endl;
  std::cout << std::endl;

  {
    int n = 10;
    ClosestPair closestPair;
    std::vector<Point> randomPoints = closestPair.randomPoints(n);

    for (Point& point : randomPoints) {
      std::cout << point << std::endl;
    }

    double naive = closestPair.nTimesN(randomPoints, 0, n - 1);
    double slow = closestPair.divideAndConquerSlow(randomPoints, 0, n - 1);
    double fast = closestPair.divideAndConquerFast(randomPoints, 0, n - 1);

    std::cout << std::endl;
    std::cout << "Naive:\t" << naive << std::endl;
    std::cout << "Slow:\t" << slow << std::endl;
    std::cout << "Fast:\t" << fast << std::endl;

    if ((int)naive != (int)slow || (int)naive != (int)fast)
      std::cout << "************Fail**********" << std::endl;
  }

  std::cout << std::endl;
  std::cout << "Running 100 random tests..." << std::endl;
  std::cout << std::endl;

  {
    int good = 0, bad = 0;

    int n = 10;
    ClosestPair closestPair;

    for (int i = 0; i < 100; ++i) {
      std::vector<Point> randomPoints = closestPair.randomPoints(n);

      double naive = closestPair.nTimesN(randomPoints, 0, n - 1);
      double slow = closestPair.divideAndConquerSlow(randomPoints, 0, n - 1);
      double fast = closestPair.divideAndConquerFast(randomPoints, 0, n - 1);

      if ((int)naive != (int)slow || (int)naive != (int)fast) {
        bad++;
        std::cout << "Failed results" << std::endl;
        std::cout << "Naive:\t" << naive << std::endl;
        std::cout << "Slow:\t" << slow << std::endl;
        std::cout << "Fast:\t" << fast << std::endl;
        std::cout << std::endl;
      }
      else
        good++;
    }

    std::cout << "Results: " << good << " good, " << bad << " bad" << std::endl;
  }
}