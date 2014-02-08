#ifndef CACHE_REGULAR_HPP
#define CACHE_REGULAR_HPP

#include <vector>
#include <iostream>
#include <algorithm>

#include "Cache.hpp"

class CacheRegular : public Cache {
private:
  std::vector<std::vector<int> > cache;

public: 

  CacheRegular(int n, int bagSize);

  void reset();

  int get(int row, int col);
  void set(int row, int col, int value);

  bool seen(int row, int col);

  void print(); 
};

#endif