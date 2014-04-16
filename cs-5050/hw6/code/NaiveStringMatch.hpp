#ifndef NAIVE_STRING_MATCH_HPP
#define NAIVE_STRING_MATCH_HPP

#include <string>

#include "StringMatch.hpp"

class NaiveStringMatch : public StringMatch
{
private:

public:
  virtual void preprocess(std::string& T);
  virtual int match(std::string& P, std::string& T);
};

#endif