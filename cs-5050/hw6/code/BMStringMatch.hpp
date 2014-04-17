#ifndef BM_STRING_MATCH_HPP
#define BM_STRING_MATCH_HPP

#include <string>
#include <vector>
#include <algorithm>

#include "StringMatch.hpp"

class BMStringMatch : public StringMatch
{
private:
  std::vector<int> offsetTable;
  std::vector<int> charTable;

  void makeCharTable(std::string& needle, int alphabetSize);
  void makeOffsetTable(std::string& needle);

  bool isPrefix(std::string needle, int p);
  int suffixLength(std::string needle, int p);
public:
  virtual void preprocess(std::string& needle);
  virtual int match(std::string& haystack, std::string& needle);
};

#endif