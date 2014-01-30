#ifndef KNAPSAK_HPP
#define KNAPSAK_HPP

#include <vector>
#include <iostream>
#include <algorithm>
#include <ctime>
#include <cstdio>
#include <cstdlib>

struct CacheItem {
  bool seen;
  int value;

  CacheItem(bool s, int v) {
    seen = s, value = v;
  }
};

class Knapsack {
private: 
  std::vector<int> size, value;

  std::vector< std::vector<CacheItem> > cache;
  bool cacheInitialized;

public: 

  Knapsack(int, int, int, int);
  
  Knapsack(int);
  void initSize(int, int);
  void initValue(int, int);


  void initCache(int, int);

  int fillBagRecursive(int, int);
  int fillBagCaching(int, int);
  int fillBagDynamic(int, int);

  void print(); 
};

#endif