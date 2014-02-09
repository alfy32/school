#ifndef CACHE_LINEAR
#define CACHE_LINEAR

#include <vector>
#include <iostream>
#include <algorithm>

#include "Cache.hpp"

class CacheLinear : public Cache {
private:
  std::vector<int> cache[2];

public:

  CacheLinear(int n, int bagSize);

  void reset();

  int get(int row, int col);
  void set(int row, int col, int value);

  bool seen(int row, int col);

  void print();
};

#endif