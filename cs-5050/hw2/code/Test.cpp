#include "Test.hpp"

void Test::run(std::string which) {
  if(which == "All") {
    correctValues();
    traceback();
    linear();
  } else if(which == "values") {
    correctValues();
  } else if(which == "traceback") {
    traceback();
  } else if(which == "given") {
    showTraceback();
  } else if(which == "linear") {
    linear();
  } else if(which == "dptraceback") {
    DPTraceback();
  } else if(which == "divideandconquer") {
    divideAndConquer();
  } else if(which == "dpvsdc") {
    divideAndConquerAndDPTraceback();
  } else if(which == "linearsplit") {
    linearSplit();
  }
}

void Test::correctValues() {
  std::cout << "Testing for correct values..." << std::endl;

  int n = 5;
  int bagSize = 10;
  int minSize = 1, maxSize = 5;
  int minValue = 10, maxValue = 100;

  Cache* cachingCache = new CacheRegular(n, bagSize);
  Cache* dynamicCache = new CacheRegular(n, bagSize);

  Knapsack knapsack(n);
  knapsack.initSizes(minSize,maxSize);
  knapsack.initValues(minValue,maxValue);

  int recursive = knapsack.fillBagRecursive(n, bagSize);

  knapsack.setCache(cachingCache);
  int caching = knapsack.fillBagCaching(n, bagSize);

  knapsack.setCache(dynamicCache);
  int dynamic = knapsack.fillBagDynamic(n, bagSize);

  Cache* left = new CacheLinear(n, bagSize);
  Cache* right = new CacheLinear(n, bagSize);
  knapsack.initLinear(left, right);
  int linear = knapsack.linear(1, n, bagSize);

  if(recursive == caching && recursive == dynamic && recursive == linear) {
    std::cout << "All three values match." << std::endl;
  } else {
    std::cout << "Error: The values don't match" << std::endl;
  }
}

void Test::traceback() {
  std::cout << "Testing traceback..." << std::endl;

  int n = 5;
  int bagSize = 10;
  int minSize = 1, maxSize = 5;
  int minValue = 10, maxValue = 100;

  int s[] = {0,6, 4, 3, 5, 5};
  int v[] = {0,12,15,15,16,70};

  Cache* cachingCache = new CacheRegular(n, bagSize);
  Cache* dynamicCache = new CacheRegular(n, bagSize);

  Knapsack knapsack(n);
  knapsack.initSizes(std::vector<int>(s, s+6));
  knapsack.initValues(std::vector<int>(v, v+6));

  int recursive = knapsack.fillBagRecursive(n, bagSize);

  knapsack.setCache(cachingCache);
  int caching = knapsack.fillBagCaching(n, bagSize);
  std::vector<bool> usedCaching = knapsack.getItemsUsed(n, bagSize);

  knapsack.setCache(dynamicCache);
  int dynamic = knapsack.fillBagDynamic(n, bagSize);
  std::vector<bool> usedDynamic = knapsack.getItemsUsed(n, bagSize);

  bool passed = usedCaching[1] == false && usedDynamic[1] == false
             && usedCaching[2] == false && usedDynamic[2] == false
             && usedCaching[3] == false && usedDynamic[3] == false
             && usedCaching[4] == true && usedDynamic[4] == true
             && usedCaching[5] == true && usedDynamic[5] == true;

  if(passed)
    std::cout << "traceback worked" << std::endl;
  else
    std::cout << "Error: traceback failed" << std::endl;
}

