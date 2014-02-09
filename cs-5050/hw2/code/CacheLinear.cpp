#include "CacheLinear.hpp"

CacheLinear::CacheLinear(int n, int bagSize) {
  cache[0] = std::vector<int>(bagSize+1, NOT_SEEN);
  cache[1] = std::vector<int>(bagSize+1, NOT_SEEN);
}

void CacheLinear::reset() {
  for(int bagSize = 0; bagSize < cache[0].size(); ++bagSize) {
    cache[0][bagSize] = NOT_SEEN;
    cache[1][bagSize] = NOT_SEEN;
  }
}

bool CacheLinear::seen(int item, int bagSize) {
  return cache[item % 2][bagSize] != NOT_SEEN;
}

int CacheLinear::get(int item, int bagSize) {
  return cache[item % 2][bagSize];
}
void CacheLinear::set(int item, int bagSize, int value) {
  cache[item % 2][bagSize] = value;
}

void CacheLinear::print() {
  for(int bagSize = 0; bagSize < cache[0].size(); ++bagSize) {
    std::cout << cache[0][bagSize] << '\t' << cache[1][bagSize] << std::endl;
  }
}