#ifndef KNAPSACK_HPP
#define KNAPSACK_HPP

#include <vector>
#include <iostream>
#include <algorithm>
#include <utility>

#include "Cache.hpp"
#include "CacheLinear.hpp"

class Knapsack {
protected:
  std::vector<int> size, value;
  Cache* cache;

  Cache* leftCache,* rightCache;
  std::vector<bool> used;
  double split;

  void findUsed(int n, int bagSize, std::vector<bool>& used);
  void fillBagLD(int start, int end, int bagSize, Cache*);

public:

  Knapsack(int n);
  ~Knapsack();

  void initSizes(std::vector<int>);
  void initValues(std::vector<int>);

  void initSizes(int min, int max);
  void initValues(int min, int max);
  void setCache(Cache*);

  int getValue(int);
  int getSize(int);
  Cache* getCache();

  int fillBagRecursive(int, int);
  int fillBagCaching(int, int);
  int fillBagDynamic(int, int);

  void initLinear(Cache*, Cache*, double split=0.5);
  int linear(int start, int end, int bagSize);
  std::vector<bool> getLinearUsed();

  std::vector<bool> getItemsUsed(int n, int bagSize);

  void print();
};

#endif