void Test::showTraceback(int n, int bagSize) {
  bool givenItems = (n == 0 && bagSize == 0);

  if(givenItems) {
    n = 5;
    bagSize = 10;
  }

  Cache* cachingCache = new CacheRegular(n, bagSize);
  Cache* dynamicCache = new CacheRegular(n, bagSize);

  Knapsack knapsack(n);

  if(givenItems) {
    int s[] = {0,6, 4, 3, 5, 5};
    int v[] = {0,12,15,15,16,70};

    knapsack.initSizes(std::vector<int>(s, s+6));
    knapsack.initValues(std::vector<int>(v, v+6));
  } else {
    int minSize = 1, maxSize = 5;
    int minValue = 10, maxValue = 100;

    knapsack.initSizes(minSize,maxSize);
    knapsack.initValues(minValue,maxValue);
  }

  knapsack.print();

  int recursiveValue;

  if(n < 20)
    recursiveValue = knapsack.fillBagRecursive(n, bagSize);

  knapsack.setCache(cachingCache);
  int cachingValue = knapsack.fillBagCaching(n, bagSize);
  std::vector<bool> usedCaching = knapsack.getItemsUsed(n, bagSize);

  knapsack.setCache(dynamicCache);
  int dynamicValue = knapsack.fillBagDynamic(n, bagSize);
  std::vector<bool> usedDynamic = knapsack.getItemsUsed(n, bagSize);

  Cache* left = new CacheLinear(n, bagSize);
  Cache* right = new CacheLinear(n, bagSize);
  knapsack.initLinear(left, right);
  int linearValue = knapsack.linear(1, n, bagSize);
  std::vector<bool> usedLinear = knapsack.getLinearUsed();

  if(n < 20)
    std::cout << "Recursive Answer: " << recursiveValue << std::endl;

  std::cout << "Caching Answer: " << cachingValue << std::endl;
  std::cout << "Dynamic Answer: " << dynamicValue << std::endl;
  std::cout << "Linear Answer: " << linearValue << std::endl;

  std::cout << std::endl;

  std::cout << "Used" << '\t'
            << "Caching" << '\t'
            << "Dynamic" << '\t'
            << "Linear" << '\t'
            << "Same"
            << std::endl;
  for(int i = 1; i < usedCaching.size(); ++i) {
    std::string passed = "Same";
    if(usedCaching[i] == usedDynamic[i] && usedCaching[i] == usedLinear[i])
      passed = "Same";
    else
      passed = "*** Failed ***";

    std::cout << i << '\t'
              << usedCaching[i] << '\t'
              << usedDynamic[i] << '\t'
              << usedLinear[i] << '\t'
              << passed
              << std::endl;
  }

  std::cout << "Caching Cache:" << std::endl;
  cachingCache->print();
  std::cout << "Dynamic Cache:" << std::endl;
  dynamicCache->print();
}

void Test::linear() {
  std::cout << "Testing Linear...." << std::endl;

  { // Test 1
    int n = 5;
    int bagSize = 10;

    int s[] = {0,6, 4, 3, 5, 5};
    int v[] = {0,12,60,15,16,70};

    Cache* left = new CacheLinear(n, bagSize);
    Cache* right = new CacheLinear(n, bagSize);

    Knapsack knapsack(n);
    knapsack.initSizes(std::vector<int>(s, s+n+1));
    knapsack.initValues(std::vector<int>(v, v+n+1));
    knapsack.initLinear(left, right);

    int value = knapsack.linear(1, n, bagSize);

    if(value == 130)
      std::cout << "Passed Test 1" << std::endl;
    else
      std::cout << "Failed Test 1" << std::endl;
  }

  { // Test 2
    int n = 6;
    int bagSize = 20;

    int s[] = {0,6, 4, 3, 5, 5, 8};
    int v[] = {0,17,60,15,16,70,13};

    Cache* left = new CacheLinear(n, bagSize);
    Cache* right = new CacheLinear(n, bagSize);

    Knapsack knapsack(n);
    knapsack.initSizes(std::vector<int>(s, s+n+1));
    knapsack.initValues(std::vector<int>(v, v+n+1));
    knapsack.initLinear(left, right);

    int value = knapsack.linear(1, n, bagSize);

    if(value == 163)
      std::cout << "Passed Test 2" << std::endl;
    else
      std::cout << "Failed Test 2" << std::endl;
  }
}

