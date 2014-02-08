#include "Test.hpp"

void Test::run(std::string which) {
  if(which == "All") {
    correctValues();
    traceback();
  } else if(which == "values") {
    correctValues();
  } else if(which == "traceback") {
    traceback();
  } else if(which == "random") {
    showTraceback();
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

  if(recursive == caching && recursive == dynamic) {
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

  int recursiveValue = knapsack.fillBagRecursive(n, bagSize);

  knapsack.setCache(cachingCache);
  int cachingValue = knapsack.fillBagCaching(n, bagSize);
  std::vector<bool> usedCaching = knapsack.getItemsUsed(n, bagSize);

  knapsack.setCache(dynamicCache);
  int dynamicValue = knapsack.fillBagDynamic(n, bagSize);
  std::vector<bool> usedDynamic = knapsack.getItemsUsed(n, bagSize);

  std::cout << "Recursive Answer: " << recursiveValue << std::endl;
  std::cout << "Caching Answer: " << recursiveValue << std::endl;
  std::cout << "Dynamic Answer: " << dynamicValue << std::endl;

  std::cout << std::endl;

  std::cout << "Used" << '\t' << "Caching" << '\t' << "Dynamic" << std::endl;
  for(int i = 1; i < usedCaching.size(); ++i) {
    std::cout << i << '\t' << usedCaching[i] << '\t' << usedDynamic[i] << std::endl;
  }

  std::cout << "Caching Cache:" << std::endl;
  cachingCache->print();
  std::cout << "Dynamic Cache:" << std::endl;
  dynamicCache->print();
}