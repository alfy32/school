#include "KMLStringMatch.hpp"

void KMLStringMatch::preprocess(std::string& p) {
  a = std::vector<int>(p.size() + 1, 0);

  int m = p.length();
  a[1] = 0;
  int k = 0;
  for (int q = 2; q <= m; ++q) {
    while (k > 0 && p[k + 1] != p[q]) {
      k = a[k];
    }
    if (p[k + 1] == p[q]) {
      k = k + 1;
    }
    a[q] = k;
  }
}

int KMLStringMatch::match(std::string& S, std::string& p)
{
  int matchIndex = NO_MATCH;
  
  int n = S.length();
  int m = p.length();

  int q = 0;

  for (int i = 1; i <= n; ++i) {
    while (q > 0 && p[q + 1] != S[i]) {
      q = a[q];
      if (p[q + 1] == S[i]) {
        q++;
      }
      if (q == m) {
        matchIndex = i - m;
        q = a[q];
      }
    }
  }

  return matchIndex;
}