template<typename T>
double Test::averageRuntime(T t, int runs) {
  double times[runs];

  for(int i = 0; i < runs; i++) {
    clock_t start = clock();

    t();

    clock_t end = clock();

    times[i] = (end - start)/(CLOCKS_PER_SEC/1000);
  }

  return avg(times, runs);
}

double Test::avg(double values[], int size) {
  double sum = 0;
  for(int i = 0; i < size; i++) {
    sum += values[i];
  }
  return sum/size;
}

void Test::divideAndConquer() {
  double leftSize = 1.0/2.0;
  int minValue = 10, maxValue = 100;
  int minSize = 1, maxSize = 10;
  int minN = 8, maxN = 64;
  int bagSize;

  std::cout << "Linear" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n++) {
    bagSize = 10*n;
    double runTime = averageRuntime([=](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);
      knapsack.initLinear(left, right, leftSize);

      knapsack.linear(1, n, bagSize);

      knapsack.getLinearUsed();
    }, 10);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::DPTraceback() {
  int minValue = 10, maxValue = 100;
  int minSize = 1, maxSize = 5;
  int minN = 8, maxN = 64;
  int bagSize;

  std::cout << "DP" << std::endl
            << "N" << '\t' << "Time" << std::endl;

  for(int n = minN; n <= maxN; n++) {
    bagSize = 10*n;
    double runTime = averageRuntime([=](){
      Cache* cache = new CacheRegular(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);
      knapsack.setCache(cache);

      knapsack.fillBagDynamic(n, bagSize);
      knapsack.getItemsUsed(n, bagSize);
    }, 10);

    std::cout << n << '\t' << runTime << std::endl;
  }
}

void Test::divideAndConquerAndDPTraceback() {
  int bagSize;
  int minN = 64, maxN = 1000000000;
  int minSize, maxSize;
  int minValue = 1, maxValue = 10;
  int runs = 30;

  std::cout << "Linear" << std::endl
            << "N" << '\t' << "linear" << '\t' << "dp" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {
    bagSize = 10*n;
    minSize = 1;
    maxSize = bagSize/10;

    int linearRunTime = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    int dynamicRunTime = averageRuntime([&](){
      Cache* cache = new CacheRegular(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.setCache(cache);

      knapsack.fillBagDynamic(n, bagSize);
      knapsack.getItemsUsed(n, bagSize);
    }, runs);

    std::cout << n << '\t' << linearRunTime << '\t' << dynamicRunTime << std::endl;
  }
}

void Test::linearSplit() {
  int bagSize;
  int minN = 32, maxN = 1000000000;
  int minSize, maxSize;
  int minValue = 1, maxValue = 10;
  int runs = 30;

  std::cout << "Linear Sizes" << std::endl
            << "N" << '\t' << "1/2" << '\t' << "1/4" << '\t' << "1/8" << '\t' << "1/16" << '\t' << "1/32" << '\t' << "1" << std::endl;

  for(int n = minN; n <= maxN; n*=2) {
    bagSize = 10*n;
    minSize = 1;
    maxSize = bagSize/10;

    int r2 = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right, 1.0/2.0);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    int r4 = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right, 1.0/4.0);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    int r8 = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right, 1.0/8.0);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    int r16 = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right, 1.0/16.0);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    int r32 = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right, 1.0/32.0);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    int r1 = averageRuntime([&](){
      Cache* left = new CacheLinear(n, bagSize);
      Cache* right = new CacheLinear(n, bagSize);

      Knapsack knapsack(n);
      knapsack.initValues(minValue, maxValue);
      knapsack.initSizes(minSize, maxSize);

      knapsack.initLinear(left, right, 1.0/n);

      knapsack.linear(1, n, bagSize);
      knapsack.getLinearUsed();
    }, runs);

    std::cout << n << '\t' << r2 << '\t' << r4 << '\t' << r8 << '\t' << r16 << '\t' << r32 << '\t' << r1 << std::endl;
  }
}