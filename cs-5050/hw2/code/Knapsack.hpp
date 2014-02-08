#ifndef KNAPSACK_HPP
#define KNAPSACK_HPP

#include <vector>
#include <iostream>
#include <algorithm>

#include "Cache.hpp"

class Knapsack {
protected: 
  std::vector<int> size, value;
  Cache* cache;

public: 

  Knapsack(int n);
  ~Knapsack();

  void initSizes(int min, int max);
  void initValues(int min, int max);
  void setCache(Cache*);

  int getValue(int);
  int getSize(int);  
  Cache* getCache();

  int fillBagRecursive(int, int);
  int fillBagCaching(int, int);
  int fillBagDynamic(int, int);

  void print(); 
};

#endif