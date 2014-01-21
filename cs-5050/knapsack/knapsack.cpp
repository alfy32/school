#include "knapsack.hpp"

const int MAX_SIZE = 10;
const int MAX_VALUE = 100;

Knapsack::Knapsack(int minSize, int maxSize, int n, int bagSize) {
  srand(time(NULL));

  size = std::vector<int>(n+1);
  value = std::vector<int>(n+1);

  std::generate(size.begin(), size.end(), [=](){
    return rand() % (maxSize - minSize + 1) + minSize;
  });

  std::generate(value.begin(), value.end(), [](){
    return rand() % MAX_VALUE + 1;
  });

  cacheInitialized = false;
}

Knapsack::Knapsack(int n) {
  srand(time(NULL));

  size = std::vector<int>(n+1);
  value = std::vector<int>(n+1);

  cacheInitialized = false;
}

void Knapsack::initSize(int min, int max) {
  std::generate(size.begin(), size.end(), [=](){
    return rand() % (max - min + 1) + min;
  });
}

void Knapsack::initValue(int min, int max) {
  std::generate(value.begin(), value.end(), [=](){
    return rand() % (max - min + 1) + min;
  });
}

void Knapsack::initCache(int n, int bagSize) {
  cache = std::vector< std::vector<CacheItem> >
          (n+1, std::vector<CacheItem>(bagSize+1, CacheItem(false, 0)));

  cacheInitialized = true;
}

int Knapsack::fillBagRecursive(int n, int bagSize) {
  if(n == 0) return 0;
  if(bagSize < 0) return 0;

  int with    = value[n] + fillBagRecursive(n-1, bagSize - size[n]);
  int without = fillBagRecursive(n-1, bagSize);

  if(bagSize - size[n] < 0) with = 0; // current item doesn't fit.

  return with > without ? with : without;
}

int Knapsack::fillBagCaching(int n, int bagSize) {
  if(!cacheInitialized) initCache(n, bagSize);

  if(n == 0) return 0;
  if(bagSize < 0) return 0;

  if(cache[n][bagSize].seen) return cache[n][bagSize].value;

  int with    = value[n] + fillBagCaching(n-1, bagSize - size[n]);
  int without = fillBagCaching(n-1, bagSize);

  if(bagSize - size[n] < 0) with = 0; // current item doesn't fit.

  cache[n][bagSize].value = with > without ? with : without;
  return cache[n][bagSize].value;
}

int Knapsack::fillBagDynamic(int n, int bagSize) {
  cache.clear();
  initCache(n, bagSize);

  std::fill(cache[0].begin(), cache[0].end(), CacheItem(true, 0));
  
  for(int curItem = 1; curItem <= n; curItem++) {
    for(int curBagSize = 1; curBagSize <= bagSize; curBagSize++) {

      int with;

      if(curBagSize - size[curItem] < 0) {
        with = 0;
      } else {
        with = value[curItem] + cache[curItem-1][curBagSize - size[curItem]].value;
      }

      int without = cache[curItem-1][curBagSize].value;

      cache[curItem][curBagSize].value = with > without ? with : without;
    }
  }

  return cache[n][bagSize].value;
}

void Knapsack::print() {
  std::cout << "Size\tRow" << std::endl;

  for(int row = 1; row < size.size(); row++) {
    std::cout << size[row] << "\t" << value[row] << std::endl;
  }
}