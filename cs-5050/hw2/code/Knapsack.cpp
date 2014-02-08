#include "Knapsack.hpp"

Knapsack::Knapsack(int n) {
  srand(time(NULL));

  size = std::vector<int>(n+1, 0);
  value = std::vector<int>(n+1, 0);
}

Knapsack::~Knapsack() {
  delete cache;
}

void Knapsack::initSizes(int min, int max) {
  std::generate(size.begin(), size.end(), [=](){
    return rand() % (max - min + 1) + min;
  });
}

void Knapsack::initValues(int min, int max) {
  std::generate(value.begin(), value.end(), [=](){
    return rand() % (max - min + 1) + min;
  });
}

void Knapsack::setCache(Cache* cache) {
  this->cache = cache;
}

int Knapsack::getValue(int index) {
  return value[index];
}

int Knapsack::getSize(int index) {
  return size[index];
}

Cache* Knapsack::getCache() {
  return cache;
}

int Knapsack::fillBagRecursive(int n, int bagSize) {
  if(n == 0) return 0;
  if(bagSize < 0) return 0;

  int with = getValue(n) + fillBagRecursive(n-1, bagSize - getSize(n));
  int without = fillBagRecursive(n-1, bagSize);

  if(bagSize - getSize(n) < 0) with = 0; // current item doesn't fit.

  return with > without ? with : without;
}

int Knapsack::fillBagCaching(int n, int bagSize) {
  if(n == 0) return 0;
  if(bagSize < 0) return 0;

  if(cache->seen(n,bagSize)) return cache->get(n,bagSize);

  int with = getValue(n) + fillBagCaching(n-1, bagSize - getSize(n));
  int without = fillBagCaching(n-1, bagSize);

  if(bagSize - getSize(n) < 0) with = 0; // current item doesn't fit.

  int maxValue = with > without ? with : without;

  cache->set(n,bagSize, maxValue);
  return maxValue;
}

int Knapsack::fillBagDynamic(int n, int bagSize) {
  for(int col = 0; col < bagSize+1; ++col) {
    cache->set(0, col, 0);
  }

  for(int row = 0; row < n+1; ++row) {
    cache->set(row, 0, 0);
  }
  
  for(int item = 1; item <= n; item++) {
    for(int curSize = 1; curSize <= bagSize; curSize++) {

      int with;

      if(curSize - size[item] < 0) {
        with = 0;
      } else {
        with = getValue(item) + cache->get(item-1,curSize - getSize(item));
      }

      int without = cache->get(item-1, curSize);

      cache->set(item, curSize, with > without ? with : without);
    }
  }

  return cache->get(n, bagSize);
}

void Knapsack::print() {
  std::cout << "i" << '\t' << "Size" << '\t' << "Value" << std::endl;
  std::cout << "--" << '\t' << "----" << '\t' << "-----" << std::endl;

  for(int i = 1; i < size.size(); i++) {
    std::cout << i << '\t' << size[i] << '\t' << value[i] << std::endl;
  }

  std::cout << std::endl;
}