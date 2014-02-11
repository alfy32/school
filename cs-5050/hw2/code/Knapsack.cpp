#include "Knapsack.hpp"

Knapsack::Knapsack(int n) {
  srand(time(NULL));

  size = std::vector<int>(n+1, 0);
  value = std::vector<int>(n+1, 0);

  cache = NULL;

  leftCache = rightCache = NULL;
  used = std::vector<bool>(n+1, false);
  split = 1.0/2.0;
}

Knapsack::~Knapsack() {
  if(cache != NULL)
    delete cache;
}

void Knapsack::initSizes(std::vector<int> sizes) {
  size = sizes;
}

void Knapsack::initValues(std::vector<int> values) {
  value = values;
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
  for(int size = 0; size < bagSize+1; ++size) {
    cache->set(0, size, 0);
  }

  for(int item = 0; item < n+1; ++item) {
    cache->set(item, 0, 0);
  }

  for(int item = 1; item <= n; item++) {
    for(int size = 1; size <= bagSize; size++) {

      int with;

      if(size - getSize(item) < 0) {
        with = 0;
      } else {
        with = getValue(item) + cache->get(item-1,size - getSize(item));
      }

      int without = cache->get(item-1, size);

      cache->set(item, size, with > without ? with : without);
    }
  }

  return cache->get(n, bagSize);
}

void Knapsack::fillBagLD(int start, int end, int bagSize, Cache* lCache) {
  lCache->reset();

  for(int item = start; (end > start) ? item <= end : item >= end; (end > start) ? ++item : --item) {
    for(int size = 1; size <= bagSize; ++size) {
      int with, without;

      if(size - getSize(item) < 0) {
        with = 0;
      } else {
        with = getValue(item) + lCache->get(item-1, size - getSize(item));
      }

      without = lCache->get(item-1, size);

      lCache->set(item, size, with > without ? with : without);
    }
  }
}

void Knapsack::initLinear(Cache* left, Cache* right, double split) {
  leftCache = left;
  rightCache = right;
  this->split = split;
}

int Knapsack::linear(int start, int end, int bagSize) {
  if(bagSize <= 0) return 0;
  if(end == start) {
    used[start] = getSize(start) <= bagSize;
    return getValue(start);
  }

  int mid = (end-start)*split + start;

  fillBagLD(start, mid, bagSize, leftCache);
  fillBagLD(end, mid+1, bagSize, rightCache);

  int bestValue = 0, bestSize = 0;

  for(int size = 0; size <= bagSize; size++) {
    int leftValue = leftCache->get(mid, size);
    int rightValue = rightCache->get(mid+1, bagSize - size);

    if(leftValue + rightValue > bestValue) {
      bestValue = leftValue + rightValue;
      bestSize = size;
    }
  }

  linear(start, mid, bestSize);
  linear(mid+1, end, bagSize - bestSize);

  return bestValue;
}

std::vector<bool> Knapsack::getLinearUsed() {
  return used;
}

std::vector<bool> Knapsack::getItemsUsed(int n, int bagSize) {
  std::vector<bool> used(n+1, false);

  findUsed(n, bagSize, used);

  return used;
}

void Knapsack::findUsed(int n, int bagSize, std::vector<bool>& used) {
  if(n == 0) return;
  if(bagSize < 0) return;

  if( (cache->get(n, bagSize) == cache->get(n-1, bagSize)) ||
      (cache->get(n, bagSize) == 0 && cache->get(n-1, bagSize) == NOT_SEEN)
    ) {
    used[n] = false;
  } else {
    used[n] = true;
    bagSize -= getSize(n);
  }

  findUsed(n-1, bagSize, used);
}

void Knapsack::print() {
  std::cout << "i" << '\t' << "Size" << '\t' << "Value" << std::endl;
  std::cout << "--" << '\t' << "----" << '\t' << "-----" << std::endl;

  for(int i = 1; i < size.size(); i++) {
    std::cout << i << '\t' << size[i] << '\t' << value[i] << std::endl;
  }

  std::cout << std::endl;
}