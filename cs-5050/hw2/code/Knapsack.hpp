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

  void findUsed(int n, int bagSize, std::vector<bool>& used);

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

  std::vector<bool> getItemsUsed(int n, int bagSize);  

  void print(); 
};

#endif