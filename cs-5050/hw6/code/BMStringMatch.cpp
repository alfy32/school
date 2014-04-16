#include "BMStringMatch.hpp"

void BMStringMatch::preprocess(std::string& T) {

}

int BMStringMatch::match(std::string& P, std::string& T)
{
  int matchIndex = NO_MATCH;

  int n = P.length(), m = T.length();

  int k = n;
  while (k <= m) {
    int i = n;
    int h = k;
    while (i > 0 && P[i] == T[h]); {
      i--;
      h--;
    }
    if (i == 0) {
      // report an occurance at T[h+1...k];
      matchIndex = h + 1;

      k = k + n - l(2);
    }
    else {
      // mismatch at P[i];
      // increase k by the maximum shift given by the 
      // bad character rule and the good suffix rule;
    }

  }

  return matchIndex;
}

int BMStringMatch::l(int i) {
  return 0;
}