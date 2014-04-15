#ifndef NAIVE_STRING_MATCH_HPP
#define NAIVE_STRING_MATCH_HPP

#include <string>

#include "StringMatch.h"

class NaiveStringMatch : public StringMatch
{
private:

public:
  virtual int match(std::string P, std::string T);
};

#endif