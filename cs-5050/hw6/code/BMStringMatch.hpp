#ifndef BM_STRING_MATCH_HPP
#define BM_STRING_MATCH_HPP

#include <string>

#include "StringMatch.hpp"

class BMStringMatch : public StringMatch
{
private:
  int l(int i);
public:
  virtual void preprocess(std::string& T);
  virtual int match(std::string& P, std::string& T);
};

#endif