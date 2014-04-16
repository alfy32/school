#include "NaiveStringMatch.hpp"

void NaiveStringMatch::preprocess(std::string& T) {

}

int NaiveStringMatch::match(std::string& P, std::string& T)
{
  int matchIndex = NO_MATCH;
  bool match = true;

  for (int p = 0; p < P.length() - T.length() + 1; ++p) {
    match = true;
    for (int t = 0; t < T.length(); ++t) {
      if (P[p+t] != T[t]) {
        match = false;
        break;
      }
    }
    if (match) matchIndex = p;
  }

  return matchIndex;
}