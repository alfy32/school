#include "CacheRegular.hpp"

CacheRegular::CacheRegular(int n, int bagSize) {
  cache = std::vector<std::vector<int> >(n+1, 
    std::vector<int>(bagSize+1, NOT_SEEN));
}

void CacheRegular::reset() {
  for(std::vector<int> row : cache) {
    for(int col = 0; col < row.size(); ++col) {
      row[col] = NOT_SEEN;
    }
  }
}

bool CacheRegular::seen(int row, int col) {
  return cache[row][col] != NOT_SEEN;
}

int CacheRegular::get(int row, int col) {
  return cache[row][col];
}
void CacheRegular::set(int row, int col, int value) {
  cache[row][col] = value;
}

void CacheRegular::print() {
  for(std::vector<int> row : cache) {
    for(int value : row) {
      std::cout << value << ' ';
    }
    std::cout << std::endl;
  }
}